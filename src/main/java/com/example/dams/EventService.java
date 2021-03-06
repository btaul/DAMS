package com.example.dams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class EventService {

    @Autowired
    JdbcTemplate template;

    public List<Event> findAll(){
        String sql = "select * from events";
        RowMapper<Event> rm = new RowMapper<Event>() {
            @Override
            public Event mapRow(ResultSet resultSet, int i) throws SQLException {
                Event event = new Event(resultSet.getLong("id"),
                        resultSet.getString("status"),
                        resultSet.getInt("zip"),
                        resultSet.getString("address"),
                        resultSet.getString("start"),
                        resultSet.getString("end"),
                        resultSet.getString("event"));
                return event;
            }
        };
        return template.query(sql, rm);
    }
}
