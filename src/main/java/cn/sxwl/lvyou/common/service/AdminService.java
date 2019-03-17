package cn.sxwl.lvyou.common.service;

import cn.sxwl.lvyou.common.dao.AdminMapper;
import cn.sxwl.lvyou.common.entity.Admin;
import cn.sxwl.lvyou.common.entity.ResponseEntity;
import cn.sxwl.lvyou.common.util.MD5;
import cn.sxwl.lvyou.common.util.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private TokenManager tokenManager;
    public ResponseEntity loginCheck(String username,String password){
        Admin admin=adminMapper.selectByPrimaryKey(username);
        ResponseEntity re=new ResponseEntity();
        if (admin==null){
            re.setStatus(2);
            re.setMsg("用户错误");
        }else if (admin.getApassword().equals(MD5.md5(password))){
            re.setStatus(1);
            re.setMsg("登录成功");
            re.setData(tokenManager.createToken(1));
        }else {
            re.setStatus(3);
            re.setMsg("密码错误");
        }
        return re;
    }
    public ResponseEntity logout(String token){
        ResponseEntity re=new ResponseEntity();
        Boolean flag= tokenManager.clearToken(token);
        if (flag) {
            re.setMsg("退出成功");
            re.setStatus(1);
        }else {
            re.setStatus(2);
            re.setMsg("账户异常");
        }
        return re;
    }

}
