package com.smartQA.operation.service.user;

import com.smartQA.operation.dao.UserDAO;
import com.smartQA.operation.dataobject.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述:
 * 用户服务接口实现类
 *
 * @outhor didonglin
 * @create 2018-02-08 16:56
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDAO userDAO;

    public boolean addUser(String nickName, String remark, Integer gender, String lang, String city, String province, String country, String avatarUrl, String joinCourse) {
        userDAO.addUser(nickName, remark, gender, lang, city, province, country, avatarUrl, joinCourse);
        return false;
    }
    public User getUser(String nickName) {
        User user= userDAO.getUser(nickName);
        return user;
    }
    public boolean deleteUser(String nickName) {
        userDAO.deleteUser(nickName);
        return false;
    }

    public List<User> getUserList() {
        List<User> users=userDAO.getUserList();
        return users;
    }
}