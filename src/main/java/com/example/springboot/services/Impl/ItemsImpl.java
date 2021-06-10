package com.example.springboot.services.Impl;

import com.example.springboot.dao.ItemsMapper;
import com.example.springboot.entity.Items;
import com.example.springboot.services.ItemsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsImpl  implements ItemsServices {

    @Autowired
    private ItemsMapper itemsMapper;


    @Override
    public List<Items> ItemsAll() {
        return itemsMapper.ItemsAll();
    }
}
