package com.wqddg.controller;

import com.wqddg.common.vo.ResultVo;
import com.wqddg.service.ProductCommentsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wqddg
 * @ClassName ProductCommentsController
 * @DateTime: 2021/11/19 12:05
 * @remarks : #
 */
@RestController
@RequestMapping("comments")
@Api(value = "商品评论接口",tags = "评论接口")
public class ProductCommentsController {


}
