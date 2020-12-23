package com.secondhand.xiaoyi.service;

import com.secondhand.xiaoyi.entity.Favorite;
import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.xiaoyi.entity.GoodsWant;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
public interface FavoriteService extends IService<Favorite> {

    /**
     * @author Gaosl
     * @description //根据userId, goodsWantId，查询favoriteId,若收藏夹里没有记录则返回null。
     * @date 20:51 2020/12/10
     * @param userId, goodsWantId
     * @return java.lang.Long
     **/
    Long getFavoriteId(Long userId, Long goodsWantId);



    /**
     * @author Gaosl
     * @description //根据 userId，查询该用户收藏夹记录
     * @date 20:51 2020/12/10
     * @param userId
     * @return java.util.List<com.secondhand.xiaoyi.entity.Favorite>
     **/
    List<GoodsWant> getByUserId(Long userId);

}
