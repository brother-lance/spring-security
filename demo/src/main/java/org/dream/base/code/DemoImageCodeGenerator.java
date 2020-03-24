/**
 * 
 */
package org.dream.base.code;

import org.dream.base.core.validate.code.ValidateCode;
import org.dream.base.core.validate.code.ValidateCodeGenerator;
import org.dream.base.core.validate.code.image.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;


/**
 * @author zhailiang
 *
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	/* (non-Javadoc)
	 * @see com.imooc.security.core.validate.code.ValidateCodeGenerator#generate(org.springframework.web.context.request.ServletWebRequest)
	 */
	@Override
	public ImageCode generate(ServletWebRequest request) {
		System.out.println("更高级的图形验证码生成代码");
		return null;
	}

}
