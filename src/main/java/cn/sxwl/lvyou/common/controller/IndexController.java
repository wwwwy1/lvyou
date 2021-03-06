package cn.sxwl.lvyou.common.controller;

import cn.sxwl.lvyou.common.entity.ResponseEntity;
import cn.sxwl.lvyou.common.util.HttpUtils;
import cn.sxwl.lvyou.common.util.TokenManager;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping("/admin/login")
    public String login(){
        return "/admin/login";
    }
    @RequestMapping("/admin/index")
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView();
        mav.getModel().put("ii","hello");
        mav.setViewName("/admin/index");
        return mav;
    }
    @RequestMapping("/admin/users")
    public String users(){
        return "/admin/users";
    }
    @RequestMapping("/admin/addUser")
    public String addUsers(){
        return "/admin/addUser";
    }
    @RequestMapping("/admin/test")
    public String test(){
        return "/admin/test";
    }
    @RequestMapping("/admin/sceniclist")
    public String scenicList(){
        return "/admin/sceniclist";
    }
    @RequestMapping("/admin/addscenic")
    public String addScenic(){
        return "/admin/addscenic";
    }
    @RequestMapping("/admin/businesslist")
    public String businessList(){return "/admin/businesslist";}
    @RequestMapping("/admin/addbusiness")
    public String addBusiness(){return "/admin/addbusiness";}
    @RequestMapping("/admin/testsqlprovider")
    public String testSqlProvider(){return "/admin/testsqlprovider";}
    @RequestMapping("/admin/searchproduct")
    public String searchProduct(){return "/admin/searchProduct";}
    @RequestMapping("/admin/testChoice")
    public String testChoice(){return "/admin/testChoice";}
    @RequestMapping("/admin/userContact")
    public String userContact(){return "/admin/userContact";}

    @RequestMapping("/user/index")
    public String goUserIndex(){return "/user/index";}
    @GetMapping(value = "/user/scenicList")
    public String userScenicList(){
        return "/user/scenicList";
    }
    @GetMapping(value = "/user/scenicRegion")
    public String userScenicRegion(){
        return "/user/scenicRegion";
    }
    @GetMapping(value = "/user/businessList")
    public String userBusinessList(){
        return "/user/businessList";
    }
    @GetMapping(value = "/user/businessRegion")
    public String userBusinessRegion(){
        return "/user/businessRegion";
    }

}
