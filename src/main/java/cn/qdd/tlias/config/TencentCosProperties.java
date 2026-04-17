package cn.qdd.tlias.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tencent.cos")
@Data
public class TencentCosProperties {

    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;

}
