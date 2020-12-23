package com.secondhand.xiaoyi.service;

import com.secondhand.xiaoyi.entity.Action;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
public interface ActionService extends IService<Action> {

     /**
      * @author Gaosl
      * @description //向action表插入一条行为信息
      * @date 23:41 2020/12/9
      * @param userId, goodsWantId, actionSort,行为类别：g发布商品 w发布求购 b已购买
      * @return java.lang.Integer
      **/
     Integer saveAction(Long userId,Long goodsWantId,String actionSort);

     /**
      * @author Gaosl
      * @description //根据userId, actionSort，查询action记录
      * @date 20:20 2020/12/11
      * @param userId, actionSort
      * @return com.secondhand.xiaoyi.entity.Action
      **/
     List<Action> getByUserIdAndActionSort(Long userId, String actionSort );

}
