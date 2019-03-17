package cn.sxwl.lvyou.common.service;

import cn.sxwl.lvyou.common.dao.ContactMapper;
import cn.sxwl.lvyou.common.entity.Contact;
import cn.sxwl.lvyou.common.entity.ResponseEntity;
import cn.sxwl.lvyou.common.util.PageParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContactService {
    @Autowired
    private ContactMapper contactMapper;
    public ResponseEntity insertContact(Contact contact){
       Integer i= contactMapper.insert(contact);
       ResponseEntity re=new ResponseEntity();
       re.setStatus(i);
       if (i==1)re.setMsg("发送成功");
       else re.setMsg("发送失败");
        return re;
    }
    public ResponseEntity selectAll(Integer pageNum){
        Page page=PageHelper.startPage(pageNum, PageParam.PAGE_Region_SIZE);
        List<Contact> list=contactMapper.selectAll();
        ResponseEntity re=new ResponseEntity();
        Map<String,Object> map=new HashMap<>();
        map.put("data",list);
        map.put("records",page.getTotal());
        map.put("pages",page.getPages());
        re.setData(map);
        re.setStatus(pageNum);
        return re;
    }
    public ResponseEntity delContact(Integer contactid){
       Integer i= contactMapper.deleteByPrimaryKey(contactid);
        ResponseEntity re=new ResponseEntity();
        re.setStatus(i);
        if (i==1){
            re.setMsg("删除成功");
        }else re.setMsg("删除失败");
        return re;
    }
}
