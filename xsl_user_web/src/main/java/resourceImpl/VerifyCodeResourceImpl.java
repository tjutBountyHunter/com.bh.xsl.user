package resourceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.xsl.user.VerifyCodeResource;
import user.service.VerifyCodeService;
import vo.ResBaseVo;
import vo.XslResult;

@Controller
public class VerifyCodeResourceImpl implements VerifyCodeResource {
	@Autowired
	private VerifyCodeService verifyCodeService;


	@Override
	public ResBaseVo sendMessageCode(String phone) {
		XslResult xslResult = verifyCodeService.sendMessageCode(phone);
		ResBaseVo resBaseVo = new ResBaseVo();
		BeanUtils.copyProperties(xslResult, resBaseVo);
		return resBaseVo;
	}

	@Override
	public ResBaseVo checkCode(String phone, String code) {
		XslResult xslResult = verifyCodeService.checkCode(phone, code);
		ResBaseVo resBaseVo = new ResBaseVo();
		BeanUtils.copyProperties(xslResult, resBaseVo);
		return resBaseVo;
	}
}
