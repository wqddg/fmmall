package com.wqddg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wqddg.common.utils.Base64Utils;
import com.wqddg.common.vo.ResStatus;
import com.wqddg.common.vo.ResultVo;
import com.wqddg.entity.ShoppingCart;
import com.wqddg.entity.Users;
import com.wqddg.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import java.util.Date;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ShopcartController
 * @DateTime: 2021/11/15 21:01
 * @remarks : #
 */
@RestController
@Api(value = "提供用户购物车",tags = "购物车管理")
@RequestMapping("shopcart")
public class ShopcartController {

    @Autowired
    public ShoppingCartService cartService;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("list")
    @ApiOperation("用户购物车接口")
    @ApiImplicitParam(value = "用户id",name = "userId")
    public ResultVo listCarts(String userId){
        return cartService.findAllShoppingCart(userId);
    }


    @PostMapping("addcar")
    public ResultVo addShoppingCart(@RequestBody ShoppingCart cart,@RequestHeader("token")String token) throws JsonProcessingException {
        String user_token = stringRedisTemplate.boundValueOps(token).get();
        Users users = objectMapper.readValue(user_token, Users.class);


        return cartService.addshoppingCart(cart);
    }

    @PutMapping("updata/{cid}/{num}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id",name = "carId",required = true,dataType = "int"),
            @ApiImplicitParam(value = "用户数量",name = "carNum",required = true,dataType = "int")
    })
    public ResultVo updataByCarId(@PathVariable("cid") Integer carId,@PathVariable("num") Integer carNum,@RequestHeader("token") String token){
        return cartService.updataShoppingCart(carId,carNum);
    }



    @GetMapping("listcarId")
    @ApiOperation("勾选的购物车物品")
    @ApiImplicitParam(value = "选择的购物车记录的id",name = "cids",required = true,dataType = "string")
    public ResultVo findAllByCids(String cids,@RequestHeader("token")String token){
        return cartService.listShoppingCartByCids(cids);
    }
}
