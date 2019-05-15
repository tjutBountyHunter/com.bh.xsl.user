package user.service;

import vo.CreateOrderReqVo;
import vo.TaskEsInfo;
import vo.UpdateTaskVo;

public interface TaskMqService {

	void updateEsTask(UpdateTaskVo updateTaskVo);

	void addEsTask(TaskEsInfo taskEsInfo);

	void createOrder(CreateOrderReqVo createOrderReqVo);

	void updateNetwork(String msg);


}
