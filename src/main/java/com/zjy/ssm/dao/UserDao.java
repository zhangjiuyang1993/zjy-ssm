/**
 * FileName: UserDao
 * Author:   zhangjiuyang
 * Date:     2018/8/3 13:00
 * Description:  用户接口mapper
 */
package com.zjy.ssm.dao;

import com.zjy.ssm.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /*
     * 登录
     * @author zhangjiuyang
     * @date 2018/8/3 13:00
     * @param [user]
     * @return com.zjy.ssm.entity.User
     */
    public User login(User user);

    /*
     * 查找用户列表
     * @author zhangjiuyang
     * @date 2018/8/3 13:01
     * @param [map]
     * @return java.util.List<com.zjy.ssm.entity.User>
     */
    public List<User> findUsers(Map<String, Object> map);

    /*
     * 获取用户数据
     * @author zhangjiuyang
     * @date 2018/8/3 13:02
     * @param [map]
     * @return java.lang.Long
     */
    public Long getTotalUser(Map<String, Object> map);

    /*
     * 更新用户
     * @author zhangjiuyang
     * @date 2018/8/3 13:02
     * @param [user]
     * @return int
     */
    public int updateUser(User user);

    /*
     * 添加用户
     * @author zhangjiuyang
     * @date 2018/8/3 13:03
     * @param [user]
     * @return int
     */
    public int insertUser(User user);

    /*
     * 根据id删除用户
     * @author zhangjiuyang
     * @date 2018/8/3 13:03
     * @param [id]
     * @return int
     */
    public int deleteUser(Integer id);
}
