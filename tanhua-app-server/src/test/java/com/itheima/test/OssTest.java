package com.itheima.test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.tanhua.autoconfig.template.OssTemplate;
import com.tanhua.server.AppServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author: slx
 * @create: 2023/9/15
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppServerApplication.class)
public class OssTest {

    @Autowired
    private OssTemplate template;

    @Test
    public void testTemplateUpload() throws FileNotFoundException {
        String path = "";
        FileInputStream inputStream = new FileInputStream(new File(path));
        String imageUrl = template.upload(path, inputStream);
        System.out.println(imageUrl);
    }

    @Test
    public void testOss() throws FileNotFoundException {
        String path = "";
        FileInputStream inputStream = new FileInputStream(new File(path));
        String filename = new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/"
                + UUID.randomUUID().toString() + path.substring(path.lastIndexOf("."));
        String endpoint = "";
        String accessKeyId = "";
        String accessKeySecret = "";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject("tanhua001", filename, inputStream);
        ossClient.shutdown();
        String url = "" + filename;
        System.out.println(url);
    }

}
