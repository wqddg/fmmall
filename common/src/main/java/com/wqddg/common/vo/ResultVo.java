package com.wqddg.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wqddg
 * @ClassName ResultVo
 * @DateTime: 2021/11/12 22:37
 * @remarks : #我们的json数据返回
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "返回值类型",description = "我们的返回值")
public class ResultVo {
    //状态码
    @ApiModelProperty(value = "状态码")
    private int code;
    //响应给前端的信息
    @ApiModelProperty(value = "信息")
    private String msg;
    @ApiModelProperty(value = "数据")
    private Object data;
}
