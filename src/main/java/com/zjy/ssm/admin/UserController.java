package com.zjy.ssm.admin;

import com.zjy.ssm.common.Result;
import com.zjy.ssm.common.ResultGenerator;
import com.zjy.ssm.entity.PageBean;
import com.zjy.ssm.entity.User;
import com.zjy.ssm.service.UserService;
import com.zjy.ssm.util.MD5Util;
import com.zjy.ssm.util.ResponseUtil;
import com.zjy.ssm.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjiuyang
 * @create 2018/8/3
 * @since 1.0.0
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;
    private static final Logger log = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/cookie", method = RequestMethod.POST)
    @ResponseBody
    public Result login(User user) {
        try {
            String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
            user.setPassword(MD5pwd);
        } catch (Exception e) {
            user.setPassword("");
        }
        User resultUser = userService.login(user);
        log.info("request: user/login, user: " + user.toString());
        if (resultUser == null) {
            return ResultGenerator.genFailResult("请认真核对账号，密码");
        } else {
            resultUser.setPassword("PASSWORD");
            Map data = new HashMap();
            data.put("currentUser", resultUser);
            return ResultGenerator.genSuccessResult(data);
        }
    }

    @RequestMapping(value = "/datagrid", method = RequestMethod.POST)
    public String list(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            User s_user, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<>();
        map.put("userName", StringUtil.formatLike(s_user.getUserName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<User> userList = userService.findUser(map);
        Long total = userService.getTotalUser(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(userList);
        result.put("rows", jsonArray);
        result.put("total", total);
        log.info("request: user/list, map: " + map.toString());
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody User user) throws Exception {
        int resultTotal = 0;
        String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
        user.setPassword(MD5pwd);
        resultTotal = userService.addUser(user);
        if (resultTotal > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("FAIL");
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public Result update(@RequestBody User user) throws Exception {
        String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
        user.setPassword(MD5pwd);
        int resultTotal = userService.updateUser(user);
        log.info("request: user/update, user: " + user.toString());
        if (resultTotal > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("FAIL");
        }
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(@PathVariable(value = "ids") String ids) throws Exception {
        if (ids.length() > 20) {
            return ResultGenerator.genFailResult("ERROR");
        }
        String idsStr[] = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            userService.deleteUser(Integer.valueOf(idsStr[i]));
        }
        log.info("request: user/delete, ids: " + ids);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/page")
    public String findUserByPage(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        //获取当前是第几页
        int pageNum = Integer.valueOf(request.getParameter("pageNum"));
        //每页显示的记录数
        int pageSize = 5;
        //获取一个PageBean对象，pb中包含了所有分页所需要的数据
        //PageBean pb = userService.findAllUserWithPage(pageNum, pageSize);
        return "/page/main";
    }
}
