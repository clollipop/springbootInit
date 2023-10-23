package com.star.springbootinit.mapper;

import com.star.springbootinit.model.entity.Func;

/**
* @author PC
* @description 针对表【func(权限表)】的数据库操作Mapper
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
