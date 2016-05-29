package diao.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import diao.taotao.common.EUDataGridResult;
import diao.taotao.common.util.TaotaoResult;
import diao.taotao.pojo.TbContent;
import diao.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;

	/**
	 * 内容列表 2016年5月29日13:27
	 */
	@RequestMapping("/query/list")
	@ResponseBody
	// 如果id为null是使用默认值，也就是parentid为0的分类列表
	public EUDataGridResult contentList(int page, int rows, int categoryId) throws Exception {
		EUDataGridResult result;
		result = contentService.getContentList(page, rows, categoryId);
		return result;
	}

	/**
	 * 内容增加 2016年5月29日13:23
	 */
	@RequestMapping("/save")
	@ResponseBody
	// 如果id为null是使用默认值，也就是parentid为0的分类列表
	public TaotaoResult contentAdd(TbContent content) throws Exception {
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}
	
	/**
	 * 内容删除 2016年5月29日13:23   content/delete 404 (Not Found)
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	// 如果id为null是使用默认值，也就是parentid为0的分类列表
	public TaotaoResult contentDel(String ids) throws Exception {
		TaotaoResult result = contentService.delContent(ids);
		return result;
	}
}
