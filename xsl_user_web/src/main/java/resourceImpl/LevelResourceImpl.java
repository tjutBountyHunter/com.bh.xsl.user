package resourceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.xsl.user.LevelResource;
import user.service.LevelService;
import vo.ResBaseVo;
import vo.UserLevelReqVo;
@Controller
public class LevelResourceImpl implements LevelResource {
    @Autowired
    private LevelService levelService;

    @Override
    public ResBaseVo MasterAddEmpirical(UserLevelReqVo userLevelReqVo) {
        return levelService.MasterAddEmpirical(userLevelReqVo);
    }

    @Override
    public ResBaseVo HunterAddEmpirical(UserLevelReqVo userLevelReqVo) {
        return levelService.HunterAddEmpirical(userLevelReqVo);
    }
}
