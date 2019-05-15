package resourceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)//固定写法
@ContextConfiguration({"classpath:spring/applicationContext-*.xml"})//spring与mybatis整合的配置文件

public class UserResourceImplTest {

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