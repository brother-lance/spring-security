package org.dream.base.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 项目名称：security
 * 类 名 称：ImageCode
 * 类 描 述：图型验证码
 * 创建时间：2020/3/19 17:59
 * 创 建 人：Lance.WU
 */
@ToString
@AllArgsConstructor
@Getter
@Setter
public class ValidateCode {

    /** 验证码 */
    private String code;

    /** 有效时间 */
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);  // 当前时间添加
    }

    /**
     * 是否过期
     */
    public boolean isExpired() {
        return this.expireTime.compareTo(LocalDateTime.now()) == -1;
    }


}
