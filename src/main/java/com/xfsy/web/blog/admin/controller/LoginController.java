package com.xfsy.web.blog.admin.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class : LoginController
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/20 0020
 * Time  : 07:28
 */
@Controller
public class LoginController {

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login")
    public String login() {
        return "admin/login";
    }

    /**
     * 登录实现
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "login-impl")
    @ResponseBody
    public int loginImpl(HttpServletRequest request, HttpSession session) {
        String token = request.getParameter("token");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        //String pwd = "xfsy" + formatter.format(new Date());
        String pwd = "xfsy2017!!!!!!!"; // 临时密码
        if (token.equals(pwd)) {
            session.setAttribute("token", pwd);
            //Cookie
            Cookie cookie = new Cookie("token", pwd);
            cookie.setPath("/");
            cookie.setDomain(".fengwenyi.com");
            cookie.setMaxAge(60 * 60 * 24 * 365 * 10);

            return 1;
        } else {
            //
            return 0;
        }
    }

    /**
     * 退出登录
     * @param session
     * @param response
     */
    @RequestMapping(value = "logout")
    public void logout(HttpSession session, HttpServletResponse response) {
        session.removeAttribute("token");
        // 清除Cookie
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
//        String pwd = "xfsy" + formatter.format(new Date());
        String pwd = "xfsy2017!!!!!!!";
        Cookie cookie = new Cookie("token", pwd);
        cookie.setPath("/");
        cookie.setDomain(".fengwenyi.com");
        cookie.setMaxAge(0);
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
