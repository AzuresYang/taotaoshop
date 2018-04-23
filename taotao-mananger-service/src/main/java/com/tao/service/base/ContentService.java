package com.tao.service.base;

import com.tao.entity.EUDataGridResult;
import com.tao.entity.ResponseResult;
import com.tao.pojo.TbContent;

/**
 * Created by 28029 on 2018/4/3.
 */
public interface ContentService {
    public EUDataGridResult getContentList(long categoryId, int page, int rows);
    public ResponseResult saveContent(TbContent content);
    public ResponseResult updateContent(TbContent content);
    public ResponseResult deleteContent(long contentId);
}
