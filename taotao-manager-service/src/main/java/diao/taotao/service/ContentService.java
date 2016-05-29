package diao.taotao.service;

import diao.taotao.common.EUDataGridResult;
import diao.taotao.common.util.TaotaoResult;
import diao.taotao.pojo.TbContent;

public interface ContentService {
    public EUDataGridResult  getContentList(int page, int rows, long categoryId);

	public TaotaoResult insertContent(TbContent content);

	public TaotaoResult delContent(String ids) throws Exception;
}
