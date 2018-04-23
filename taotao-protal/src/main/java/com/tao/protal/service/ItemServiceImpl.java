package com.tao.protal.service;

import com.sun.org.apache.regexp.internal.RE;
import com.tao.entity.ResponseResult;
import com.tao.pojo.TbItemDesc;
import com.tao.pojo.TbItemParam;
import com.tao.pojo.TbItemParamItem;
import com.tao.protal.pojo.ItemInfo;
import com.tao.protal.service.base.ItemService;
import com.tao.utils.HttpClientUtil;
import com.tao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 28029 on 2018/4/8.
 */
@Service
public class ItemServiceImpl implements ItemService{

    private Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${ITME_INFO_URL}")
    private String ITEM_INFO_URL;

    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;

    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;

    @Override
    public ItemInfo getItemById(Long itemId) {
        logger.info("protal get item:"+itemId);
        try{
            String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
            logger.info("protal json:"+json);
            if(!StringUtils.isBlank(json))
            {
                ResponseResult responseResult = ResponseResult.formatToPojo(json,ItemInfo.class);

                if(responseResult.getStatus() == 200)
                {
                    ItemInfo item = (ItemInfo) responseResult.getData();
                    return item;
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemDescById(Long itemId) {
        try{
            String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_DESC_URL+itemId);
            if(!StringUtils.isBlank(json))
            {
                ResponseResult responseResult = ResponseResult.formatToPojo(json,TbItemDesc.class);
                if(responseResult.getStatus() == 200)
                {
                    TbItemDesc item = (TbItemDesc) responseResult.getData();
                    String result = item.getItemDesc();
                    //获取商品描述信息
                    return result;
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemParam(Long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            //把json转换成java对象
            ResponseResult responseResult = (ResponseResult) JsonUtils.jsonToPojo(json, ResponseResult.class);
            if (responseResult.getStatus() == 200) {
                TbItemParamItem itemParamItem = (TbItemParamItem) responseResult.getData();
                String paramData = itemParamItem.getParamData();
                //生成html
                // 把规格参数json数据转换成java对象
                List<Map> jsonList = JsonUtils.jsonToCollectionList(paramData, Map.class);
                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
                sb.append("    <tbody>\n");
                for (Map m1 : jsonList) {
                    sb.append("        <tr>\n");
                    sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
                    sb.append("        </tr>\n");
                    List<Map> list2 = (List<Map>) m1.get("params");
                    for (Map m2 : list2) {
                        sb.append("        <tr>\n");
                        sb.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
                        sb.append("            <td>" + m2.get("v") + "</td>\n");
                        sb.append("        </tr>\n");
                    }
                }
                sb.append("    </tbody>\n");
                sb.append("</table>");
                //返回html片段
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
