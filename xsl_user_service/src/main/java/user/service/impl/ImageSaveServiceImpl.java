package user.service.impl;

import com.xsl.user.vo.FileUploadReqVo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.FileUploadVo;
import user.service.ImageSaveService;
import util.FtpUtil;
import util.IdUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传服务
 */
@Service
public class ImageSaveServiceImpl implements ImageSaveService {
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private int FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public Map uploadPicture(FileUploadVo fileUploadReqVo) {
        Map<String, String> resultMap = new HashMap<>();

        try {
            byte[] bs = fileUploadReqVo.getBytes();
            if (StringUtils.isEmpty(bs)) {
                resultMap.put("error", "1");
                resultMap.put("message", "文件上传为空");
                return resultMap;
            }
            // 生成一个新的文件名
            // 取原始文件名
            String oldName = fileUploadReqVo.getOriginalFilename();
            // 生成新的文件名
            // UUID.randomUUID();
            String newName = IdUtil.genImageName();
            newName = newName + oldName.substring(oldName.indexOf("."));
            // 图片上传
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
                    FTP_BASE_PATH, imagePath, newName, fileUploadReqVo.getInputStream());
            // 返回结果
            if (!result) {
                resultMap.put("error", "1");
                resultMap.put("message", "文件上传失败");
                return resultMap;
            }

            resultMap.put("error", "0");
            resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
            return resultMap;

        } catch (Exception e) {
            resultMap.put("error", "1");
            resultMap.put("message", "文件上传失败");
            return resultMap;
        }
    }
}
