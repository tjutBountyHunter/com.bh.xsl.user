package mapper;

import example.XslMasterLevelExperience;
import example.XslMasterLevelExperienceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XslMasterLevelExperienceMapper {
    int countByExample(XslMasterLevelExperienceExample example);

    int deleteByExample(XslMasterLevelExperienceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslMasterLevelExperience record);

    int insertSelective(XslMasterLevelExperience record);

    List<XslMasterLevelExperience> selectByExample(XslMasterLevelExperienceExample example);

    XslMasterLevelExperience selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslMasterLevelExperience record, @Param("example") XslMasterLevelExperienceExample example);

    int updateByExample(@Param("record") XslMasterLevelExperience record, @Param("example") XslMasterLevelExperienceExample example);

    int updateByPrimaryKeySelective(XslMasterLevelExperience record);

    int updateByPrimaryKey(XslMasterLevelExperience record);
}