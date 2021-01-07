package com.secondhand.xiaoyi.controller;


import com.alibaba.fastjson.JSONObject;
import com.secondhand.xiaoyi.entity.Action;
import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.entity.VO.BuyActionVO;
import com.secondhand.xiaoyi.service.ActionService;
import com.secondhand.xiaoyi.service.GoodsWantService;
import com.secondhand.xiaoyi.utils.ImgHandlerUtil;
import com.secondhand.xiaoyi.utils.resultabout.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author Gaosl
 * @since 2020-12-05
 */
@Api(value = "我的行为模块，发布商品，发布求购，已购买")
@RestController
@RequestMapping("/xiaoyi/action")
public class ActionController {

    @Resource
    private ActionService actionService;

    @Resource
    GoodsWantService goodsWantService;

    @ApiOperation("查看我的行为：根据userId和actionSort查看我的行为记录")
    @GetMapping("showActionInfo")
    public Result getActionInfo (@RequestParam("userId") Long userId,@RequestParam("actionSort")String actionSort){
        List<Action> actions = actionService.getByUserIdAndActionSort(userId, actionSort);
        ArrayList<BuyActionVO> buyActionVOs = new ArrayList<>();
        for (Action action : actions) {
            GoodsWant goodsWant = goodsWantService.getById(action.getGoodsWantId());
            goodsWant.setGoodsWantImage(ImgHandlerUtil.imgHandlerRead(goodsWant.getGoodsWantImage()));
            BuyActionVO buyActionVO = new BuyActionVO(action, goodsWant);
            buyActionVOs.add(buyActionVO);
        }
        Collections.sort(buyActionVOs);
        return Result.success().data("items",buyActionVOs);
    }


    @ApiOperation("删除我的行为：")
    @DeleteMapping("deleteAction/{actionId}")
    public Result deleteAction (@PathVariable Long actionId ){
        boolean b = actionService.removeById(actionId);
        if (!b) {
            return Result.failure().message("删除失败");
        }
        return Result.success().message("删除成功");
    }




}

