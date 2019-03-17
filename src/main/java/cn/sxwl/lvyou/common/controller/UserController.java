package cn.sxwl.lvyou.common.controller;

import cn.sxwl.lvyou.common.entity.ResponseEntity;
import cn.sxwl.lvyou.common.entity.User;
import cn.sxwl.lvyou.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/admin/getUsers",method = RequestMethod.GET)
    public ResponseEntity selectAll(){
        ResponseEntity list= userService.selectPaging(1);
        return list;
    }
    @RequestMapping(value = "/admin/userPaging/{pageNum}" ,method = RequestMethod.GET)
    public ResponseEntity userPaging(@PathVariable Integer pageNum){
        ResponseEntity list= userService.selectPaging(pageNum);
        return list;
    }
    @RequestMapping(value ="/admin/insertUser",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public ResponseEntity insertUser(User user){
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        user.setUcreatetime(new Date());
        user.setUbalance(new BigDecimal(0));
        ResponseEntity re=userService.insertUser(user);
        return re;
    }
    @RequestMapping(value = "/admin/deleteUser/{uid}",method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable Integer uid){
        ResponseEntity re= userService.deleteUser(uid);
        return re;
    }
    @RequestMapping(value = "/admin/updateUser",method = RequestMethod.POST)
    public ResponseEntity updateUser(User user){
       ResponseEntity re= userService.updateUser(user);
       return re;
    }
    @RequestMapping(value = "/admin/gotest" ,method = RequestMethod.POST)
    public ResponseEntity gotest(@RequestParam(required = true) MultipartFile profile,String jiji){
        ResponseEntity list= userService.addUser(profile,jiji);
        return list;
    }
    @RequestMapping(value = "/admin/openUser/{uid}",method = RequestMethod.GET)
    public ModelAndView openUser(@PathVariable Integer uid){
        ModelAndView mav=new ModelAndView();
        User user=userService.selectById(uid);
        mav.getModel().put("data",user);
        mav.setViewName("/admin/openUser");
        return mav;
    }
    @RequestMapping(value = "/admin/testOne/{uid}",method = RequestMethod.GET)
    public User testOne(@PathVariable Integer uid){
        User user=userService.testOne(uid);
        System.out.println("---------------------------------");
        System.out.println(user);
        System.out.println("---------------------------------");
        System.out.println(user.getOrders());
        return user;
    }
    @RequestMapping(value = "/admin/goTestSqlProvider" ,method = RequestMethod.POST)
    public List<Map<String,Object>> goTestSqlProvider(String tiaojian){
       return userService.goTestSqlProvider(tiaojian);
    }



}