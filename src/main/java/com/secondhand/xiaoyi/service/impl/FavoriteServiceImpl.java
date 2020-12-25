package com.secondhand.xiaoyi.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.xiaoyi.entity.Favorite;
import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.mapper.FavoriteMapper;
import com.secondhand.xiaoyi.service.FavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.xiaoyi.service.GoodsWantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {


    @Resource
    private FavoriteMapper favoriteMapper;

    @Resource
    private GoodsWantService goodsWantService;
    /**
     * @param userId
     * @param goodsWantId
     * @return java.lang.Long
     * @author Gaosl
     * @description //根据userId, goodsWantId，查询favoriteId,若收藏夹里没有记录则返回null。
     * @date 20:51 2020/12/10
     **/
    @Override
    public Long getFavoriteId(Long userId, Long goodsWantId) {
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("favorite_id").eq("user_id",userId).eq("goods_want_id",goodsWantId);
        Favorite favorite = favoriteMapper.selectOne(queryWrapper);
        if (favorite==null) {
            return null;
        }
        return favorite.getFavoriteId();
    }


    /**
     * @param userId
     * @return java.util.List<com.secondhand.xiaoyi.entity.Favorite>
     * @author Gaosl
     * @description //根据 userId，查询该用户收藏夹记录
     * @date 20:51 2020/12/10
     **/
    @Override
    public List<GoodsWant> getByUserId(Long userId) {
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Favorite> favorites = favoriteMapper.selectList(queryWrapper);
        Collections.sort(favorites);
        ArrayList<GoodsWant> goodsWants = new ArrayList();
        for (Favorite favorite : favorites) {
            goodsWants.add(goodsWantService.getById(favorite.getGoodsWantId()));
        }
        return goodsWants;
    }
}
