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
    public List<Message> getMessages(Long goodsWantId);


}
