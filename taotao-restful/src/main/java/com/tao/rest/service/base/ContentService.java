package com.tao.rest.service.base;

import com.tao.entity.EUDataGridResult;
import com.tao.entity.ResponseResult;
import com.tao.pojo.TbContent;

import java.util.List;

/**
 * Created by 28029 on 2018/4/4.
 */
public interface ContentService {
    public List<TbContent> getContentList(long categoryId);
    public ResponseResult saveContent(TbContent content);
    public ResponseResult updateContent(TbContent content);
    public ResponseResult deleteContent(long contentId);
}
