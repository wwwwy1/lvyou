package cn.sxwl.lvyou.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenManager {
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    public  String createToken(int uid){
        UUID uuid=UUID.randomUUID();
        String token=uid+"_"+uuid;
        String key=uid+"_token";
        token=token.replace("-","");
        redisTemplate.opsForValue().set(key,token,30,TimeUnit.MINUTES);
        return token;
    }
    public  boolean checkToken(String token){
        if (token==null||"".equals(token)) return false;
        String[] arr=token.split("_");
        if (arr.length!=2)return false;
        String key=arr[0]+"_token";
        String r_token= (String) redisTemplate.opsForValue().get(key);
        if (r_token==null) return false;
        if (r_token.equals(token))
            redisTemplate.opsForValue().set(key,token,30,TimeUnit.MINUTES);
        else return false;
        return true;
    }
    public boolean clearToken(String token){
        if (token==null||"".equals(token)) return false;
        String[] arr=token.split("_");
        if (arr.length!=2)return false;
        String key=arr[0]+"_token";
        String r_token= (String) redisTemplate.opsForValue().get(key);
        if (r_token==null) return false;
        redisTemplate.delete(key);
        return true;
    }
   /* public static void main(String[] args) {
        TokenManager tm=new TokenManager();
        System.out.println( tm.createToken(12));
    }*/
}