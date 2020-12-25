package com.secondhand.xiaoyi.controller;


import com.secondhand.xiaoyi.entity.Favorite;
import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.service.FavoriteService;
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
@RequestMapping("/xiaoyi/favorite")
public class FavoriteController {

    @Resource
    private FavoriteService favoriteService;

    @ApiOperation(value = "加入收藏夹：输入favorite字段信息，先判断favorite表里是否已存在记录，若存在则返回‘已添加’")
    @PostMapping("addToFavorite")
    public Result saveFavorite(@RequestBody Favorite favorite){
        Long userId = favorite.getUserId();
        Long goodsWantId = favorite.getGoodsWantId();
        Long favoriteId = favoriteService.getFavoriteId(userId, goodsWantId);
        if (favoriteId!=null) {
            boolean remove = favoriteService.removeById(favoriteId);
            if (!remove) {
                return Result.failure().message("移除失败");
            }
            return Result.success().message("移除成功");
        }
        if (!favoriteService.save(favorite)) {
            return Result.failure().message("更新收藏夹数据失败");
        }
        return Result.success().message("操作成功");
    }

    @ApiOperation("查看收藏夹：根据用户userId查看收藏夹记录goodsWants")
    @GetMapping("getFavoriteInfo/{userId}")
    public Result getAllByUserId (@PathVariable Long userId){
        List<GoodsWant> goodsWants = favoriteService.getByUserId(userId);
        for (GoodsWant goodsWant : goodsWants) {
            //读出图片
            goodsWant.setGoodsWantImage(ImgHandlerUtil.imgHandlerRead(goodsWant.getGoodsWantImage()));
        }
        return Result.success().data("items",goodsWants);
    }

    @ApiOperation("移除收藏夹记录：输入favoriteId")
    @DeleteMapping("deleteFavoriteInfo/{favoriteId}")
    public Result removeById (@PathVariable Long favoriteId){
        boolean remove = favoriteService.removeById(favoriteId);
        if (!remove) {
            return Result.failure().message("移除失败");
        }
        return Result.success().message("移除成功");
    }
}

