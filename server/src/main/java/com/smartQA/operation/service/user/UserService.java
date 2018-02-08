package com.smartQA.operation.service.user;

import com.smartQA.operation.dataobject.User;

import java.util.List;

public interface UserService {
    public boolean addUser(String nickName, String remark, Integer gender, String lang, String city, String province, String country, String avatarUrl, String joinCourse);
    public User getUser(String nickName);
    public boolean deleteUser(String nickName);
    public List<User> getUserList();
}
