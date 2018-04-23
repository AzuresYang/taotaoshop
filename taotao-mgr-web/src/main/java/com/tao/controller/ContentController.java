package com.tao.controller;

import com.tao.entity.EUDataGridResult;
import com.tao.entity.EasyUIResult;
import com.tao.entity.ResponseResult;
import com.tao.pojo.TbContent;
import com.tao.service.base.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 28029 on 2018/4/3.
 */
@Controller

public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EUDataGridResult getContentList(@RequestParam long categoryId,
                                           @RequestParam int page, @RequestParam int rows)
    {
        EUDataGridResult result = contentService.getContentList(categoryId,page, rows);
        return result;
    }
    @RequestMapping("/content/save")
    @ResponseBody
    public ResponseResult saveContent(TbContent content)
    {
        ResponseResult result = contentService.saveContent(content);
        return result;
    }

    @RequestMapping("rest/content/edit")
    @ResponseBody
    public ResponseResult updateContent(TbContent content)
    {
        ResponseResult result = contentService.updateContent(content);
        return result;
    }

    @RequestMapping("/content/delete")
    @ResponseBody
    public ResponseResult deleteContent(@RequestParam long ids)
    {
        ResponseResult result = contentService.deleteContent(ids);
        return result;
    }

}
