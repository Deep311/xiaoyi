package com.secondhand.xiaoyi.controller;

import com.secondhand.xiaoyi.entity.Message;
import com.secondhand.xiaoyi.service.MessageService;
import com.secondhand.xiaoyi.utils.resultabout.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author Gaosl
 * @since 2020-12-05
 */
@RestController
@RequestMapping("/xiaoyi/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @ApiOperation(value = "写留言：根据商品goodsWantId、用户userId及留言内容content增加message表一条记录")
    @PostMapping("saveMessage")
    public Result saveMessage(@RequestBody Message message){
        boolean save = messageService.save(message);
        if (!save) {
            return Result.failure().message("未成功评论");
        }
        return Result.success().message("评论成功");
    }

    @ApiOperation("移除留言：输入messageId")
    @DeleteMapping("deleteMessage/{messageId}")
    public Result removeById (@PathVariable Long messageId){
        boolean remove = messageService.removeById(messageId);
        if (!remove) {
            return Result.failure().message("移除失败");
        }
        return Result.success().message("移除成功");
    }
}

