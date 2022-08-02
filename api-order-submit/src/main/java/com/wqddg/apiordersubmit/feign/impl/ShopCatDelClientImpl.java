package com.wqddg.apiordersubmit.feign.impl;

import com.wqddg.apiordersubmit.feign.ShopCatDelClient;
import org.springframework.stereotype.Component;

/**
 * @Author: wqddg
 * @ClassName ShopCatDelClientImpl
 * @DateTime: 2021/12/20 19:21
 * @remarks : #
 */
@Component
public class ShopCatDelClientImpl implements ShopCatDelClient {
    @Override
    public int delect(String cids) {
        return 0;
    }
}
