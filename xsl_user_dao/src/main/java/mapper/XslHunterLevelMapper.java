package mapper;

import example.XslHunterLevelExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslHunterLevel;

import java.util.List;

public interface XslHunterLevelMapper {
    int countByExample(XslHunterLevelExample example);

    int deleteByExample(XslHunterLevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslHunterLevel record);

    int insertSelective(XslHunterLevel record);

    List<XslHunterLevel> selectByExample(XslHunterLevelExample example);

    XslHunterLevel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslHunterLevel record, @Param("example") XslHunterLevelExample example);

    int updateByExample(@Param("record") XslHunterLevel record, @Param("example") XslHunterLevelExample example);

    int updateByPrimaryKeySelective(XslHunterLevel record);

    int updateByPrimaryKey(XslHunterLevel record);
}