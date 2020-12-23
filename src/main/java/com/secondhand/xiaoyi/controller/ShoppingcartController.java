package com.secondhand.xiaoyi.controller;


import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.entity.Shoppingcart;
import com.secondhand.xiaoyi.service.ShoppingcartService;
import com.secondhand.xiaoyi.utils.ImgHandlerUtil;
import com.secondhand.xiaoyi.utils.resultabout.Result;
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
@RestController
@RequestMapping("/xiaoyi/shoppingcart")
public class ShoppingcartController {

    @Resource
    private ShoppingcartService shoppingcartService;

    @ApiOperation(value = "加入购物车：先判断shoppingcart表里是否已存在记录，若存在更新需求数量")
    @PostMapping("addToCart")
    public Result saveShoppingcart(@RequestBody Shoppingcart shoppingcart){
        Long userId = shoppingcart.getUserId();
        Long goodsWantId = shoppingcart.getGoodsWantId();
        Integer needCount = shoppingcart.getNeedCount();
        Long shopId = shoppingcartService.getShopId(userId, goodsWantId);
        if (shopId==null) {
            if (!shoppingcartService.save(shoppingcart)) {
                return Result.failure().message("加入购物车失败");
            }
        }else {
            if (shoppingcartService.updateNeedCountByShopId(shopId, needCount)!=1) {
                return Result.failure().message("更新购物车数据失败");
            }
        }
        return Result.success().message("操作成功");
    }

    @ApiOperation("查看购物车：根据用户userId查看购物车记录goodsWants")
    @GetMapping("showCartInfo/{userId}")
    public Result getByUserId (@PathVariable Long userId){
        List<GoodsWant> goodsWants = shoppingcartService.getByUserId(userId);
        for (GoodsWant goodsWant : goodsWants) {
            //读出图片
            goodsWant.setGoodsWantImage(ImgHandlerUtil.imgHandlerRead(goodsWant.getGoodsWantImage()));
        }
        return Result.success().data("items",goodsWants);
    }

    @ApiOperation("移除购物车记录：输入shopId")
    @DeleteMapping("deleteShoppingCart/{shopId}")
    public Result removeById (@PathVariable Long shopId){
        boolean remove = shoppingcartService.removeById(shopId);
        if (!remove) {
            return Result.failure().message("移除失败");
        }
        return Result.success().message("移除成功");
    }
}

