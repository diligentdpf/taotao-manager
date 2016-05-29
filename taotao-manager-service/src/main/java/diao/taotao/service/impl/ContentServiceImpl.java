package diao.taotao.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import diao.taotao.common.EUDataGridResult;
import diao.taotao.common.util.TaotaoResult;
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

	@Override
	public TaotaoResult insertContent(TbContent content) {
		// 补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult delContent(String ids) throws Exception {
		List<Long> list=new ArrayList<>();
		for (String id:Arrays.asList(ids.split(","))) {
			list.add(Long.valueOf(id));
		}
		TbContentExample example=new TbContentExample();
		Criteria create=example.createCriteria();
		create.andIdIn(list);
		int one=contentMapper.deleteByExample(example);
		if(one<=0){
			throw new Exception("数据已经被删除！");
		}
		return TaotaoResult.ok();
	}

}
