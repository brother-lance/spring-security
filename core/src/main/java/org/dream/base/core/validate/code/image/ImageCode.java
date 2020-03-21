package org.dream.base.core.validate.code.image;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.dream.base.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;

/**
 * 项目名称：security
 * 类 名 称：ImageCode
 * 类 描 述：图型验证码
 * 创建时间：2020/3/19 17:59
 * 创 建 人：Lance.WU
 */
@ToString
@Getter
@Setter
public class ImageCode extends ValidateCode {

    BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

}
