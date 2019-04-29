package mapper;

import example.XslRoleRuleExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslRoleRule;

import java.util.List;

public interface XslRoleRuleMapper {
    int countByExample(XslRoleRuleExample example);

    int deleteByExample(XslRoleRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslRoleRule record);

    int insertSelective(XslRoleRule record);

    List<XslRoleRule> selectByExample(XslRoleRuleExample example);

    XslRoleRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslRoleRule record, @Param("example") XslRoleRuleExample example);

    int updateByExample(@Param("record") XslRoleRule record, @Param("example") XslRoleRuleExample example);

    int updateByPrimaryKeySelective(XslRoleRule record);

    int updateByPrimaryKey(XslRoleRule record);
}