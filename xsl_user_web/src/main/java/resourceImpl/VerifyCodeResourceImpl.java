package resourceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.xsl.user.VerifyCodeResource;
import user.service.VerifyCodeService;
import com.xsl.user.vo.ResBaseVo;

@Controller
public class VerifyCodeResourceImpl implements VerifyCodeResource {
	@Autowired
	private VerifyCodeService verifyCodeService;


	@Override
	public ResBaseVo sendMessageCode(String phone) {
		ResBaseVo resBaseVo;
		try {
			resBaseVo = verifyCodeService.sendMessageCode(phone);
		}catch (Exception e){
			throw new RuntimeException(e);
		}

		return resBaseVo;
	}

	@Override
	public ResBaseVo checkCode(String phone, String code) {
		ResBaseVo resBaseVo;
		try {
			resBaseVo = verifyCodeService.checkCode(phone, code);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
		return resBaseVo;
	}
}
