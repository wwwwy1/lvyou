package cn.sxwl.lvyou.common.controller;

import cn.sxwl.lvyou.common.entity.Business;
import cn.sxwl.lvyou.common.entity.Product;
import cn.sxwl.lvyou.common.entity.ResponseEntity;
import cn.sxwl.lvyou.common.entity.Scenic;
import cn.sxwl.lvyou.common.service.BusinessService;
import cn.sxwl.lvyou.common.service.ProductService;
import cn.sxwl.lvyou.common.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@RestController
public class BusinessController {
    @Autowired
    private BusinessService businessService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ScenicService scenicService;
    @RequestMapping(value = "/admin/businessPaging/{pageNum}" ,method = RequestMethod.GET)
    public ResponseEntity businessPaging(@PathVariable Integer pageNum){
        ResponseEntity list= businessService.selectPaging(pageNum);
        return list;
    }
    @RequestMapping(value = "/user/getBusinessList/{pageNum}" ,method = RequestMethod.GET)
    public ResponseEntity getBusinessList(@PathVariable Integer pageNum){
        ResponseEntity list= businessService.userBusinessList(pageNum);
        return list;
    }
    @RequestMapping(value = "/user/getBusinessRegion" ,method = RequestMethod.POST)
    public ResponseEntity getBusinessRegion(@RequestParam("keyWords") String keyWords,@RequestParam("pageNum") Integer pageNum){
        ResponseEntity list= businessService.userBusinessRegion(keyWords,pageNum);
        return list;
    }
    @RequestMapping(value = "/admin/insertBusiness",method = RequestMethod.POST)
    public ModelAndView insertScenic(@RequestParam MultipartFile profile, Business business){
        ResponseEntity re=new ResponseEntity();
       if (profile.isEmpty()) re= businessService.insertBusiness(null,business);
       else  re= businessService.insertBusiness(profile,business);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/admin/transfer");
        mav.getModel().put("data",re);
        return mav;
    }
    @RequestMapping(value = "/admin/deleteBusiness/{bid}",method = RequestMethod.DELETE)
    public ResponseEntity deleteScenic(@PathVariable Integer bid){
        ResponseEntity re= businessService.deleteBusiness(bid);
        return re;
    }
    @RequestMapping(value = "/admin/openBusiness/{bid}",method = RequestMethod.GET)
    public ModelAndView openBusiness(@PathVariable Integer bid){
        ModelAndView mav=new ModelAndView();
        ResponseEntity re=businessService.selectByBid(bid);
        mav.getModel().put("data",re.getData());
        mav.setViewName("/admin/openBusiness");
        return mav;
    }
    @RequestMapping(value = "/admin/updateBusiness",method = RequestMethod.POST)
    public ResponseEntity updateBusiness(MultipartFile imgGo, Business business){
        ResponseEntity re= businessService.updateBusiness(imgGo,business);
        return re;
    }
    @RequestMapping(value = "/admin/selectBusiness",method = RequestMethod.GET)
    public ResponseEntity selectBusiness(){
        return businessService.selectBusiness();
    }
    @GetMapping(value = "/user/getNiceBusiness")
    public ResponseEntity getNiceBusiness(Integer num){
        ResponseEntity re=businessService.getNiceBusiness(num);
        return re;
    }
    @GetMapping(value = "/user/hotelSingle")
    public ModelAndView scenicSingle(@RequestParam(required = true) Integer bid){
        ModelAndView mav=new ModelAndView();
        ResponseEntity re= businessService.selectByBid(bid);
        Business business=(Business) re.getData();
        List<Scenic> list2=new LinkedList<>();
        if (business.getBaddress().indexOf("绍兴")!=-1){
             list2=(List<Scenic>)scenicService.selectByNameOrAddress("绍兴",0).getData();
        }else if (business.getBaddress().indexOf("杭州")!=-1){
            list2=(List<Scenic>)scenicService.selectByNameOrAddress("杭州",0).getData();
        }else if (business.getBaddress().indexOf("宁波")!=-1){
            list2=(List<Scenic>)scenicService.selectByNameOrAddress("宁波",0).getData();
        }
        List<Product> list=productService.selectByBid(bid);
        mav.getModel().put("list",list);
        mav.getModel().put("list2",list2);
        mav.getModel().put("business",business);
        mav.setViewName("/user/hotelSingle");
        return mav;
    }
}
