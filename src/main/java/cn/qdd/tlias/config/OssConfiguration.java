package cn.qdd.tlias.config;

import cn.qdd.tlias.utils.TencentCosUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OssConfiguration {

    @Bean
    public TencentCosUtil tencentCosUtil(TencentCosProperties tencentCosProperties) {
        log.info("开始创建腾讯云 COS 工具类：{}", tencentCosProperties);
        return new TencentCosUtil(
                tencentCosProperties.getSecretId(),
                tencentCosProperties.getSecretKey(),
                tencentCosProperties.getRegion(),
                tencentCosProperties.getBucketName()
        );
    }
}
