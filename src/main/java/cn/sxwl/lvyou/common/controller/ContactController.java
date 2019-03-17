package cn.sxwl.lvyou.common.controller;

import cn.sxwl.lvyou.common.entity.Contact;
import cn.sxwl.lvyou.common.entity.ResponseEntity;
import cn.sxwl.lvyou.common.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;
    @PostMapping(value = "/user/insertContact")
    public ResponseEntity insertContact(Contact contact){
        System.out.println(contact);
        return contactService.insertContact(contact);
    }
    @GetMapping(value = "/admin/getContacts/{pageNum}")
    public ResponseEntity getContacts(@PathVariable("pageNum") Integer pageNum){
        return contactService.selectAll(pageNum);
    }
    @DeleteMapping(value = "/admin/deleteContact/{contactid}")
    public ResponseEntity delContact(@PathVariable("contactid")Integer contactid){
        ResponseEntity re=contactService.delContact(contactid);
        return re;
    }

}
