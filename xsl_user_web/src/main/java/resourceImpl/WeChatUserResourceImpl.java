package resourceImpl;

import com.xsl.user.WeChatUserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import user.service.WeChatUserService;
import vo.ResBaseVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 22:00
 */
@Controller
public class WeChatUserResourceImpl implements WeChatUserResource {

    @Autowired
    private WeChatUserService weChatUserService;

    @Override
    public ResBaseVo getOpenIdAndSessionKey(String code, String nickName, String avatarUrl) {

        return weChatUserService.getOpenIdAndSessionKey(code,nickName,avatarUrl);
    }
}
