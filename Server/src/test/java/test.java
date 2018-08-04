
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xiyoulinux.recruitment.config.database.DbConfig;
import org.xiyoulinux.recruitment.config.database.MyBatis;
import org.xiyoulinux.recruitment.config.spring.RootConfig;
import org.xiyoulinux.recruitment.config.spring.WebConfig;
import org.xiyoulinux.recruitment.config.spring.WebInit;
import org.xiyoulinux.recruitment.dao.ActivityDAO;
import org.xiyoulinux.recruitment.model.Activity;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {RootConfig.class,WebConfig.class,WebInit.class,MyBatis.class,DbConfig.class})
@WebAppConfiguration("src/main/resources") //1 此注解指定web资源的位置，默认为src/main/webapp
public class test{

    @Before
    public void before() throws Exception{}

    @After
    public void after() throws Exception{}

    @Autowired
    private ActivityDAO activityDAO;

    @Test
    public void Test(){
//        Activity activity = new Activity("纳新1","2018-01-01",1);
//        System.out.println(activityDAO.insert(activity));
    }
}
