package cn.sxwl.lvyou.common.service;

import cn.sxwl.lvyou.common.dao.ScenicMapper;
import cn.sxwl.lvyou.common.entity.Business;
import cn.sxwl.lvyou.common.entity.ResponseEntity;
import cn.sxwl.lvyou.common.entity.Scenic;
import cn.sxwl.lvyou.common.entity.User;
import cn.sxwl.lvyou.common.util.HttpUtils;
import cn.sxwl.lvyou.common.util.PageParam;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ScenicService {

    @Autowired
    private ScenicMapper scenicMapper;
    public ResponseEntity selectAll(){
        ResponseEntity re=new ResponseEntity();
        List<Scenic> list=scenicMapper.selectAll();
        re.setData(list);
        return re;
    }
    public ResponseEntity selectPaging(Integer pageNum){
        List<Scenic> list=new ArrayList<>();
        Page page=PageHelper.startPage(pageNum, PageParam.PAGE_SIZE);
        list=scenicMapper.selectAll();
        ResponseEntity re=new ResponseEntity();
        Map<String,Object> map=new HashMap<>();
        map.put("data",list);
        map.put("records",page.getTotal());
        map.put("pages",page.getPages());
        re.setData(map);
        re.setStatus(pageNum);
        return re;
    }
    public ResponseEntity getUserScenic(Integer pageNum){
        List<Scenic> list=new ArrayList<>();
        Page page=PageHelper.startPage(pageNum, PageParam.PAGE_Scenic_SIZE);
        list=scenicMapper.selectAll();
        ResponseEntity re=new ResponseEntity();
        Map<String,Object> map=new HashMap<>();
        map.put("data",list);
        map.put("records",page.getTotal());
        map.put("pages",page.getPages());
        re.setData(map);
        re.setStatus(pageNum);
        return re;
    }
    public ResponseEntity getRegionScenic(String keyWords,Integer pageNum){
        List<Scenic> list=new ArrayList<>();
        Page page=PageHelper.startPage(pageNum, PageParam.PAGE_Region_SIZE);
        list=scenicMapper.selectByAddressNotSelf(keyWords,0);
        ResponseEntity re=new ResponseEntity();
        Map<String,Object> map=new HashMap<>();
        map.put("data",list);
        map.put("records",page.getTotal());
        map.put("pages",page.getPages());
        re.setData(map);
        re.setStatus(pageNum);
        return re;
    }
    public ResponseEntity insertScenic(MultipartFile newProfile, Scenic scenic){
            ResponseEntity re=new ResponseEntity();
            String profilesPath="F:\\sanxia\\lvyou\\lvyou\\src\\main\\webapp\\static\\images\\scenicImg\\";
            BufferedOutputStream out=null;
            UUID uuid=UUID.randomUUID();
            String uid=uuid.toString();
            uid=uid.replace("-","");
            String[] last=newProfile.getOriginalFilename().split("[.]");
            String path=profilesPath+uid+"."+last[1];
            scenic.setSimg("scenicImg/"+uid+"."+last[1]);
            re.setStatus(scenicMapper.insert(scenic));
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
            re.setMsg("添加成功");
            return re;
    }
    public ResponseEntity deleteScenic(Integer id){
        int result=scenicMapper.deleteByPrimaryKey(id);
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
    public ResponseEntity selectById(Integer id){
        Scenic scenic=scenicMapper.selectByPrimaryKey(id);
        ResponseEntity re=new ResponseEntity();
        re.setData(scenic);
        re.setMsg("查找成功");
        re.setStatus(1);
        return re;
    }
    public ResponseEntity updateScenic(MultipartFile imgGo, Scenic scenic){
        ResponseEntity re=new ResponseEntity();
        if (imgGo!=null){
            String profilesPath="F:\\sanxia\\lvyou\\lvyou\\src\\main\\webapp\\static\\images\\scenicImg\\";
            BufferedOutputStream out=null;
            UUID uuid=UUID.randomUUID();
            String uid=uuid.toString();
            uid=uid.replace("-","");
            String[] last=imgGo.getOriginalFilename().split("[.]");
            String path=profilesPath+uid+"."+last[1];
            scenic.setSimg("scenicImg/"+uid+"."+last[1]);
            re.setStatus(scenicMapper.updateByPrimaryKey(scenic));
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
            re.setStatus(scenicMapper.updateNotImg(scenic));
        }
        re.setMsg("更改成功");
        return re;
    }
    public ResponseEntity selectHotScenic(Integer num){
        ResponseEntity re=new ResponseEntity();
        PageHelper.startPage(1, num);
        List<Scenic> list=scenicMapper.selectAll();
        re.setData(list);
        return re;
    }
    public ResponseEntity selectByNameOrAddress(String keyWords,Integer sid){
       PageHelper.startPage(1,4);
       List<Scenic> list= scenicMapper.selectByAddressNotSelf(keyWords,sid);
       ResponseEntity re=new ResponseEntity();
       re.setData(list);
       return re;
    }
    public List<Business> selectByAddressBusiness(String keyWords){
        PageHelper.startPage(1,4);
        List<Business> list= scenicMapper.selectByAddressBusiness(keyWords);
        return list;
    }
    public ResponseEntity getWeather(String cityId){
        //System.out.println("------------------"+cityId);
        ResponseEntity re=new ResponseEntity();
        String host = "http://freecityid.market.alicloudapi.com";
        String path = "/whapi/json/alicityweather/briefcondition";
        String method = "POST";
        String appcode = "5a83fc424eab4c35911d7f2caf18c7c3";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("cityId", cityId);
        bodys.put("token", "46e13b7aab9bb77ee3358c3b672a2ae4");
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            // System.out.println(response.toString());
            //获取response的body
            re.setData(JSONObject.parseObject(EntityUtils.toString(response.getEntity())) );
            //System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }
}
