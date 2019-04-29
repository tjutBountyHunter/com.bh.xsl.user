package mapper;

import pojo.XslAllHistoryHunter;
import pojo.XslHotHisory;
import pojo.XslHunterhistoryDefault;
import pojo.XslOneHunter;

import java.util.List;
import java.util.Map;

public interface XslHunterShopMapper {
    List<XslAllHistoryHunter> selectByThree(Map<String, Object> map);

    List<XslAllHistoryHunter> selectByThreenew(Map<String, Object> map);

    List<XslHunterhistoryDefault> selectBydefault(Integer userId);

    Integer getXslHunterCount(Map<String, Object> map);

    List<XslHotHisory> selectByHot(Integer rows);

    List<String> selectHotTag(Integer hunterId);

    XslOneHunter selectByhunterId(Integer hunterId);
}
