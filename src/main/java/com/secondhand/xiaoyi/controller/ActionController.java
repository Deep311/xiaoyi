package com.secondhand.xiaoyi.controller;


import com.alibaba.fastjson.JSONObject;
import com.secondhand.xiaoyi.entity.Action;
import com.secondhand.xiaoyi.service.ActionService;
import com.secondhand.xiaoyi.utils.resultabout.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
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

    @ApiOperation("查看我的行为：根据userId和actionSort查看我的行为记录")
    @GetMapping("showActionInfo")
    public Result getActionInfo (@RequestParam String conditionStr){
        //解析Json串
        JSONObject conditionJson = JSONObject.parseObject(conditionStr);
        Long userId=conditionJson.getLong("userId");
        String actionSort = conditionJson.getString("actionSort");
        List<Action> actions = actionService.getByUserIdAndActionSort(userId, actionSort);
        //
        return Result.success().data("items",actions);
    }

}

