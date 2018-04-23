package com.tao.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.tao.entity.EUTreeNode;
import com.tao.entity.ResponseResult;
import com.tao.service.base.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 28029 on 2018/4/3.
 */
@Controller
@RequestMapping("/content/category")
public class ItemCategoryController {
   @Autowired
    private ContentCategoryService contentCategoryService;

   @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCatList(
           @RequestParam(value="id",defaultValue = "0") long parentId)
   {
       List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
       return list;
   }
   @RequestMapping("/create")
   @ResponseBody
    public ResponseResult createContentCategory(Long parentId, String name)
   {
       ResponseResult result = contentCategoryService.insertCategory(parentId,name);
       return result;
   }

    @RequestMapping("/delete")
    @ResponseBody
    public ResponseResult deleteContentCategory(@RequestParam long id)
    {
        return contentCategoryService.deleteCategory(id);
    }
    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult updateContentCategory(@RequestParam long id ,@RequestParam String name)
    {
        ResponseResult result = contentCategoryService.updateCategory(id, name);
        return result;
    }


}
