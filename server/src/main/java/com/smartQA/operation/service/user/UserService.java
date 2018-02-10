package com.smartQA.operation.service.user;

import com.smartQA.operation.dataobject.User;

import java.util.List;

public interface UserService {
    public boolean addUser(User user);
    public User getUser(String nickName);
    public boolean deleteUser(String nickName);
    public List<User> getUserList();
}
