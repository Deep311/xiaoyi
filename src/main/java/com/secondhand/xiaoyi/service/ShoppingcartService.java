package com.secondhand.xiaoyi.service;

import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.entity.Shoppingcart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.xiaoyi.entity.VO.GoodsWantAndNeedCountVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
public interface ShoppingcartService extends IService<Shoppingcart> {

    /**
     * @param userId, goodsWantId
     * @return java.lang.Long
     * @author Gaosl
     * @description //根据userId, goodsWantId，查询ShopId,若购物车里没有记录者返回null。
     * @date 19:35 2020/12/10
     **/
    Long getShopId(Long userId, Long goodsWantId);


    /**
     * @author Gaosl
     * @description //根据 shopId，更新needCount
     * @date 20:02 2020/12/10
     * @param shopId, needCount
     * @return java.lang.Integer
     **/
    Integer updateNeedCountByShopId(Long shopId, Integer needCount);


    /**
     * @author Gaosl
     * @description //根据 userId，查询该用户购物车记录
     * @date 20:53 2020/12/10
     * @param userId
     * @return java.util.List<com.secondhand.xiaoyi.entity.Shoppingcart>
     **/
    List<GoodsWantAndNeedCountVO> getByUserId(Long userId);



}
