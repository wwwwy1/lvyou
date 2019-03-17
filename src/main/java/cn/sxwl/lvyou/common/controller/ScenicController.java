package cn.sxwl.lvyou.common.controller;

import cn.sxwl.lvyou.common.dao.ScenicMapper;
import cn.sxwl.lvyou.common.entity.Business;
import cn.sxwl.lvyou.common.entity.ResponseEntity;
import cn.sxwl.lvyou.common.entity.Scenic;
import cn.sxwl.lvyou.common.service.ScenicService;
import cn.sxwl.lvyou.common.service.UserService;
import cn.sxwl.lvyou.common.util.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class ScenicController {
    @Autowired
    private ScenicService scenicService;
    @RequestMapping(value = "/admin/getScenics",method = RequestMethod.GET)
    public ResponseEntity selectAll(){
        ResponseEntity  list=scenicService.selectAll();
        return list;
    }
    @RequestMapping(value = "/user/getScenicList/{pageNum}",method = RequestMethod.GET)
    public ResponseEntity getScenicList(@PathVariable Integer pageNum){
        ResponseEntity  list=scenicService.getUserScenic(pageNum);
        return list;
    }
    @RequestMapping(value = "/user/getScenicRegion",method = RequestMethod.POST)
    public ResponseEntity getRegionScenic(@RequestParam("keyWords") String keyWords,@RequestParam("pageNum") Integer pageNum){
        ResponseEntity  list=scenicService.getRegionScenic(keyWords,pageNum);
        return list;
    }
    @RequestMapping(value = "/admin/scenicPaging/{pageNum}" ,method = RequestMethod.GET)
    public ResponseEntity scenicPaging(@PathVariable Integer pageNum){
        ResponseEntity list= scenicService.selectPaging(pageNum);
        return list;
    }
    @RequestMapping(value = "/admin/insertScenic",method = RequestMethod.POST)
    public ModelAndView insertScenic(@RequestParam(required = true) MultipartFile profile, Scenic scenic){
        ResponseEntity re= scenicService.insertScenic(profile,scenic);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/admin/transfer");
        mav.getModel().put("data",re);
        return mav;
    }
    @RequestMapping(value = "/admin/deleteScenic/{sid}",method = RequestMethod.DELETE)
    public ResponseEntity deleteScenic(@PathVariable Integer sid){
        ResponseEntity re= scenicService.deleteScenic(sid);
        return re;
    }
    @RequestMapping(value = "/admin/openScenic/{sid}",method = RequestMethod.GET)
    public ModelAndView openScenic(@PathVariable Integer sid){
        ModelAndView mav=new ModelAndView();
        ResponseEntity scenic=scenicService.selectById(sid);
        mav.getModel().put("data",scenic.getData());
        mav.setViewName("/admin/openScenic");
        return mav;
    }
    @RequestMapping(value = "/admin/updateScenic",method = RequestMethod.POST)
    public ResponseEntity updateScenic(MultipartFile imgGo, Scenic scenic){
        ResponseEntity re= scenicService.updateScenic(imgGo,scenic);
        return re;
    }
    @GetMapping(value = "/user/getHotScenic")
    public ResponseEntity getHotScenic(Integer num){
        ResponseEntity re=scenicService.selectHotScenic(num);
        return re;
    }
    @GetMapping(value = "/user/scenicSingle")
    public ModelAndView scenicSingle(@RequestParam(required = true) Integer sid){
        ModelAndView mav=new ModelAndView();
        Scenic scenic=(Scenic) scenicService.selectById(sid).getData();
        mav.getModel().put("scenic",scenic);
        System.out.println(scenic);
        List<Scenic> list=new LinkedList<>();
        List<Business> list2=new LinkedList<>();
        String cityId="";
        if (scenic.getSaddress().indexOf("绍兴")!=-1){
            list=(List<Scenic>)scenicService.selectByNameOrAddress("绍兴",sid).getData();
            list2=scenicService.selectByAddressBusiness("绍兴");
            cityId="1246";
        }
        else if (scenic.getSaddress().indexOf("杭州")!=-1) {
            list = (List<Scenic>) scenicService.selectByNameOrAddress("杭州", sid).getData();
            list2=scenicService.selectByAddressBusiness("杭州");
            cityId="1185";
        }
        else if (scenic.getSaddress().indexOf("宁波")!=-1) {
            list = (List<Scenic>) scenicService.selectByNameOrAddress("宁波", sid).getData();
            list2 = scenicService.selectByAddressBusiness("宁波");
            cityId="1208";
        }
        mav.getModel().put("list",list);
        mav.getModel().put("list2",list2);
        mav.getModel().put("cityId",cityId);
        mav.setViewName("/user/scenicSingle");
        return mav;
    }
    @GetMapping(value = "/user/getWeather")
    public ResponseEntity getWeather(String cityId){
       return scenicService.getWeather(cityId);
    }

}
