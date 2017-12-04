/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.service;

import com.samirhasanov.spring.data.domain.User;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Hasanov (Asus)
 */
public interface IUserService {
    public User findById(Long id);
    public int countUsers();
    public List<User> getAll();
    public boolean saveUser(User user);
    public Set<User> getUsersByFullname(String fullname, boolean exactMatch);
    public boolean updateUser(User user);
    public boolean deleteUser(Long userId);
    public void doSomeComplexUserManipulation(User user) throws Exception;
}
