package listener;

import org.springframework.beans.factory.annotation.Autowired;
import user.service.UserNetworkService;
import util.GsonSingle;
import vo.NetworkReqVo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class UpdateNetworkListener implements MessageListener {
	@Autowired
	private UserNetworkService userNetworkService;

	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage) message;
		try {
			String msg = text.getText();
			NetworkReqVo networkReqVo = GsonSingle.getGson().fromJson(msg, NetworkReqVo.class);
			userNetworkService.updateNetwork(networkReqVo);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
