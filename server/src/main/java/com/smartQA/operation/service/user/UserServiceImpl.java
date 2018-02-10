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

    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }
    public User getUser(String nickName) {
        User user= userDAO.getUser(nickName);
        return user;
    }
    public boolean deleteUser(String nickName) {
        return userDAO.deleteUser(nickName);
    }

    public List<User> getUserList() {
        List<User> users=userDAO.getUserList();
        return users;
    }
}