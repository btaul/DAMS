package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;


public class ItemsCustomDetailsService implements ItemsDetailsService{
    @Autowired
    ItemsRepository repo;


    @Override
    public ItemsDetails loadItemByID(Long var1) throws Exception {
        Items item = repo.findByItemID(var1);
        if (item==null){
            throw new Exception("Item ID not found");
        }
        return new com.example.dams.ItemsCustomDetails(item);
    }
}
