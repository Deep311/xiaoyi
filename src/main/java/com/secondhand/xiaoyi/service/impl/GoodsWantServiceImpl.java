package com.secondhand.xiaoyi.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.mapper.GoodsWantMapper;
import com.secondhand.xiaoyi.service.GoodsWantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.xiaoyi.utils.ImgHandlerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.File;
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
public class GoodsWantServiceImpl extends ServiceImpl<GoodsWantMapper, GoodsWant> implements GoodsWantService {
    @Resource
    private GoodsWantMapper goodsWantMapper;

    /**
     * @param queryKeyword
     * @param sortId
     * @param goodsWant
     * @return java.util.List<com.secondhand.xiaoyi.entity.GoodsWant>
     * @author Gaosl
     * @description //模糊查询关键词queryKeyword、商品类别sortId、帖子类型goodsWant、搜索满足条件的商品/求购
     * @date 18:56 2020/12/9
     **/
    @Override
    public List<GoodsWant> getGoodsWant(String queryKeyword, Long sortId, String goodsWant) {
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("sort_id",sortId);
        map.put("goods_want",goodsWant);
        map.put("is_deleted",0);
        QueryWrapper<GoodsWant> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(map,false);
        if (!StringUtils.isEmpty(queryKeyword)) {
            queryWrapper.and(i -> i.like("goods_want_name",queryKeyword)
                    .or().like("goods_want_info",queryKeyword)
                    .or().like("goods_want_price",queryKeyword));
        }
        List<GoodsWant> goodsWants = goodsWantMapper.selectList(queryWrapper);
        //降序排列goodsWants
        Collections.sort(goodsWants);
        return goodsWants;
    }

    /**
     * @param goodsWantId
     * @return java.lang.Integer
     * @author Gaosl
     * @description //点击商品详情后，更新商品浏览量+1
     * @date 22:40 2020/12/9
     **/
    @Override
    public Integer updateBrowsedCount(Long goodsWantId) {
        UpdateWrapper<GoodsWant> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("browsed_count=browsed_count+1").eq("goods_Want_id",goodsWantId);
        return  goodsWantMapper.update(new GoodsWant(),updateWrapper);
    }

    /**
     * @param goodsWantId
     * @return java.lang.Integer
     * @author Gaosl
     * @description //修改商品图片存放地址
     * @date 22:40 2020/12/9
     **/
    @Override
    public Integer updateGoodsWantImage(Long goodsWantId ,String imgName) {
        UpdateWrapper<GoodsWant> updateWrapper = new UpdateWrapper<>();
        File file = new File(ImgHandlerUtil.IMGDIR+imgName);
        String goodsWantImage=imgName.replaceAll("xxx",goodsWantId.toString());
        file.renameTo(new File(ImgHandlerUtil.IMGDIR+goodsWantImage));
        updateWrapper.set("goods_want_image",goodsWantImage).eq("goods_want_id",goodsWantId);
        return goodsWantMapper.update(new GoodsWant(),updateWrapper);
    }

    /**
     * @param goodsWantId
     * @return boolean
     * @author Gaosl
     * @description //逻辑删除，将isDelete字段置为true/1
     * @date 0:07 2020/12/11
     **/
    @Override
    public boolean logicDelete(Long goodsWantId) {
        UpdateWrapper<GoodsWant> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("is_deleted=1").eq("goods_want_id",goodsWantId);
        int update = goodsWantMapper.update(new GoodsWant(), updateWrapper);
        if (update!=1) {
            return false;
        }
        return true;
    }

    /**
     * @param goodsWantId
     * @return boolean
     * @author Gaosl
     * @description //根据goodsWantId更新goodsSales字段
     * @date 19:47 2020/12/11
     **/
    @Override
    public boolean updateGoodsSalesById(Long goodsWantId,Integer needCount) {
        UpdateWrapper<GoodsWant> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("goods_sales=goods_sales+"+needCount).eq("goods_want_id",goodsWantId);
        if (goodsWantMapper.update(new GoodsWant(), updateWrapper)!=1) {
            return false;
        }
        return true;
    }


}
