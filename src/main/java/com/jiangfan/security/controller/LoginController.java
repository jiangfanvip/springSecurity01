package com.jiangfan.security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * ClassName: LoginController
 * Description:
 * date: 2020-8-21 10:05
 *
 * @author LENOVO
 * @since JDK 1.8
 */
@RestController
public class LoginController {
    @RequestMapping(value = "/login-success", produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess() {
        return "登录成功";
    }

    @RequestMapping(value = "/user/resources01", produces = {"text/plain;charset=UTF-8"})
    public String getResources01() {
        return "访问资源01";
    }

    @RequestMapping(value = "/all/resources02", produces = {"text/plain;charset=UTF-8"})
    public String getResources02() {
        return "访问资源02";
    }
    @RequestMapping(value = "/user/p1", produces = {"text/plain;charset=UTF-8"})
    public String userp1() {
        return "..............userp1..........";
    }


    @RequestMapping(value = "/user/p2", produces = {"text/plain;charset=UTF-8"})
    public String userp2() {
        return "................userp2........";
    }
}
