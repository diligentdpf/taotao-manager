package diao.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import diao.taotao.common.util.EUTreeNode;
import diao.taotao.common.util.TaotaoResult;
import diao.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCatList(
            @RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
        return list;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId, String name) {
        TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }



    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(
            @RequestParam(value = "parentId", required = false) Long parentId, Long id) {
        TaotaoResult result = null;
        parentId = (parentId == null) ? 0 : parentId;
        id = (id == null) ? 0 : id;
        result = contentCategoryService.deleteContentCategory(parentId, id);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(long id, String name) {
        TaotaoResult result = contentCategoryService.updateContentCategory(id, name);
        return result;
    }
}
