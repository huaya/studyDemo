package com.maxlong.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-20 16:02
 */
@Setter
@Getter
@Slf4j
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisProperties {

    private List<String> nodes;

    private Integer connectionTimeout;

    private Integer soTimeout;

    private Integer maxAttempts;

    private String password;

    private Integer maxRedirects;

    private Integer maxTotal;

    private Integer maxIdle;

    private Integer maxWaitMillis;

    private boolean testOnBorrow;

    private boolean testOnCreate;

    private boolean testOnReturn;

    private boolean testWhileIdle;

    private boolean blockWhenExhausted;

    private int numTestsPerEvictionRun;

    private long softMinEvictableIdleTimeMillis;

    private long timeBetweenEvictionRunsMillis;

    private long minEvictableIdleTimeMillis;

    private boolean clusterMode;

    private boolean viewable;

    private String host;

    private Integer port;

    private String keySerializer;

    private String hashKeySerializer;

    private String hashValueSerializer;

    private String valueSerializer;

}
