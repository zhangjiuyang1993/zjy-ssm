package com.zjy.ssm.service.impl;

import com.zjy.ssm.dao.UserDao;
import com.zjy.ssm.domain.PageBean;
import com.zjy.ssm.entity.User;
import com.zjy.ssm.service.UserService;
import com.zjy.ssm.util.AntiXssUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjiuyang
 * @create 2018/8/3
 * @since 1.0.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public List<User> findUser(Map<String, Object> map) {
        return userDao.findUsers(map);
    }

    @Override
    public Long getTotalUser(Map<String, Object> map) {
        return userDao.getTotalUser(map);
    }

    @Override
    public int addUser(User user) {
        if (user.getUserName() == null || user.getPassword() == null || getTotalUser(null) > 90) {
            return 0;
        }
        user.setUserName(AntiXssUtil.replaceHtmlCode(user.getUserName()));
        return userDao.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        //防止有人胡乱修改导致其他人无法正常登陆
        if ("admin".equals(user.getUserName())) {
            return 0;
        }
        user.setUserName(AntiXssUtil.replaceHtmlCode(user.getUserName()));
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(Integer id) {
        //防止有人胡乱修改导致其他人无法正常登陆
        if (2 == id) {
            return 0;
        }
        return userDao.deleteUser(id);
    }

    @Override
    public PageBean<User> findAllUserWithPage(int pageNum, int pageSize) {
        //在这里就要将PageBean中的数据创建好，然后将该对象传回去
        //先要从数据库中获取所有用户的数据量有多少，获得totalRecord
        List<User> allUser = userDao.findAllUser();
        int totalRecord = allUser.size();

        //有了三个初始数据，就能够创建PageBean对象
        PageBean pb = new PageBean(pageNum, pageSize, totalRecord);

        //获取PageBean对象中startIndex
        int startIndex = pb.getStartIndex();

        //有startIndex和pageSize，就可以拿到每页的数据
        pb.setList(userDao.findAll(startIndex, pageSize));
        return pb;
    }
}
