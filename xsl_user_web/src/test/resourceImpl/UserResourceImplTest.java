package resourceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user.service.impl.UserServiceImpl;
import vo.UserReqVo;
import vo.XslUserRegister;

import static org.junit.Assert.*;



@RunWith(SpringJUnit4ClassRunner.class)//固定写法
@ContextConfiguration({"classpath:spring/applicationContext-*.xml"})//spring与mybatis整合的配置文件

public class UserResourceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @Test
    public void quickCreateUser() {

    }
//
//    @Test
//    public void userLogin() {
//    }
//
//    @Test
//    public void getUserByToken() {
//    }
//
//    @Test
//    public void password() {
//    }
}