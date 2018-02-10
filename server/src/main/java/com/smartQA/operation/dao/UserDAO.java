package com.smartQA.operation.dao;

import com.smartQA.operation.dataobject.User;

import java.util.List;

public interface UserDAO {
    public boolean addUser(User user);
    public User getUser(String nickName);
    public boolean deleteUser(String nickName);
    public List<User> getUserList();
}
