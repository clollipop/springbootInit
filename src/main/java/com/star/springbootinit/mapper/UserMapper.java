package com.star.springbootinit.mapper;
import com.star.springbootinit.model.entity.User;
import java.util.List;

/**
* @author PC
* @description 针对表【user(用户表)】的数据库操作Mapper
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查询用户对应的权限
     * @param id
     * @return
     */
    List<String> searchUserPermissions(Long id);

}
