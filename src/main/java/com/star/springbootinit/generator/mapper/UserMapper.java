package com.star.springbootinit.generator.mapper;

import com.star.springbootinit.generator.domain.User;

/**
* @author PC
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-10-28 21:03:23
* @Entity generator.domain.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
