package diao.taotao.service;

import diao.taotao.common.EUDataGridResult;
import diao.taotao.common.util.TaotaoResult;
import diao.taotao.pojo.TbItemParam;

public interface ItemParamService {
    public TaotaoResult getItemParamByCid(long cid);

    public TaotaoResult insertItemParam(TbItemParam itemParam);

    public TaotaoResult insertItemParamItem(Long itemId, String itemParam);

    public String getItemParamByItemId(Long itemId);
    
    public EUDataGridResult getItemParamList(int page, int rows);
    
}
