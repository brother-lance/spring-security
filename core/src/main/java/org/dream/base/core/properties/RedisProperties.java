package org.dream.base.core.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

/**
 * 项目名称：security
 * 类 名 称：RedisProperties
 * 类 描 述：
 * 创建时间：2020/3/28 16:04
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
public class RedisProperties {

    /**
     * 获取Redis设备的Key
     */
    private String codeKey = "deviceId";

    /**
     * 验证获取的有效时间 ，以秒为单
     */
    private long codeExpireTime = 60;

}
