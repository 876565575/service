package com.xc.file.controller;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.IdUtil;
import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.xc.file.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : 吴后荣
 * @date : 2019/12/19 10:49
 * @description :
 */

@Api("七牛云存储")
@RestController
@RequestMapping("/file")
public class QiNiuController {

    private final static String JPG = "jpg";
    private final static String PNG = "png";
    private final static String GIF = "gif";

    @Value(value = "${qiniu.accessKey}")
    private String accessKey;

    @Value(value = "${qiniu.secretKey}")
    private String secretKey;

    @Value(value = "${qiniu.bucket}")
    private String bucket;

    @ApiOperation("图片上传服务")
    @ApiImplicitParam(name = "file", value = "上传的文件", required = true)
    @PostMapping("/img/upload")
    public ResponseEntity imgUpload(@RequestBody MultipartFile file) {
        try {
            //判断文件类型
            String type = FileTypeUtil.getType(file.getInputStream());
            boolean flag = (type != null && (type.equals(JPG) || type.equals(PNG) || type.equals(GIF)));
            if (flag) {
                //获取文件名和后缀名
                String filename = file.getOriginalFilename();
                //文件保存地址
                String path = "img/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

                //构造一个带指定 Region 对象的配置类
                Configuration cfg = new Configuration(Region.region0());
                UploadManager uploadManager = new UploadManager(cfg);

                InputStream inputStream = file.getInputStream();
                //默认不指定key的情况下，以文件内容的hash值作为文件名
                String key = path + "/" + IdUtil.simpleUUID() + "." + type;
                Auth auth = Auth.create(accessKey, secretKey);
                String upToken = auth.uploadToken(bucket);
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return ResponseEntity.ok(putRet.key);
            }else {
                return ResponseEntity.badRequest().body("错误的文件类型!");
            }
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件保存失败！");
        }
    }

}
