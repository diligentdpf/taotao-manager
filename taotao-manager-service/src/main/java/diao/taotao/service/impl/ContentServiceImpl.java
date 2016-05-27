package diao.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import diao.taotao.common.EUDataGridResult;
import diao.taotao.mapper.TbContentMapper;
import diao.taotao.pojo.TbContent;
import diao.taotao.pojo.TbContentExample;
import diao.taotao.pojo.TbContentExample.Criteria;
import diao.taotao.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public EUDataGridResult getContentList(int page, int rows, long categoryId) {
        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        // 分页处理
        PageHelper.startPage(page, rows);
        // 创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        List<TbContent> list = contentMapper.selectByExample(example);
        // 取记录的总条数
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        result.setRows(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

}
