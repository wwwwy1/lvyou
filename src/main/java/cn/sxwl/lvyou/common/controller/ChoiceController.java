package cn.sxwl.lvyou.common.controller;

import cn.sxwl.lvyou.common.entity.Choice;
import cn.sxwl.lvyou.common.entity.ResponseEntity;
import cn.sxwl.lvyou.common.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ChoiceController {
    @Autowired
    private ChoiceService choiceService;
    @RequestMapping(value = "/admin/searchRoom",method = RequestMethod.POST)
    public ResponseEntity searchRoom(Integer bid, String cdate) throws ParseException {
        ResponseEntity re=new ResponseEntity();
        System.out.println(cdate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Choice> list=choiceService.searchRoom(bid,cdate);
        if (list.isEmpty())
            re.setStatus(0);
        else re.setStatus(1);
        re.setData(list);
        return  re;
    }
    @GetMapping(value = "/admin/openChoice/{bid}")
    public ModelAndView openChoice(@PathVariable String bid){
        ModelAndView mav=new ModelAndView();
        mav.getModel().put("bid",bid);
        mav.setViewName("/admin/openChoice");
        return mav;
    }

}
