package cn.sxwl.lvyou.common.service;

import cn.sxwl.lvyou.common.dao.ChoiceMapper;
import cn.sxwl.lvyou.common.entity.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChoiceService {
    @Autowired
    private ChoiceMapper choiceMapper;


    public List<Choice> searchRoom(Integer bid, String cdate){
      List<Choice> list=  choiceMapper.selectByBidDate(bid,cdate);
    return list;
    }
}
