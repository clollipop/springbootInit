package com.star.springbootinit.generator.mapper;

import com.star.springbootinit.generator.domain.Role;

/**
* @author PC
* @description 针对表【role(角色表)】的数据库操作Mapper
* @createDate 2023-10-28 21:03:18
* @Entity generator.domain.Role
*/
public interface RoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

}
