package com.sankuai.inf.leaf.plugin;

import com.sankuai.inf.leaf.exception.InitException;
import com.sankuai.inf.leaf.service.SegmentService;
import com.sankuai.inf.leaf.service.SnowflakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author zhaodong.xzd (github.com/yaccc)
 * @date 2019/10/09
 * @since support springboot starter with dubbo and etc rpc
 */
@Configuration
public class LeafSpringBootStarterAutoConfigure {
    private Logger logger = LoggerFactory.getLogger(LeafSpringBootStarterAutoConfigure.class);

    @Value("${leaf.name}")
    private String leafName;
    @Value("${leaf.segment.enable}")
    private boolean segmentEnable;
    @Value("${leaf.jdbc.url}")
    private String url;
    @Value("${leaf.jdbc.username}")
    private String username;
    @Value("${leaf.jdbc.password}")
    private String password;
    @Value("${leaf.snowflake.enable}")
    private boolean snowflakeEnable;
    @Value("${leaf.snowflake.zk.address}")
    private String address;
    @Value("${leaf.snowflake.port}")
    private int port;

    @Bean
    public SegmentService initLeafSegmentStarter() throws Exception {
        if (segmentEnable) {
            SegmentService segmentService = new SegmentService(url, username, password);
            return segmentService;
        }
        logger.warn("init leaf segment ignore properties is null");
        return null;
    }

    @Bean
    public SnowflakeService initLeafSnowflakeStarter() throws InitException {
        if (snowflakeEnable) {
            SnowflakeService snowflakeService = new SnowflakeService(address, port);
            return snowflakeService;
        }
        logger.warn("init leaf snowflake ignore properties is {}");
        return null;
    }
}
