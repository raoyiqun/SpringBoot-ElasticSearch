package cn.thislx.springbootes.utils;

import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: LX
 * @Description:
 * @Date: Created in 11:21 2018/11/6
 * @Modified by:
 */
@Component
public class ElasticsearchUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchUtil.class);

    @Autowired
    private TransportClient transportClient;

    public static TransportClient client;

    /**
     * @PostContruct是spring框架的注解 spring容器初始化的时候执行该方法
     */
    @PostConstruct
    public void init() {
        client = this.transportClient;
    }

    public static TransportClient getTransportClient(){
        return client;
    }

}