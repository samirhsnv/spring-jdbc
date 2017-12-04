/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.service;

import com.samirhasanov.spring.data.dao.IUserDao;
import com.samirhasanov.spring.data.domain.User;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hasanov (Asus)
 */
@Service
@Transactional
public class UserService implements IUserService {
    private final IUserDao userDao;
    
    @Autowired
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return this.userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public int countUsers() {
        return this.userDao.countUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return this.userDao.getAll();
    }

    @Override
    public boolean saveUser(User user) {
        return this.userDao.saveUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<User> getUsersByFullname(String fullname, boolean exactMatch) {
        return this.userDao.getUsersByFullname(fullname, exactMatch);
    }

    @Override
    public boolean updateUser(User user) {
        return this.userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(Long userId) {
        return this.userDao.deleteUser(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doSomeComplexUserManipulation(User user) throws Exception {
        this.userDao.saveUser(user);
        User createdUser = this.userDao.getUsersByFullname(user.getFullname(), true).iterator().next();
        createdUser.setFullname("Rokki Balboa");
        
        boolean isUpdated = this.userDao.updateUser(createdUser);
        
        if(!isUpdated) {
            throw new Exception("Cannot update user");
        }
    }
    
}
