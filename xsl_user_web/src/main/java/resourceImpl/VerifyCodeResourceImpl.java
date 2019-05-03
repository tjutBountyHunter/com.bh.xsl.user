package resourceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import resource.VerifyCodeResource;
import user.service.VerifyCodeService;
import vo.XslResult;

@Controller
public class VerifyCodeResourceImpl implements VerifyCodeResource {
	@Autowired
	private VerifyCodeService verifyCodeService;


	@Override
	public XslResult sendMessageCode(String phone) {
		return verifyCodeService.sendMessageCode(phone);
	}

	@Override
	public XslResult checkCode(String phone, String code) {
		return verifyCodeService.checkCode(phone, code);
	}
}
