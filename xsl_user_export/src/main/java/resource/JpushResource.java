package resource;

import vo.JPushReqVo;
import vo.XslResult;

public interface JpushResource {

	XslResult sendByPhone(JPushReqVo jPushReqVo);

	XslResult sendAll(JPushReqVo jPushReqVo);

}
