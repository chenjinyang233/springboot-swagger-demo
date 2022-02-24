package com.swagger.demo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel("用户实体类")
public class UserBean {
    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("年龄")
    private int age;
    @ApiModelProperty("密码")
    private String password;

}