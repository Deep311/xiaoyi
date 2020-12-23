package com.secondhand.xiaoyi.controller;
import com.alibaba.fastjson.JSONObject;
import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.entity.User;
import com.secondhand.xiaoyi.service.ActionService;
import com.secondhand.xiaoyi.service.GoodsWantService;
import com.secondhand.xiaoyi.service.UserService;
import com.secondhand.xiaoyi.utils.ImgHandlerUtil;
import com.secondhand.xiaoyi.utils.resultabout.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author Gaosl
 * @since 2020-12-05
 */
//@Transactional
@RestController
@RequestMapping("/xiaoyi/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private ActionService actionService;

    @Resource
    private GoodsWantService goodsWantService;

    @ApiOperation(value = "用户登录:根据手机号phonenum/用户名username+密码password,返回user对象")
    @PostMapping("login")
    public Result getUserInfo(@RequestBody User user){
        if(user!=null&& !StringUtils.isEmpty(user.getPassword())) {
             User userInfo = userService.getUserInfo(user);
            if(userInfo!=null){
                userInfo.setUserImg(ImgHandlerUtil.imgHandlerRead(userInfo.getUserImg()));
                return Result.success().data("items",userInfo);
            }
        }
        return Result.failure();
    }

    @ApiOperation(value = "用户注册:根据手机号phonenum/用户名username+密码password,插入一条用户信息")
    @PostMapping("register")
    public Result saveUserInfo(@RequestBody User user){
        String str=userService.saveUserInfo(user);
        if (str.charAt(0)=='f'){
           return Result.failure().message(str.substring(1));
        }else {
            return Result.success().message(str.substring(1));
        }
    }

    @ApiOperation(value = "个人主页:根据用户userId查询用户个人信息，返回user对象")
    @GetMapping("userInfo/{userId}")
    public Result getUserInfoById (@PathVariable Long userId){
        User userInfo = userService.getUserInfoById(userId);
        if (userInfo==null){
            return Result.failure().message("查询失败");
        }
        userInfo.setUserImg(ImgHandlerUtil.imgHandlerRead(userInfo.getUserImg()));
        return Result.success().data("items",userInfo);
    }

    @ApiOperation(value = "添加个人详细信息:user数据库字段")
    @PutMapping("addUserInfo")
    public Result addUserInfo(@RequestBody User user){
        String base64ImgStr=user.getUserImg();
        String imgPartName="/userImg/"+user.getUserId().toString();
        imgPartName=ImgHandlerUtil.imgHandlerWrite(base64ImgStr,imgPartName);
        user.setUserImg(imgPartName);
        if (!userService.updateById(user)) {
            return Result.failure();
        }
        return Result.success();
    }

    @ApiOperation(value = "购买模块：传入买方userId、商品帖goodsWantId、购买商品数needCount，更新双方钱包余额以及商品信息,以及我的行为表")
    @PutMapping("buy")
    public Result buy (@RequestParam String conditionStr){
        //解析Json串
        JSONObject conditionJson = JSONObject.parseObject(conditionStr);
        Long userId=conditionJson.getLong("userId");
        Long goodsWantId=conditionJson.getLong("goodsWantId");
        int needCount=conditionJson.getIntValue("needCount");

        //根据goodsWantId得到商品部分信息
        GoodsWant goodsWantInfo = goodsWantService.getById(goodsWantId);
        if (goodsWantInfo==null) {
            return Result.failure().message("查询失败");
       }

        //得到买方总购买金额
        if(needCount> goodsWantInfo.getGoodsWantCount()){
            return Result.failure().message("购买量超出余量");
        }
        BigDecimal totalAmount=goodsWantInfo.getGoodsWantPrice()
                .multiply(new BigDecimal(needCount));

        //得到买方用户钱包余额数
        BigDecimal walletBalance = userService.getWalletBalance(userId);
        if (totalAmount.compareTo(walletBalance)==1) {
            return Result.failure().message("余额不足");
        }

        //更新买方用户、卖方用户钱包余额
       userService.updateBuyerAndSellerWalletBalance(userId,goodsWantInfo.getUserId(),totalAmount);

        //更新action表
        if (actionService.saveAction(userId, goodsWantId, "b")!=1) {
            return Result.failure().message("更新action表失败");
        }

        //购买成功更新商品销量字段
        if (!goodsWantService.updateGoodsSalesById(goodsWantId,needCount)) {
            return Result.failure().message("更新销量字段失败");
        }

        //如果购入量等于商品存量，则逻辑删除商品
        if(needCount==goodsWantInfo.getGoodsWantCount()){
            if (!goodsWantService.logicDelete(goodsWantId)) {
                return Result.failure().message("删除商品失败");
            }
        }
        return Result.success().message("购买成功");
    }
}

