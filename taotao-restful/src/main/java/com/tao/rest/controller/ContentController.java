package com.tao.rest.controller;

import com.tao.entity.ResponseResult;
import com.tao.pojo.TbContent;
import com.tao.rest.service.base.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 28029 on 2018/4/4.
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public ResponseResult getContentList(@PathVariable Long contentCategoryId)
    {
        List<TbContent> list = contentService.getContentList(contentCategoryId);
        return ResponseResult.ok(list);
    }





}
