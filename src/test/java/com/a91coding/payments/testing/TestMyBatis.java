package com.a91coding.payments.testing;

import com.a91coding.payments.model.User;
import com.a91coding.payments.service.IUserService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath*:spring-config/spring-mybatis.xml"})

public class TestMyBatis {
    private static Logger logger = Logger.getLogger(TestMyBatis.class);
    private ApplicationContext ac = null;
    @Autowired
    private IUserService userService = null;

	@Before
	public void before() {
		ac = new ClassPathXmlApplicationContext("classpath:spring-config/spring-mybatis.xml");
		userService = (IUserService) ac.getBean("userService");
	}

    @Test
    public void test1() {
        User user = userService.selectByPrimaryKey(1);
         System.out.println(user.getUsername());
         logger.info("值："+user.getUsername());
        logger.info(JSON.toJSONString(user));
    }
}
