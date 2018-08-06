package com.zjy.ssm.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author zhangjiuyang
 * @create 2018/8/3
 * @since 1.0.0
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
