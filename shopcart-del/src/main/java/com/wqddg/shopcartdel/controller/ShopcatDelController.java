package com.wqddg.shopcartdel.controller;

import com.wqddg.shopcartdel.service.ShopcartDelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wqddg
 * @ClassName ShopcatDelController
 * @DateTime: 2021/12/19 22:02
 * @remarks : #
 */
@RestController
public class ShopcatDelController {

    @Autowired
    private ShopcartDelService delService;

    @DeleteMapping("/shopcart/del")
    public int delect(String cids){
        return delService.delectShopcart(cids);
    }
}
