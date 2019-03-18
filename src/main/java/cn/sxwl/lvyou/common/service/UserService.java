package cn.sxwl.lvyou.common.service;

import cn.sxwl.lvyou.common.dao.AdminMapper;
import cn.sxwl.lvyou.common.dao.UserMapper;
import cn.sxwl.lvyou.common.entity.ResponseEntity;
import cn.sxwl.lvyou.common.entity.ResponsePagingEntity;
import cn.sxwl.lvyou.common.entity.User;
import cn.sxwl.lvyou.common.util.MD5;
import cn.sxwl.lvyou.common.util.PageParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Filter;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> selectAll(){
       List<User> list=new ArrayList<>();
       list=userMapper.selectAll();
       return list;
    }
    public ResponseEntity selectPaging(Integer pageNum){
        List<User> list=new ArrayList<>();
        Page page=PageHelper.startPage(pageNum, PageParam.PAGE_SIZE);
        list=userMapper.selectAll();
        ResponseEntity re=new ResponseEntity();
        Map<String,Object> map=new HashMap<>();
        map.put("data",list);
        map.put("records",page.getTotal());
        map.put("pages",page.getPages());
        re.setData(map);
        re.setStatus(pageNum);
        return re;
    }
    public ResponseEntity insertUser(User user){
        ResponseEntity re=new ResponseEntity();
        user.setUpassword(MD5.md5(user.getUpassword()));
        user.setUhead("defalut");
        int i=userMapper.insert(user);
        if (i==1){
            re.setMsg("添加成功");
            re.setStatus(1);
        }else {
            re.setMsg("添加失败");
            re.setStatus(0);
        }
        return re;
    }
    public ResponseEntity deleteUser(Integer id){
        int result=userMapper.deleteByPrimaryKey(id);
        ResponseEntity re=new ResponseEntity();
        if (result==1){
            re.setStatus(1);
            re.setMsg("删除成功");
        }else if (result==0){
            re.setMsg("删除失败");
            re.setStatus(0);
        }
        return re;
    }
    public ResponseEntity updateUser(User user){
        user.setUpassword(MD5.md5(user.getUpassword()));
        int result=userMapper.updateByPrimaryKey(user);
        ResponseEntity re=new ResponseEntity();
        re.setStatus(result);
        if (result==1){
            re.setMsg("更新成功");
        }else re.setMsg("更新失败");
        return re;
    }
    public User selectById(Integer uid){
       User user= userMapper.selectByPrimaryKey(uid);
       return user;
    }
    public ResponseEntity addUser(MultipartFile newProfile, String jiji){
        System.out.println("-------------------------------"+newProfile.getOriginalFilename());
        System.out.println("-------------------------------"+newProfile.getName());
        ResponseEntity re=new ResponseEntity();
        String profilesPath="F:\\sanxia\\lvyou\\lvyou\\src\\main\\webapp\\admin\\images\\scenicImg\\";
        BufferedOutputStream out=null;
        UUID uuid=UUID.randomUUID();
        String uid=uuid.toString();
        uid=uid.replace("-","");
        String[] last=newProfile.getOriginalFilename().split("[.]");
        System.out.println("-------------------------------"+last[1]);
        String path=profilesPath+uid+"."+last[1];
        System.out.println(path);
        try {
            File folder=new File(profilesPath);
            if (!folder.exists())folder.mkdirs();
            out = new BufferedOutputStream(new FileOutputStream(path));
            out.write(newProfile.getBytes());
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
            re.setMsg("badddd");
            return re;
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        re.setMsg("okkk");
        return re;
    }
    public User testOne(Integer uid){
       return userMapper.selectUserOrderByUid(uid);
    }
    public List<Map<String,Object>>  goTestSqlProvider(String tiaojian){
          return   userMapper.goTestSqlProvider(tiaojian);
    }
    public ResponseEntity userLogin(User user, HttpServletRequest request){
        ResponseEntity re=new ResponseEntity();
        User checkUser= userMapper.selectByUsername(user.getUname());
        if (checkUser==null){
            re.setMsg("用户不存在");
            re.setStatus(2);
            return re;
        }else if (!checkUser.getUpassword().equals(MD5.md5(user.getUpassword()))){
            re.setMsg("密码错误");
            re.setStatus(1);
            return re;
        }
        re.setMsg("登录成功");
        re.setStatus(0);
        HttpSession session=request.getSession();
        session.setAttribute("username",user.getUname());
        return re;
    }
    public ResponseEntity userRegister(User user, HttpServletRequest request){
        ResponseEntity re=new ResponseEntity();
        user.setUhead("/headImg/boy.jpg");
        user.setUpassword(MD5.md5(user.getUpassword()));
        user.setUcreatetime(new Date());
        user.setUbalance(BigDecimal.ZERO);
        int i=userMapper.insert(user);
        if (i==1){
            re.setMsg("登录成功");
            re.setStatus(1);
            HttpSession session=request.getSession();
            session.setAttribute("username",user.getUname());
            return re;
        }else {
            re.setMsg("注册失败");
            re.setStatus(0);
            return re;
        }
    }
}
