package user.service;

import vo.UpdateTaskVo;

public interface UserMqService {
	void updateEsTask(UpdateTaskVo updateTaskVo);

	void updateNetwork(String msg);
}
