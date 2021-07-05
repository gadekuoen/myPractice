package cn.wjqixige;

import cn.wjqixige.dao.UserDao;
import cn.wjqixige.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisCRUDTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserDao userDao;

    @Test
    public void testFindById() {
        User user = userDao.findById(41);
        System.out.println(user);
    }

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    /** 需要加入事务，否则数据库不会添加数据 */
    public void testUserSave(){
        User user = new User();
        user.setUserName("wujiang123");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("杭州");
        int count = userDao.userSave(user);
        System.out.println(count);
    }

    @Before
    public void init() throws Exception{
        //读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //创建sqlSession工厂对象
        factory = builder.build(in);
        //创建SqlSession对象
        session = factory.openSession();
        //创建Dao的代理对象
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destory() throws IOException {
        session.commit();
        session.close();
        in.close();
    }
}
