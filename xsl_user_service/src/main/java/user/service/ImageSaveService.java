package user.service;

import com.xsl.user.vo.FileUploadReqVo;
import pojo.FileUploadVo;

import java.util.Map;

/**
 * 照片处理
 *
 * @author 高山潍
 */
public interface ImageSaveService {
    /**
     * 照片储存
     *
     * @param fileUploadReqVo
     * @return
     */
    Map uploadPicture(FileUploadVo fileUploadReqVo);
}
