package com.tao.service.base;

import com.tao.entity.EUTreeNode;
import com.tao.entity.ResponseResult;

import java.util.List;

/**
 * Created by 28029 on 2018/4/3.
 */
public interface ContentCategoryService {
    public List<EUTreeNode> getCategoryList(long parentId);
    public ResponseResult insertCategory(long parentId, String name);
    public ResponseResult deleteCategory(long id);
    public ResponseResult updateCategory(long id, String name);
}
