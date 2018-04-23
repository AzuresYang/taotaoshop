package com.tao.service;

import com.tao.service.base.PictureService;
import com.tao.utils.FtpUtil;
import com.tao.utils.IDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 28029 on 2018/3/26.
 */
@Service
public class PictureServiceImpl implements PictureService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${ftp_addr}")
    private String ftp_addr;
    @Value("${ftp_port}")
    private int ftp_port;
    @Value("${ftp_username}")
    private String ftp_username;
    @Value("${ftp_password}")
    private String ftp_password;
    @Value("${ftp_image_path}")
    private String ftp_image_path;

    public Map uploadPic(MultipartFile uploadFile) throws IOException {
        Map<String,String> resultMap = new HashMap<>();


        String oldName = uploadFile.getOriginalFilename();
        String newName = IDUtil.getImageName()+oldName.substring(oldName.lastIndexOf("."));
        Date date  = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
        String imagePath = sdf.format(date);

        try{
            logger.info("ready to upload file:\n oldName:"+ oldName+"\nnewName:"+newName);
            boolean result = FtpUtil.uploadFile(ftp_addr,ftp_port,ftp_username,ftp_password,ftp_image_path,
                    imagePath,newName,uploadFile.getInputStream());
            if(!result)
            {
                resultMap.put("error","1");
                resultMap.put("message","文件上传失败");
                logger.info("upload failed");
                return resultMap;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("upload succeeded");
        resultMap.put("error","0");
        resultMap.put("message","http://"+ftp_addr+"/"+imagePath+"/"+newName);
        return resultMap;
    }
}
