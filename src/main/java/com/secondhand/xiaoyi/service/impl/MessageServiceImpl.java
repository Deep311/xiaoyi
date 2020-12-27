package com.secondhand.xiaoyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.xiaoyi.entity.Message;
import com.secondhand.xiaoyi.mapper.MessageMapper;
import com.secondhand.xiaoyi.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    /**
     * @param goodsWantId
     * @return java.util.List<com.secondhand.xiaoyi.entity.Message>
     * @author Gaosl
     * @description //根据商品goodsWantId，查询该商品下的所有评论
     * @date 22:21 2020/12/9
     **/
    @Override
    public List<Message> getMessages(Long goodsWantId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_want_id", goodsWantId).orderByAsc("message_floor");
        return messageMapper.selectList(queryWrapper);
    }

    /**
     * @param userId
     * @return java.util.List<com.secondhand.xiaoyi.entity.Message>
     * @author Gaosl
     * @description //展示用户的所有留言
     * @date 15:49 2020/12/27
     **/
    @Override
    public List<Message> showMessageByuserId(Long userId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Message> messageList = messageMapper.selectList(queryWrapper);
        return messageList;
    }
}

