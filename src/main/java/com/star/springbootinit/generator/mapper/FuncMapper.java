package com.star.springbootinit.generator.mapper;

import com.star.springbootinit.generator.domain.Func;

/**
* @author PC
* @description 针对表【func(权限表)】的数据库操作Mapper
* @createDate 2023-10-28 21:03:03
* @Entity generator.domain.Func
*/
public interface FuncMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Func record);

    int insertSelective(Func record);

    Func selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Func record);

    int updateByPrimaryKey(Func record);

}
