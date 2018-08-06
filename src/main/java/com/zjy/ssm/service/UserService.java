/**
 * FileName: UserService
 * Author:   zhangjiuyang
 * Date:     2018/8/3 14:17
 * Description:  用户业务逻辑service
 */
package com.zjy.ssm.service;

import com.zjy.ssm.domain.PageBean;
import com.zjy.ssm.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /*
     * 登录
     * @author zhangjiuyang
     * @date 2018/8/3 16:04
     * @param [user]
     * @return com.zjy.ssm.entity.User
     */
    public User login(User user);

    /*
     * 获取用户数据集合
     * @author zhangjiuyang
     * @date 2018/8/3 14:18
     * @param [map]
     * @return java.util.List<com.zjy.ssm.entity.User>
     */
    public List<User> findUser(Map<String, Object> map);

    /*
     * 获取用户数据总数
     * @author zhangjiuyang
     * @date 2018/8/3 14:19
     * @param [map]
     * @return java.lang.Long
     */
    public Long getTotalUser(Map<String, Object> map);

    /*
     * 添加用户
     * @author zhangjiuyang
     * @date 2018/8/3 14:19
     * @param [user]
     * @return int
     */
    public int addUser(User user);

    /*
     * 更新用户
     * @author zhangjiuyang
     * @date 2018/8/3 14:20
     * @param [user]
     * @return int
     */
    public int updateUser(User user);

    /*
     * 根据用户id删除用户
     * @author zhangjiuyang
     * @date 2018/8/3 14:20
     * @param [id]
     * @return int
     */
    public int deleteUser(Integer id);

    /*
     * 分页查询用户数据
     * @author zhangjiuyang
     * @date 2018/8/6 15:15
     * @param [pageNum, pageSize]
     * @return com.zjy.ssm.domain.PageBean<com.zjy.ssm.entity.User>
     */
    public PageBean<User> findAllUserWithPage(int pageNum, int pageSize);
}
