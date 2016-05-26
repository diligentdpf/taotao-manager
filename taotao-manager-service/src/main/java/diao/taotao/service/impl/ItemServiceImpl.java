package diao.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import diao.taotao.common.EUDataGridResult;
import diao.taotao.common.util.IDUtils;
import diao.taotao.common.util.TaotaoResult;
import diao.taotao.mapper.TbItemCatMapper;
import diao.taotao.mapper.TbItemDescMapper;
import diao.taotao.mapper.TbItemMapper;
import diao.taotao.mapper.TbItemParamItemMapper;
import diao.taotao.pojo.TbItem;
import diao.taotao.pojo.TbItemDesc;
import diao.taotao.pojo.TbItemExample;
import diao.taotao.pojo.TbItemExample.Criteria;
import diao.taotao.pojo.TbItemParamItem;
import diao.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    TbItemMapper itemMapper;

    @Autowired
    TbItemCatMapper itemCatMapper;

    @Autowired
    TbItemDescMapper itemDescMapper;

    @Autowired
    TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getTBItemById(long itemId) {
        // TbItem item = tbItemMapper.selectByPrimaryKey(itemId);

        TbItemExample example = new TbItemExample();
        // 添加查询条件
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        // 查询商品列表，
        TbItemExample example = new TbItemExample();
        // 分页处理
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        // 创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        // 取记录总条数
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public int saveItem(TbItem item, String desc, String itemParam) throws Exception {
        Date date = new Date();
        // 获得商品id
        long id = IDUtils.genItemId();
        // 添加商品信息
        item.setId(id);
        // 商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(date);
        item.setUpdated(date);
        itemMapper.insert(item);
        // 添加商品描述
        // 创建TbItemDesc对象
        TbItemDesc itemDesc = new TbItemDesc();
        // 获得一个商品id
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        // 插入数据
        if (itemDescMapper.insert(itemDesc)<=0) {
            throw new Exception();
        }
        TaotaoResult result=insertItemParamItem(id,itemParam);
        if(result.getStatus()!=200){
            throw new Exception();
        }
        return 1;
    }

    public TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
        // 创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        // 向表中插入数据
        if (itemParamItemMapper.insert(itemParamItem) > 0) {
            return TaotaoResult.ok();
        }
        return null;
    }
}
