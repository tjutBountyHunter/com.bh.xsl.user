package mapper;

import vo.XslSchool;

import java.util.List;

public interface XslSchoolMessageMapper {
    List<String> selectByXslSchool();

    int selectBySchoolName(String schoolName);

    List<XslSchool> selectSchoolList();
}
