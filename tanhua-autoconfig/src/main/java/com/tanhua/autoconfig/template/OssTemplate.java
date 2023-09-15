package com.tanhua.autoconfig.template;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.tanhua.autoconfig.properties.OssProperties;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author: slx
 * @create: 2023/9/15
 */

public class OssTemplate {

    private OssProperties properties;

    public OssTemplate(OssProperties properties) {
        this.properties = properties;
    }

    /**
     * 文件上传
     * @param filename 文件名称
     * @param is 输入流
     * @return
     */
    public String upload(String filename, InputStream is) {
        filename = new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/"
                + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));

        // bucket所在地域对应的endpoint
        String endpoint = properties.getEndpoint();
        String accessKeyId = properties.getAccessKey();
        String accessKeySecret = properties.getSecret();

        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写byte数组
        // 填写bucket名称和object完整路径，object完整路径不能包含bucket名称
        ossClient.putObject(properties.getBucketName(), filename, is);

        // 关闭OSSClient
        ossClient.shutdown();
        String url = properties.getUrl() + "/" + filename;
        return url;
    }

}
