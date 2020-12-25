package com.secondhand.xiaoyi.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.entity.Shoppingcart;
import com.secondhand.xiaoyi.entity.VO.GoodsWantAndNeedCountVO;
import com.secondhand.xiaoyi.mapper.ShoppingcartMapper;
import com.secondhand.xiaoyi.service.GoodsWantService;
import com.secondhand.xiaoyi.service.ShoppingcartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ShoppingcartServiceImpl extends ServiceImpl<ShoppingcartMapper, Shoppingcart> implements ShoppingcartService {

    @Resource
    private ShoppingcartMapper shoppingcartMapper;

    @Resource
    private GoodsWantService goodsWantService;

    /**
     * @param userId
     * @param goodsWantId
     * @return java.lang.Long
     * @author Gaosl
     * @description //根据userId, goodsWantId，查询ShopId,若收藏车里没有记录者返回null。
     * @date 19:35 2020/12/10
     **/
    @Override
    public Long getShopId(Long userId, Long goodsWantId) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("shop_id").eq("user_id",userId).eq("goods_want_id",goodsWantId);
        Shoppingcart shoppingcart = shoppingcartMapper.selectOne(queryWrapper);
        if (shoppingcart==null) {
            return null;
        }
        return shoppingcart.getShopId();
    }

    /**
     * @param shopId
     * @param needCount
     * @return java.lang.Integer
     * @author Gaosl
     * @description //根据 shopId，更新needCount
     * @date 20:02 2020/12/10
     **/
    @Override
    public Integer updateNeedCountByShopId(Long shopId, Integer needCount) {
        UpdateWrapper<Shoppingcart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("need_count=need_count+"+needCount).eq("shop_id",shopId);
        return shoppingcartMapper.update(new Shoppingcart(), updateWrapper);
    }

    @Override
    public List<GoodsWantAndNeedCountVO> getByUserId(Long userId) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Shoppingcart> shoppingcarts = shoppingcartMapper.selectList(queryWrapper);
        Collections.sort(shoppingcarts);
        ArrayList<GoodsWantAndNeedCountVO> goodsWantAndNeedCountVOs = new ArrayList();
        for (Shoppingcart shoppingcart : shoppingcarts) {
            GoodsWantAndNeedCountVO goodsWantAndNeedCountVO = new GoodsWantAndNeedCountVO(shoppingcart.getShopId(),shoppingcart.getNeedCount(), goodsWantService.getById(shoppingcart.getGoodsWantId()));
            goodsWantAndNeedCountVOs.add(goodsWantAndNeedCountVO);
        }
        return goodsWantAndNeedCountVOs;
    }
}
