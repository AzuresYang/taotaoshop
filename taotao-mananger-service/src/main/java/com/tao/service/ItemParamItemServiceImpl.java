package com.tao.service;

import com.tao.mapper.TbItemParamItemMapper;
import com.tao.pojo.TbItemParamItem;
import com.tao.pojo.TbItemParamItemExample;
import com.tao.service.base.ItemParamItemService;
import com.tao.utils.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by 28029 on 2018/3/29.
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
    private static Logger logger = LogManager.getLogger();
    @Autowired
    public TbItemParamItemMapper tbItemParamItemMapper;
    @Override
    public String getItemParamByItemId(long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria a = example.createCriteria();
        a.andItemIdEqualTo(itemId);
        example.or(a);

        logger.info("itemId:"+itemId);
       List<TbItemParamItem> results =  tbItemParamItemMapper.selectByExampleWithBLOBs(example);
       if(results == null || results.size() <=0)
       {
           logger.info("cannot find paramItem");
           return "";
       }
        TbItemParamItem paramItem = results.get(0);
        String paramData = paramItem.getParamData();
        System.out.println("paramData:\n"+paramData);
        System.out.println();
       //把规格参数json数据转换成java对象
        List<Map> paramList = (List<Map>)JsonUtils.jsonToCollectionList(paramData, Map.class);

        StringBuffer sb = new StringBuffer();
        //sb.append("<div>");
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for (Map map : paramList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> params = (List<Map>) map.get("params");
            for (Map map2 : params) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
                sb.append("            <td>"+map2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        //sb.append("</div>");
        return sb.toString();

    }
    public static void main(String[] args)
    {
        ApplicationContext appliationContext =
                new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext-*.xml");
        ItemParamItemServiceImpl service = new ItemParamItemServiceImpl();
        service.tbItemParamItemMapper = (TbItemParamItemMapper)appliationContext.getBean("tbItemParamItemMapper");
        service.getItemParamByItemId((long)48);

    }
}
