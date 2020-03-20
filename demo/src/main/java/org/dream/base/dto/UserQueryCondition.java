package org.dream.base.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 项目名称：security
 * 类 名 称：UserQueryCondition
 * 类 描 述：用户查询列表
 * 创建时间：2020/3/17 23:52
 * 创 建 人：Lance.WU
 */
@Getter
@Setter
@ToString
public class UserQueryCondition {

    @ApiModelProperty(value = "用户名称") // swagger说明
    private String userName;

    @ApiModelProperty(value = "年龄开始值") // swagger说明
    private int age;
    @ApiModelProperty(value = "年龄最大值") // swagger说明
    private int ageTo;
}
