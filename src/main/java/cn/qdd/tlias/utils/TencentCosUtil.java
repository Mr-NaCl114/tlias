package cn.qdd.tlias.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Data
@AllArgsConstructor
@Slf4j
public class TencentCosUtil {

    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;

    public String upload(byte[] bytes, String objectName) {
        COSCredentials cosCredentials = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cosCredentials, clientConfig);

        try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(bytes.length);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream, objectMetadata);
            cosClient.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException("上传文件到腾讯云 COS 失败", e);
        } finally {
            cosClient.shutdown();
        }

        String url = "https://" + bucketName + ".cos." + region + ".myqcloud.com/" + objectName;
        log.info("文件上传到: {}", url);
        return url;
    }
}
