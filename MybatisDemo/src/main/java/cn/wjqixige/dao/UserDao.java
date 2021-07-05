package cn.wjqixige.dao;

import cn.wjqixige.domain.User;

import java.util.List;

public interface UserDao {

    /**
     * 返回所有用户
     * @return List<User>
     */
    List<User> findAll();
}
