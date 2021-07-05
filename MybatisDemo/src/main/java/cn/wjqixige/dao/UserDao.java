package cn.wjqixige.dao;

import cn.wjqixige.domain.User;

import java.util.List;

public interface UserDao {

    /**
     * 返回所有用户
     * @return List<User>
     */
    List<User> findAll();

    /**
     * 根据ID查询
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 保存用户
     * @param user
     * @return 影响数据库记录的行数
     */
    int userSave(User user);
}
