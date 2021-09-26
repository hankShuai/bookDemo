package com.demo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

public abstract class BaseDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;
        public int update(String sql, Object...args){
            return  jdbcTemplate.update(sql,args);
    }
    public <T> T queryForOne(Class<T> type,String sql,Object...args){
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(type),args);
    }
    public <T> List<T> queryForList(Class<T> type,String sql,Object...args){
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<T>(type),args);
    }

    public Object queryForSingleValue(String sql, Object...args){
      return jdbcTemplate.queryForObject(sql,Object.class,args);
    }
}
