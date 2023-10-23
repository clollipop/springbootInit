package generator.mapper;

import generator.domain.User;

/**
* @author PC
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-10-21 17:05:35
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