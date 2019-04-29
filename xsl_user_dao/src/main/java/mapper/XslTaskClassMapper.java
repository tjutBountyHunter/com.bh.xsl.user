package mapper;

import pojo.XslTag;

import java.util.List;

public interface XslTaskClassMapper {
    List<String> selectByXslTagCategory(XslTag xslTag);

    int selectByTaskName(String name);
}
