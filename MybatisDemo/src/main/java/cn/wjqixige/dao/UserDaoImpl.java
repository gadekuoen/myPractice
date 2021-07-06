package cn.wjqixige.dao;

import cn.wjqixige.domain.QueryVo;
import cn.wjqixige.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;


    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    public List<User> findAll() {
        SqlSession session = factory.openSession();
        List<User> users = session.selectList("cn.wjqixige.dao.UserDao.findAll");
        session.close();
        return users;
    }

    public User findById(Integer userId) {
        SqlSession session = factory.openSession();
        User user = session.selectOne("cn.wjqixige.dao.UserDao.findById", userId);
        session.close();
        return user;
    }

    public int userSave(User user) {
        SqlSession session = factory.openSession();
        int res = session.insert("cn.wjqixige.dao.UserDao.userSave", user);
        session.commit();
        session.close();
        return res;
    }

    public int updateUser(User user) {
        SqlSession session = factory.openSession();
        int res = session.insert("cn.wjqixige.dao.UserDao.updateUser", user);
        session.commit();
        session.close();
        return res;
    }

    public int deleteUser(Integer userId) {
        SqlSession session = factory.openSession();
        int res = session.insert("cn.wjqixige.dao.UserDao.deleteUser", userId);
        session.commit();
        session.close();
        return res;
    }

    public List<User> findByName1(String username) {
        return null;
    }

    public List<User> findByName2(String username) {
        return null;
    }

    public int findTotal() {
        SqlSession session = factory.openSession();
        int res = session.selectOne("cn.wjqixige.dao.UserDao.findTotal");
        session.close();
        return res;
    }

    public List<User> findByVo(QueryVo vo) {
        return null;
    }
}
