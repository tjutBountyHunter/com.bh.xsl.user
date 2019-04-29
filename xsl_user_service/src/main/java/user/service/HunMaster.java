package user.service;

import java.util.Map;

public interface HunMaster {
    Map<String, Integer> insertPeople(Integer userId);

    void insertLevel();
}
