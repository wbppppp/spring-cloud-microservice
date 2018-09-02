package com.itmuch.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/7/27 0027.
 */
@Configuration
public class Configuration1 {

    /**
     *  默认使用spring契约，这里配置为feign契约（这样就不能使用spring的注解了）
     *
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    /**
     * FeignClient日志打印
     *
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
