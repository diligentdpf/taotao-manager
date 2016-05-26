package diao.taotao.service;

import java.util.List;

import diao.taotao.common.util.EUTreeNode;
import diao.taotao.common.util.TaotaoResult;

public interface ContentCategoryService {
    public List<EUTreeNode> getCategoryList(long parentId);

    public TaotaoResult insertContentCategory(long parentId, String name);

    public TaotaoResult deleteContentCategory(long parentId, long id);

    public TaotaoResult updateContentCategory(long id, String name);
}
