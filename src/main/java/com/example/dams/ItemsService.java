package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {

    @Autowired
    JdbcTemplate template;

    public List<Items> findAll(){
        String sql = "select * from items";
        RowMapper<Items> rm = (resultSet, i) -> new Items(resultSet.getLong("iditems"),
                resultSet.getLong("eventid"),
                resultSet.getString("item"));
        return template.query(sql, rm);
    }
}


