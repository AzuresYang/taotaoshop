package com.tao.protal.service;

import com.tao.entity.ResponseResult;
import com.tao.protal.pojo.Item;
import com.tao.protal.pojo.SearchResult;
import com.tao.protal.service.base.SearchService;
import com.tao.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 28029 on 2018/4/8.
 */
@Service
public class SearchServiceImpl implements SearchService{

    public static void main(String[] args)
    {
        SearchServiceImpl service = new SearchServiceImpl();
        SearchResult result = service.search("金立",1);
        if(result == null)
        {
            System.out.println("resutl is null");
        }
        else
        {
            List<Item> list= result.getItemList();
            for(Item item: list)
            {
                System.out.println(item.toString());
            }
        }
    }
    private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
    @Value("${SEARCH_BASE_URL}")
    private  String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String queryString, int page) {

        logger.info("protal search query:"+queryString);
        logger.info("protal search query url:"+SEARCH_BASE_URL);
        //调用search的服务
        Map<String ,String > param = new HashMap<>();
        param.put("q",queryString);
        param.put("page",page+"");

        try{
            String json = HttpClientUtil.doGet(SEARCH_BASE_URL,param);

            logger.info("query result:\n"+json);
            //字符窗转化为java对象
            ResponseResult responseResult = ResponseResult.formatToPojo(json,SearchResult.class);

            if(responseResult.getStatus() == 200)
            {
                SearchResult result = (SearchResult) responseResult.getData();
                return result;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
