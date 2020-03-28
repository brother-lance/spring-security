package org.dream.base.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.dream.base.validator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * 项目名称：security
 * 类 名 称：User
 * 类 描 述：
 * 创建时间：2020/3/17 23:39
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
@ToString
public class User {

    /**
     * 返回客户端数据帅选视图
     */
    public interface UserSimpleView {
    }

    public interface UserSimpleDetailView extends UserSimpleView {
    }

    @JsonView(UserSimpleView.class)
    private String id;

    @MyConstraint
    @JsonView(UserSimpleView.class)
    @ApiModelProperty(value = "用户名称") // swagger说明
    private String userName;

    @NotBlank(message = "密码不能为空")
    @JsonView(UserSimpleDetailView.class)
    private String password;

    @Past(message = "生日必须是过去时间")
    @JsonView(UserSimpleDetailView.class)
    private Date birthday;

}
