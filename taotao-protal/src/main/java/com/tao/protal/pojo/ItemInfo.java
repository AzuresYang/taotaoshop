package com.tao.protal.pojo;

import com.tao.pojo.TbItem;

/**
 * Created by 28029 on 2018/4/8.
 */
public class ItemInfo extends TbItem {
    public String[] getImages() {
        String image = getImage();
        if (image != null) {
            String[] images = image.split(",");
            return images;
        }
        return null;
    }

}
