/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.test.dao;

import com.samirhasanov.spring.data.config.AppConfig;
import com.samirhasanov.spring.data.config.DbConfig;
import com.samirhasanov.spring.data.dao.IUserDao;
import com.samirhasanov.spring.data.domain.User;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Hasanov (Asus)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = {AppConfig.class, DbConfig.class})
public class TestJdbcTemplateUserDao {
    
    @Autowired
    private IUserDao userDao;
    
    @Before
    public void setUp() {
        assertNotNull(userDao);
    }
    
    @Test
    public void testUserPositive() {
        User user = userDao.findById(1L);
        
        assertNotNull(user);
    }
    
    @Test
    public void testCountUsersPositive() {
        int count = userDao.countUsers();
        
        assertEquals(count, 2);
    }
    
    @Test
    public void testGetAllUsersPositive() {
        List<User> users = userDao.getAll();
        
        assertEquals(users.size(), 2);
    }
    
    @Test
    public void testSaveUserPositive() {
        boolean isSaved = userDao.saveUser(new User(Long.MIN_VALUE, "Bayram Gafarli", 27));
        assertTrue(isSaved);
        
        Set<User> users = userDao.getUsersByFullname("Bayram", false);
        assertEquals(users.size(), 1);
    }
    
    @Test
    public void testUpdateUserPositive() {
        boolean isUpdated = userDao.updateUser(new User(2L, "Ziyatay Akbarli", 27));
        assertTrue(isUpdated);
        
        Set<User> users = userDao.getUsersByFullname("Ziya", false);
        assertEquals(users.size(), 1);
    }
    
    @Test
    public void testDeleteUserPositive() {
        boolean isDeleted = userDao.deleteUser(1L);
        assertTrue(isDeleted);
        
        Set<User> users = userDao.getUsersByFullname("Samir", false);
        assertEquals(users.size(), 0);
    }
}
