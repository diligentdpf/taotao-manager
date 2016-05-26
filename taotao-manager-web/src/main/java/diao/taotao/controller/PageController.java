package diao.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import diao.taotao.common.EUDataGridResult;
import diao.taotao.service.ItemService;

@Controller
public class PageController {

    @Autowired
    ItemService itemService;

    /**
     * 打开主页
     */
    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    /**
     * 展示其他页面
     * 
     * @param page,page为item-add，item-param-list等这种页面的名称，这里只是作页面跳转
     * @return
     */
    @RequestMapping("/{page}")
    public String showpage(@PathVariable String page) {
        return page;
    }

    /**
     * 商品列表
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
}
