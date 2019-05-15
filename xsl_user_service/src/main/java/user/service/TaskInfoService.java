package user.service;

import pojo.XslTag;
import pojo.XslTask;
import vo.XslResult;

import java.util.List;

public interface TaskInfoService {
	List<XslTag> getTaskTags(String taskId);

	XslResult sendMq(String msg);

	List<XslTask> getTaskByMasterId(String masterId);
}
