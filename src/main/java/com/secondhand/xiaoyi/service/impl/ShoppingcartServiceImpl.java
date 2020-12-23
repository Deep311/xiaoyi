package com.secondhand.xiaoyi.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.entity.Shoppingcart;
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
    public List<GoodsWant> getByUserId(Long userId) {
        QueryWrapper<Shoppingcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Shoppingcart> shoppingcarts = shoppingcartMapper.selectList(queryWrapper);
        ArrayList<GoodsWant> goodsWants = new ArrayList();
        for (Shoppingcart shoppingcart : shoppingcarts) {
            goodsWants.add(goodsWantService.getById(shoppingcart.getGoodsWantId()));
    }
        //热度按排列goodsWants
        Collections.sort(goodsWants,new Comparator<Object>(){
            @Override
            public int compare(Object o1, Object o2) {
                int i1 = handler(o1);
                int i2 = handler(o2);
                return i2-i1;
            }
            public int handler(Object o){
                int betweenDay = (int) DateUtil.between(((GoodsWant) o).getGmtCreate(), new Date(), DateUnit.DAY);
                int i = ((GoodsWant) o).getBrowsedCount() + ((GoodsWant) o).getCollectedCount() * 2;
                float s=(i==0?0:(i - 0.5f * 0.5f * betweenDay * betweenDay)/i);
                return (int)(i*s);
            }
        });
        return goodsWants;
    }
}
