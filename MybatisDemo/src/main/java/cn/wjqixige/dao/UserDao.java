package cn.wjqixige.dao;

import cn.wjqixige.domain.QueryVo;
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

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    int deleteUser(Integer userId);

    /**
     * 根据名称模糊查询1
     * @param username
     * @return
     */
    List<User> findByName1(String username);

    /**
     * 根据名称模糊查询2
     * @param username
     * @return
     */
    List<User> findByName2(String username);

    /**
     * 查询总记录条数
     * @return
     */
    int findTotal();

    /**
     * 根据QueryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findByVo(QueryVo vo);
}
