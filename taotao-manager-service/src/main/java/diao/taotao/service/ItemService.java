package diao.taotao.service;

import diao.taotao.common.EUDataGridResult;
import diao.taotao.pojo.TbItem;

public interface ItemService {
    TbItem getTBItemById(long itemId);

    EUDataGridResult getItemList(int page, int rows);

    int saveItem(TbItem item, String desc,String itemParam) throws Exception;
}
