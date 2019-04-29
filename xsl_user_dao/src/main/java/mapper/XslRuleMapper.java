package mapper;

import example.XslRuleExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslRule;

import java.util.List;

public interface XslRuleMapper {
    int countByExample(XslRuleExample example);

    int deleteByExample(XslRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslRule record);

    int insertSelective(XslRule record);

    List<XslRule> selectByExample(XslRuleExample example);

    XslRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslRule record, @Param("example") XslRuleExample example);

    int updateByExample(@Param("record") XslRule record, @Param("example") XslRuleExample example);

    int updateByPrimaryKeySelective(XslRule record);

    int updateByPrimaryKey(XslRule record);
}