package tech.fantasywk.ucenter.qrcode.login.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.fantasywk.ucenter.qrcode.login.model.Person;

import java.util.concurrent.TimeUnit;

/**
 * 缓存
 *
 * @author wangkai  2019/7/20
 */
@Configuration
public class CacheConfig {

    /**
     * token与用户缓存关系缓存
     * 2分钟过期时间
     */
    @Bean
    public Cache<String, Person> qrCodeLoginCache() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(2,TimeUnit.MINUTES)
                .build();
    }
}
