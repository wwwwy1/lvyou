package cn.sxwl.lvyou.common.controller;

import cn.sxwl.lvyou.common.entity.ResponseEntity;
import cn.sxwl.lvyou.common.entity.User;
import cn.sxwl.lvyou.common.service.AdminService;
import cn.sxwl.lvyou.common.util.MD5;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.net.HttpCookie;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ResponseEntity login(String username , String password) {
        System.out.println("------------------123123------------------");
        password=MD5.md5(password);
        ResponseEntity re=new ResponseEntity();
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
             subject.login(token);
            re.setStatus(1);
            re.setMsg("登录成功");
        }catch (UnknownAccountException e){
            re.setStatus(2);
            re.setMsg("用户错误");
        }catch (IncorrectCredentialsException e){
            re.setStatus(3);
            re.setMsg("密码错误");
        }
        return re;
    }
    @RequestMapping(value ="/admin/logout",method = RequestMethod.POST)
    public ResponseEntity logout(String token){
       return adminService.logout(token);
    }


}
