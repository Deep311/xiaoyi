package com.secondhand.xiaoyi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhand.xiaoyi.entity.Message;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
public interface MessageService extends IService<Message> {

    /**
     * @author Gaosl
     * @description //根据商品goodsWantId，查询该商品下的所有评论
     * @date 22:21 2020/12/9
     * @param goodsWantId
     * @return java.util.List<com.secondhand.xiaoyi.entity.Message>
     **/
     List<Message> getMessages(Long goodsWantId);

     /**
      * @author Gaosl
      * @description //展示用户的所有留言
      * @date 15:49 2020/12/27
      * @param userId
      * @return java.util.List<com.secondhand.xiaoyi.entity.Message>
      **/
    List<Message> showMessageByuserId(Long userId);



}
