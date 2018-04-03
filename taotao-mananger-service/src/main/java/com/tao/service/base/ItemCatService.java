package com.tao.service.base;

import com.tao.pojo.TbItemCat;

import java.util.List;

/**
 * Created by 28029 on 2018/3/23.
 */
public interface ItemCatService {
    public List<TbItemCat> getItemCatList(Long parentId) throws Exception;
}
