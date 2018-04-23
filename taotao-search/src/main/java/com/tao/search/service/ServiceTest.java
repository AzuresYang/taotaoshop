package com.tao.search.service;

import com.tao.search.mapper.ItemMapper;
import com.tao.search.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 28029 on 2018/4/7.
 */
@Service
public class ServiceTest {
    @Autowired
    private ItemMapper itemMapper;
    public List<Item> getList(){
        return itemMapper.getItemList();
    }
}
