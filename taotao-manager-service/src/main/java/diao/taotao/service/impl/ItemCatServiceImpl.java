package diao.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diao.taotao.mapper.TbItemCatMapper;
import diao.taotao.pojo.TbItemCat;
import diao.taotao.pojo.TbItemCatExample;
import diao.taotao.pojo.TbItemCatExample.Criteria;
import diao.taotao.service.TbItemCatService;
@Service
public class ItemCatServiceImpl implements TbItemCatService {
    @Autowired
    TbItemCatMapper itemCatMapper;
    
    @Override
    public List<TbItemCat> getItemCatList(Long parentId) throws Exception {
        
        TbItemCatExample example = new TbItemCatExample();
        //设置查询条件
        Criteria criteria = example.createCriteria();
        //根据parentid查询子节点
        criteria.andParentIdEqualTo(parentId);
        //返回子节点列表
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        return list;
    }

}
