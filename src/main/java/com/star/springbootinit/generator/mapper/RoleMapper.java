package generator.mapper;

import generator.domain.Role;

/**
* @author PC
* @description 针对表【role(角色表)】的数据库操作Mapper
* @createDate 2023-10-28 18:27:55
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
