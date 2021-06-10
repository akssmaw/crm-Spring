package com.example.springboot.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("User-用户实体类")
public class User implements Serializable {

    @ApiModelProperty("ID")
    private int uid;

    private  int ustate;

    private  int admin;

    private  int getnext;

    private  int getnext2;

    private int savecount;

    @NotNull(message = "用户账号字段不能为空")
    @Size(min = 2, max = 5, message = "账号长度必须是2-5个字符")
    private String uname;

    @NotNull(message = "用户密码字段不能为空")
    @Size(min = 5, max = 12, message = "密码长度必须是5-12个字符")
    private String upassword;

    private int nownext2;
    private int initialcount;
    private int  group;
    private int authority;


}
