package com.a91coding.payments.testing;

import com.a91coding.payments.model.User;
import com.a91coding.payments.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring-config/spring-mybatis.xml"})
public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
    private ApplicationContext ac = null;
    @Autowired
    private IUserService userService;

    @Before
    public void before() {
        ac = new ClassPathXmlApplicationContext("classpath:spring-config/spring-mybatis.xml");
        userService = (IUserService) ac.getBean("userService");
    }

    @Test
    public void add() throws Exception {
        User user = new User();
        user.setUsername("zhouguang");
        user.setPassword("666666");
        user.setNickname("周光3");
        user.setStatus(new Byte("1"));
        userService.insert(user);
        logger.debug("返回自增长的主键：" + user.getId());
        logger.info("返回自增长的主键：" + user.getId());
    }

}
