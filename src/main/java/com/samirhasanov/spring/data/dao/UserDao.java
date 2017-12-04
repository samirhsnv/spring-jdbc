/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.dao;

import com.samirhasanov.spring.data.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hasanov (Asus)
 */
@Repository
public class UserDao implements IUserDao {
    private final UserRowMapper userRowMapper = new UserRowMapper();
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public User findById(Long id) {
        String sql = "select id, fullname, age from users t where t.id = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, id);
    }

    @Override
    public int countUsers() {
        String sql = "select count(*) from users t";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<User> getAll() {
        String sql = "select id, fullname, age from users t";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public boolean saveUser(User user) {
        String sql = "insert into users(fullname, age) values(?,?)";
        return jdbcTemplate.update(sql, user.getFullname(), user.getAge()) == 1;
    }

    @Override
    public Set<User> getUsersByFullname(String fullname, boolean exactMatch) {
        String sql = "select id, fullname, age from users t where ";
        
        if(exactMatch) {
            sql += "t.fullname = ?";
        }
        else {
            sql += "t.fullname like '%'||?||'%'";
        }
        
        return new HashSet<>(jdbcTemplate.query(sql, userRowMapper, fullname));
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "update users set fullname = ?, age = ? where id = ?";
        return jdbcTemplate.update(sql, user.getFullname(), user.getAge(), user.getId()) == 1;
    }

    @Override
    public boolean deleteUser(Long userId) {
        String sql = "delete from users where id = ?";
        return jdbcTemplate.update(sql, userId) == 1;
    }
    
    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return new User(resultSet.getLong("id"), 
                            resultSet.getString("fullname"), 
                            resultSet.getInt("age"));
        }
    }
}
