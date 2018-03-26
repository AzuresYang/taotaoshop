package com.tao.controller;

import com.tao.service.base.PictureService;
import com.tao.utils.JsonUtils;
import com.tao.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 28029 on 2018/3/26.
 */
@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile)
    {
        Map result = null;
        try {
            result = pictureService.uploadPic(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("访问IP:"+ WebUtil.getIpAddr(request));
        System.out.println("result:"+result.toString());
        String str = JsonUtils.objectToJson(result);
        return str;
    }


}
