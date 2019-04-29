package mapper;

import example.XslManagerRoleExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslManagerRole;

import java.util.List;

public interface XslManagerRoleMapper {
    int countByExample(XslManagerRoleExample example);

    int deleteByExample(XslManagerRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslManagerRole record);

    int insertSelective(XslManagerRole record);

    List<XslManagerRole> selectByExample(XslManagerRoleExample example);

    XslManagerRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslManagerRole record, @Param("example") XslManagerRoleExample example);

    int updateByExample(@Param("record") XslManagerRole record, @Param("example") XslManagerRoleExample example);

    int updateByPrimaryKeySelective(XslManagerRole record);

    int updateByPrimaryKey(XslManagerRole record);
}