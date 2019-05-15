package user.service;

import pojo.XslTag;
import pojo.XslTask;

import java.util.List;

public interface TaskInfoService {
	List<XslTag> getTaskTags(String taskId);

	List<XslTask> getTaskByMasterId(String masterId);
}
