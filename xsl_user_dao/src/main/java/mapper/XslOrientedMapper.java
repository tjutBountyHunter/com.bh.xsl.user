package mapper;

import example.XslOrientedExample;
import org.apache.ibatis.annotations.Param;
import pojo.XslOriented;

import java.util.List;

public interface XslOrientedMapper {
    int countByExample(XslOrientedExample example);

    int deleteByExample(XslOrientedExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslOriented record);

    int insertSelective(XslOriented record);

    List<XslOriented> selectByExample(XslOrientedExample example);

    XslOriented selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslOriented record, @Param("example") XslOrientedExample example);

    int updateByExample(@Param("record") XslOriented record, @Param("example") XslOrientedExample example);

    int updateByPrimaryKeySelective(XslOriented record);

    int updateByPrimaryKey(XslOriented record);
}