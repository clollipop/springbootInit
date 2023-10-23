package com.star.springbootinit.mapper;
import com.star.springbootinit.model.entity.Role;


/**
* @author PC
* @description 针对表【role(角色表)】的数据库操作Mapper
*/
public interface RoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

}
