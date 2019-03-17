package cn.sxwl.lvyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@MapperScan(basePackages = {"cn.sxwl.lvyou.common.*"})
@ServletComponentScan
public class LvyouApplication {
/*@RequestMapping("/")
	public String hello(){
		return "hello world";
}*/
	public static void main(String[] args) {
		SpringApplication.run(LvyouApplication.class, args);
	}

}

