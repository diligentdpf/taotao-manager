package diao.taotao.service;

import java.util.List;

import diao.taotao.pojo.TbItemCat;

public interface TbItemCatService {
    List<TbItemCat> getItemCatList(Long parentId) throws Exception;
}
