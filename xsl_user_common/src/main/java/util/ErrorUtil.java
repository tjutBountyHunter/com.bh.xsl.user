package util;

import com.xsl.user.vo.ResBaseVo;
import org.springframework.beans.BeanUtils;

public class ErrorUtil {

	public static Object buildErrorInfo(Object o, Integer code, String msg){
		ResBaseVo resBaseVo =  ResBaseVo.build(code, msg);
		BeanUtils.copyProperties(resBaseVo, o);
		return o;
	}

}
