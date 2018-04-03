package com.tao.service.base;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by 28029 on 2018/3/25.
 */
public interface PictureService {

    public Map uploadPic(MultipartFile uploadFile) throws IOException;
}
