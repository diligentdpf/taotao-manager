package diao.taotao.service;

import diao.taotao.common.EUDataGridResult;

public interface ContentService {
    public EUDataGridResult  getContentList(int page, int rows, long categoryId);
}
