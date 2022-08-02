package com.wqddg.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName PageHelper
 * @DateTime: 2021/11/19 17:56
 * @remarks : #分页的返回类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "返回值类型",description = "我们的返回值")
public class PageHelper<T> {


    //总记录数
    @ApiModelProperty(value = "总记录数")
    private int count;

    //总页数
    @ApiModelProperty(value = "总页数")
    private int pageCount;

    //分页数据
    @ApiModelProperty(value = "分页数据")
    private List<T> list;

}
