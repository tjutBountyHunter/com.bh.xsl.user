package resourceImpl;

import com.xsl.user.FileOperateResource;
import com.xsl.user.vo.FileUploadReqVo;
import com.xsl.user.vo.FileVo;
import mapper.XslFileMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pojo.XslFile;
import user.service.ImageSaveService;
import util.ErrorUtil;

import java.util.Map;
import java.util.UUID;

@Controller
public class FileOperateResourceImpl implements FileOperateResource {
	@Autowired
	private ImageSaveService imageSaveService;
	@Autowired
	private XslFileMapper xslFileMapper;

	/**
	 * 上传图片
	 *
	 * @param fileUploadReqVo
	 * @return
	 */
	@Override
	public FileVo fileUpload(FileUploadReqVo fileUploadReqVo) {
		//初始化文件信息
		FileVo fileVo = new FileVo();
		fileVo.setFileid(UUID.randomUUID().toString());

		try {
			Map map = imageSaveService.uploadPicture(fileUploadReqVo);
			if ("1".equals(map.get("error"))) {
				return (FileVo) ErrorUtil.buildErrorInfo(fileVo, 500, "图片上传失败");
			}

			fileVo.setUrl((String) map.get("url"));
			XslFile xslFile = new XslFile();
			BeanUtils.copyProperties(fileVo, xslFile);
			int insert = xslFileMapper.insertSelective(xslFile);

			if(insert < 1){
				return (FileVo) ErrorUtil.buildErrorInfo(fileVo, 500, "服务器异常");
			}

			return fileVo;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
