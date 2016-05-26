package diao.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import diao.taotao.common.util.TaotaoResult;
import diao.taotao.pojo.TbItem;
import diao.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        TbItem tbItem = itemService.getTBItemById(itemId);
        return tbItem;
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult saveItem(TbItem item, String desc, String itemParams) throws Exception {
        // 添加商品信息
        if (itemService.saveItem(item, desc, itemParams) > 0) {
            return TaotaoResult.ok();
        }
        return TaotaoResult.build(206, "error");
    }
}
