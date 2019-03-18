package cn.sxwl.lvyou;

import cn.sxwl.lvyou.common.util.HttpUtils;
import cn.sxwl.lvyou.common.util.MD5;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LvyouApplicationTests {
	@Test
	public void contextLoads() {
		System.out.println(MD5.md5("admin"));
	}
}

