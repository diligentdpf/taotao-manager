package diao.taotao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import diao.taotao.common.EUDataGridResult;
import diao.taotao.common.util.JsonUtils;
import diao.taotao.common.util.TaotaoResult;
import diao.taotao.mapper.TbItemParamItemMapper;
import diao.taotao.mapper.TbItemParamMapper;
import diao.taotao.pojo.TbItemParam;
import diao.taotao.pojo.TbItemParamExample;
import diao.taotao.pojo.TbItemParamItem;
import diao.taotao.pojo.TbItemParamItemExample;
import diao.taotao.pojo.TbItemParamItemExample.Criteria;
import diao.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper itemParamMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TaotaoResult getItemParamByCid(long cid) {
        TbItemParamExample example = new TbItemParamExample();
        diao.taotao.pojo.TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        // 判断是否查询到结果
        if (list != null && list.size() > 0) {
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(TbItemParam itemParam) {
        // 补全pojo
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        // 插入到规格参数模板表
        itemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }

    /**
     * 添加规格参数 Title: insertItemParamItem
     * 
     * @param itemId
     * @param itemParam
     */

    @Override
    public TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
        // 创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        // 向表中插入数据
        itemParamItemMapper.insert(itemParamItem);
        return TaotaoResult.ok();

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public String getItemParamByItemId(Long itemId) {
        // 根据商品id查询规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        // 执行查询
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list == null || list.size() == 0) {
            return "";
        }
        // 取规格参数信息
        TbItemParamItem itemParamItem = list.get(0);
        String paramData = itemParamItem.getParamData();
        // 生成html
        // 把规格参数json数据转换成java对象
        List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
        StringBuffer sb = new StringBuffer();
        sb.append(
                "<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for (Map m1 : jsonList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group")
                    + "</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for (Map m2 : list2) {
                sb.append("<tr>\n");
                sb.append("<td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
                sb.append("<td>" + m2.get("v") + "</td>\n");
                sb.append("</tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }

    /**
     * 规格参数列表
     * @param  page
     * @param  rows
     * @writeTime 2016年5月23日11:43
     */
    @Override
    public EUDataGridResult getItemParamList(int page, int rows) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        // 分页处理
        PageHelper.startPage(page, rows);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        // 创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        // 取总条数
        PageInfo<TbItemParamItem> info = new PageInfo<>(list);
        result.setTotal(info.getTotal());
        return result;
    }
}
