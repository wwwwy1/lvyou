package cn.sxwl.lvyou.common.service;

import cn.sxwl.lvyou.common.dao.BusinessMapper;
import cn.sxwl.lvyou.common.dao.ScenicMapper;
import cn.sxwl.lvyou.common.entity.Business;
import cn.sxwl.lvyou.common.entity.ResponseEntity;
import cn.sxwl.lvyou.common.entity.Scenic;
import cn.sxwl.lvyou.common.util.MD5;
import cn.sxwl.lvyou.common.util.PageParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Service
public class BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    public ResponseEntity selectPaging(Integer pageNum){
        List<Business> list=new ArrayList<>();
        Page page=PageHelper.startPage(pageNum, PageParam.PAGE_SIZE);
        list=businessMapper.selectAll();
        ResponseEntity re=new ResponseEntity();
        Map<String,Object> map=new HashMap<>();
        map.put("data",list);
        map.put("records",page.getTotal());
        map.put("pages",page.getPages());
        re.setData(map);
        re.setStatus(pageNum);
        return re;
    }
    public ResponseEntity userBusinessList(Integer pageNum){
        List<Business> list=new ArrayList<>();
        Page page=PageHelper.startPage(pageNum, PageParam.PAGE_Scenic_SIZE);
        list=businessMapper.selectAll();
        ResponseEntity re=new ResponseEntity();
        Map<String,Object> map=new HashMap<>();
        map.put("data",list);
        map.put("records",page.getTotal());
        map.put("pages",page.getPages());
        re.setData(map);
        re.setStatus(pageNum);
        return re;
    }
    public ResponseEntity userBusinessRegion(String keyWord,Integer pageNum){
        List<Business> list=new ArrayList<>();
        Page page=PageHelper.startPage(pageNum, PageParam.PAGE_Region_SIZE);
        list=businessMapper.selectByAddress(keyWord);
        ResponseEntity re=new ResponseEntity();
        Map<String,Object> map=new HashMap<>();
        map.put("data",list);
        map.put("records",page.getTotal());
        map.put("pages",page.getPages());
        re.setData(map);
        re.setStatus(pageNum);
        return re;
    }
    public ResponseEntity selectByBid(Integer bid){
        Business business =businessMapper.selectByPrimaryKey(bid);
        ResponseEntity re=new ResponseEntity();
        re.setData(business);
        re.setStatus(1);
        return re;
    }
    public ResponseEntity insertBusiness(MultipartFile file,Business business){
        ResponseEntity re=new ResponseEntity();
        business.setBpassword(MD5.md5(business.getBpassword()));
        business.setBcreatetime(new Date());
        business.setBbalance(new BigDecimal(0));
        if (file!=null){
            String profilesPath="F:\\sanxia\\lvyou\\lvyou\\src\\main\\webapp\\static\\images\\businessImg\\";
            BufferedOutputStream out=null;
            UUID uuid=UUID.randomUUID();
            String uid=uuid.toString();
            uid=uid.replace("-","");
            String[] last=file.getOriginalFilename().split("[.]");
            String path=profilesPath+uid+"."+last[1];
            business.setBimg("businessImg/"+uid+"."+last[1]);
            re.setStatus(businessMapper.insert(business));
            try {
                File folder=new File(profilesPath);
                if (!folder.exists())folder.mkdirs();
                out = new BufferedOutputStream(new FileOutputStream(path));
                out.write(file.getBytes());
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
        }else {
            business.setBimg("businessImg/null.jpg");
            re.setStatus(businessMapper.insert(business));
        }
        re.setMsg("新增成功");
        return re;
    }
    public ResponseEntity deleteBusiness(Integer id){
        int result=businessMapper.deleteByPrimaryKey(id);
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
    public ResponseEntity updateBusiness(MultipartFile imgGo, Business business){
        ResponseEntity re=new ResponseEntity();
        business.setBpassword(MD5.md5(business.getBpassword()));
        if (imgGo!=null){
            String profilesPath="F:\\sanxia\\lvyou\\lvyou\\src\\main\\webapp\\static\\images\\businessImg\\";
            BufferedOutputStream out=null;
            UUID uuid=UUID.randomUUID();
            String uid=uuid.toString();
            uid=uid.replace("-","");
            String[] last=imgGo.getOriginalFilename().split("[.]");
            String path=profilesPath+uid+"."+last[1];
            business.setBimg("businessImg/"+uid+"."+last[1]);
            re.setStatus(businessMapper.updateByPrimaryKey(business));
            try {
                File folder=new File(profilesPath);
                if (!folder.exists())folder.mkdirs();
                out = new BufferedOutputStream(new FileOutputStream(path));
                out.write(imgGo.getBytes());
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
        }else {
            re.setStatus(businessMapper.updateNotImg(business));
        }
        re.setMsg("更改成功");
        return re;
    }
    public ResponseEntity selectBusiness(){
        ResponseEntity re=new ResponseEntity();
        re.setData(businessMapper.selectAll());
        return re;
    }
    public ResponseEntity getNiceBusiness(Integer num){
        ResponseEntity re=new ResponseEntity();
        PageHelper.startPage(1,num);
        re.setData( businessMapper.getNiceBusiness());
        return re;
    }
}
