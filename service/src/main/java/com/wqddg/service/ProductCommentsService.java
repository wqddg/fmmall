package com.wqddg.service;

import com.wqddg.common.vo.ResultVo;

/**
 * @Author: wqddg
 * @ClassName ProductCommentsService
 * @DateTime: 2021/11/19 12:03
 * @remarks : #
 */
public interface ProductCommentsService {
    public ResultVo selectCommentsUsers(String productId,int pageNum,int limit);

    /**
     * 根据商品id 统计当前商品的评价信息
     * @param productId
     * @return
     */
    public ResultVo getCommentsCount(String productId);
}
