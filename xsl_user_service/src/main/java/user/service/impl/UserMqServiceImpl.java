package user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import user.service.UserMqService;
import util.GsonSingle;
import vo.UpdateTaskVo;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class UserMqServiceImpl implements UserMqService {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Resource
	private Destination updateNetwork;

	@Resource
	private Destination updateTaskInfo;


	@Override
	public void updateNetwork(String msg) {
		jmsTemplate.send(updateNetwork,(session -> session.createTextMessage(msg)));
	}

	@Override
	public void updateEsTask(UpdateTaskVo updateTaskVo) {
		String s = GsonSingle.getGson().toJson(updateTaskVo);
		jmsTemplate.send(updateTaskInfo, (session -> session.createTextMessage(s)));
	}


}
