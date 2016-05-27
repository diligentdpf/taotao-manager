package diao.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import diao.taotao.common.EUDataGridResult;
import diao.taotao.service.ContentService;

@Controller
@RequestMapping("/content/query")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/list")
    @ResponseBody
    // 如果id为null是使用默认值，也就是parentid为0的分类列表
    public EUDataGridResult contentList(int page, int rows, int categoryId) throws Exception {
        EUDataGridResult result;
        result=contentService.getContentList(page, rows, categoryId);
        return result;
    }
}
