package com.secondhand.xiaoyi.controller;

import com.alibaba.fastjson.JSONObject;
import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.entity.Message;
import com.secondhand.xiaoyi.service.ActionService;
import com.secondhand.xiaoyi.service.GoodsWantService;
import com.secondhand.xiaoyi.service.MessageService;
import com.secondhand.xiaoyi.utils.ImgHandlerUtil;
import com.secondhand.xiaoyi.utils.resultabout.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
@Slf4j
@RestController
@RequestMapping("/xiaoyi/goods-want")
public class GoodsWantController {
    @Resource
    private GoodsWantService goodsWantService;

    @Resource
    private MessageService messageService;

    @Resource
    private ActionService actionService;

    @ApiOperation(value = "搜索商品/求购:模糊查询关键词queryKeyword、商品类别sortId、帖子类型goodsWant、搜索满足条件的商品/求购")
    @PostMapping("searchGoods")
    public Result getGoods(@RequestBody String conditionStr){
        // 解析Json串
        JSONObject conditionJson = JSONObject.parseObject(conditionStr);
        String queryKeyword=conditionJson.getString("queryKeyword");
        Long sortId=conditionJson.getLong("sortId");
        String goodsWant=conditionJson.getString("goodsWant");
        List<GoodsWant> goodsWants = goodsWantService.getGoodsWant(queryKeyword,sortId,goodsWant);
        for (GoodsWant want : goodsWants) {
            want.setGoodsWantImage(ImgHandlerUtil.imgHandlerRead(want.getGoodsWantImage()));
        }
        return Result.success().data("items",goodsWants);
    }

    @ApiOperation(value = "商品/求购详情页：根据商品goodsWantId查询商品/求购信息；商品留言信息；更新商品浏览量；")
    @GetMapping("searchGoodsInfo/{goodsWantId}")
    public Result getGoodsInfo(@PathVariable Long goodsWantId){
        GoodsWant goodsWantInfo = goodsWantService.getById(goodsWantId);
        goodsWantInfo.setGoodsWantImage(ImgHandlerUtil.imgHandlerRead(goodsWantInfo.getGoodsWantImage()));
        if (goodsWantInfo==null) {
            return Result.failure().message("查询失败");
        }
        List<Message> messageList = messageService.getMessages(goodsWantId);
        if (goodsWantService.updateBrowsedCount(goodsWantId)!=1) {
            return Result.failure().message("更新浏览量失败");
        }
        return Result.success().data("goodsWantInfo",goodsWantInfo).data("messageList",messageList);
    }

    @ApiOperation(value = "发布商品/求购:输入goodsWant字段信息，插入一条商品/求购信息;同时在action表中插入一条消息")
    @PostMapping("releaseGoodsWant")
    public Result saveUserInfo(@RequestBody GoodsWant goodsWant){
        //写入图片
        String base64ImgStr=goodsWant.getGoodsWantImage();
        String imgPartName="/goodsWantImg/xxx";
        String imgName=ImgHandlerUtil.imgHandlerWrite(base64ImgStr,imgPartName);
        goodsWant.setGoodsWantImage(imgName);
        log.info(imgName);
        boolean save = goodsWantService.save(goodsWant);

        goodsWantService.updateGoodsWantImage(goodsWant.getGoodsWantId(),imgName);
        if (!save) {
            return Result.failure().message("发布失败");
        }
        //在action表中插入一条消息
        if (actionService.saveAction(goodsWant.getUserId(),goodsWant.getGoodsWantId(),"g")!=1) {
            return Result.failure().message("更新action表失败");
        }
        return Result.success().message("发布成功");
    }

    @ApiOperation("逻辑删除商品/求购帖：输入goodsWantId")
    @DeleteMapping("deleteGoodsWantInfo/{goodsWantId}")
    public Result removeById (@PathVariable Long goodsWantId){
        boolean remove = goodsWantService.logicDelete(goodsWantId);
        if (!remove) {
            return Result.failure().message("移除失败");
        }
        return Result.success().message("移除成功");
    }
}

