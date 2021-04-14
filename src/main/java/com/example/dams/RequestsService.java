package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class RequestsService {

    @Autowired
    JdbcTemplate template;

    public List<Requests> findAll(){
        String sql = "select * from requests";
        RowMapper<Requests> rm = new RowMapper<Requests>() {
            @Override
            public Requests mapRow(ResultSet resultSet, int i) throws SQLException {
                Requests requests = new Requests(resultSet.getLong("requestID"),
                        resultSet.getString("eventID"),
                        resultSet.getString("requester"),
                        resultSet.getString("status"),
                        resultSet.getString("item"),
                        resultSet.getInt("volume"),
                        resultSet.getInt("remaining"),
                        resultSet.getInt("zip"),
                        resultSet.getString("expire"));
                return requests;
            }
        };
        return template.query(sql, rm);
    }
}
