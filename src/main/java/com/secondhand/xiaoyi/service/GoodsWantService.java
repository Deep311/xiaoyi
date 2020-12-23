package com.secondhand.xiaoyi.service;

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
public interface GoodsWantService extends IService<GoodsWant> {

    /**
     * @author Gaosl
     * @description //模糊查询关键词queryKeyword、商品类别sortId、帖子类型goodsWant、搜索满足条件的商品/求购
     * @date 18:56 2020/12/9
     * @param queryKeyword, sortId, goodsWant
     * @return java.util.List<com.secondhand.xiaoyi.entity.GoodsWant>
     **/
     List<GoodsWant> getGoodsWant(String queryKeyword, Long sortId, String goodsWant);

    /**
     * @author Gaosl
     * @description //点击商品详情后，更新商品浏览量+1，
     * @date 22:40 2020/12/9
     * @param goodsWantId
     * @return java.lang.Integer
     **/
     Integer updateBrowsedCount(Long goodsWantId);

    /**
     * @author Gaosl
     * @description //修改商品图片存放地址
     * @date 22:40 2020/12/9
     * @param goodsWantId
     * @return java.lang.Integer
     **/
    Integer updateGoodsWantImage(Long goodsWantId,String imgPartName);

     /**
      * @author Gaosl
      * @description //逻辑删除，将isDelete字段置为true/1
      * @date 0:07 2020/12/11
      * @param goodsWantId
      * @return boolean
      **/
     boolean logicDelete(Long goodsWantId);

     /**
      * @author Gaosl
      * @description //根据goodsWantId更新action字段
      * @date 19:47 2020/12/11
      * @param goodsWantId
      * @return boolean
      **/
     boolean updateGoodsSalesById(Long goodsWantId,Integer needCount);

}
