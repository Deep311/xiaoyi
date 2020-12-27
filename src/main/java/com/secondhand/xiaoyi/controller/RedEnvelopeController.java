package com.secondhand.xiaoyi.controller;

import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.entity.Message;
import com.secondhand.xiaoyi.entity.VO.GoodsWantAndFavoriteVO;
import com.secondhand.xiaoyi.entity.VO.MessageVO;
import com.secondhand.xiaoyi.service.RedEnvelopeService;
import com.secondhand.xiaoyi.service.impl.RedEnvelopeServiceImpl;
import com.secondhand.xiaoyi.utils.ImgHandlerUtil;
import com.secondhand.xiaoyi.utils.resultabout.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName RedEnvelopeController
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/12/27 20:54
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/xiaoyi/red-envelope")
public class RedEnvelopeController {

    @Resource
    RedEnvelopeService redEnvelopeService;

    @ApiOperation(value = "查询红包活动是否开始")
    @GetMapping("RedEnvelope/isStart")
    public Result isStart(){
        if (RedEnvelopeServiceImpl.TOTAL_AMOUNT_OF_RED_ENVELOP==null) {
            return Result.failure().message("红包活动未开始");
        }
        return Result.success().data("TOTAL_AMOUNT_OF_RED_ENVELOP",RedEnvelopeServiceImpl.TOTAL_AMOUNT_OF_RED_ENVELOP);
    }

    @ApiOperation(value = "抢红包：输入用户ID，返回抢到红包余额")
    @GetMapping("RedEnvelope/getRedEnvelope/{userId}")
    public Result getRedEnvelope(@PathVariable Long userId){
        HashMap<String, Object> redEnvelope = redEnvelopeService.getRedEnvelope(userId);
        if (redEnvelope==null) {
            return Result.failure().message("红包已被抢完");
        }
        if (redEnvelope.get("money")==null) {
            return Result.failure().message("已抢过红包");
        }
        return Result.success().data("redEnvelopeMoney",redEnvelope.get("money")).message("恭喜抢到红包");
    }
}
