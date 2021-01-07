package com.secondhand.xiaoyi.controller;

import com.alibaba.fastjson.JSONObject;
import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.entity.Message;
import com.secondhand.xiaoyi.entity.VO.GoodsWantAndFavoriteVO;
import com.secondhand.xiaoyi.entity.VO.MessageVO;
import com.secondhand.xiaoyi.service.*;
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

    @Resource
    private UserService userService;

    @Resource
    private FavoriteService favoriteService;

    @ApiOperation(value = "搜索商品/求购:模糊查询关键词queryKeyword、商品类别sortId、帖子类型goodsWant、(检查商品是否被收藏)userId搜索满足条件的商品/求购")
    @PostMapping("searchGoods")
    public Result getGoods(@RequestBody String conditionStr){
        // 解析Json串
        JSONObject conditionJson = JSONObject.parseObject(conditionStr);
        String queryKeyword=conditionJson.getString("queryKeyword");
        Long sortId=conditionJson.getLong("sortId");
        String goodsWant=conditionJson.getString("goodsWant");
        Long userId=conditionJson.getLong("userId");
        List<GoodsWant> goodsWants = goodsWantService.getGoodsWant(queryKeyword,sortId,goodsWant);

        ArrayList<GoodsWantAndFavoriteVO> goodsWantAndFavoriteVOs = new ArrayList<>();
        for (GoodsWant goodswant : goodsWants) {
            goodswant.setGoodsWantImage(ImgHandlerUtil.imgHandlerRead(goodswant.getGoodsWantImage()));
            GoodsWantAndFavoriteVO goodsWantAndFavoriteVO = new GoodsWantAndFavoriteVO(null, goodswant);
            if (userId!=null) {
                goodsWantAndFavoriteVO.setFavoriteId(favoriteService.getFavoriteId(userId,goodswant.getGoodsWantId()));
            }
            goodsWantAndFavoriteVOs.add(goodsWantAndFavoriteVO);
        }
        return Result.success().data("items",goodsWantAndFavoriteVOs);
    }

    @ApiOperation(value = "商品/求购详情页：根据商品goodsWantId查询商品/求购信息；商品留言信息；更新商品浏览量；")
    @GetMapping("searchGoodsInfo")
    public Result getGoodsInfo(@RequestParam("goodsWantId") Long goodsWantId,@RequestParam("userId") Long userId){
        GoodsWant goodsWantInfo = goodsWantService.getById(goodsWantId);
        if (goodsWantInfo==null) {
            return Result.failure().message("查询失败");
        }
        goodsWantInfo.setGoodsWantImage(ImgHandlerUtil.imgHandlerRead(goodsWantInfo.getGoodsWantImage()));
        GoodsWantAndFavoriteVO goodsWantAndFavoriteVO = new GoodsWantAndFavoriteVO(null, goodsWantInfo);
        if (userId!=null) {
            goodsWantAndFavoriteVO.setFavoriteId(favoriteService.getFavoriteId(userId,goodsWantInfo.getGoodsWantId()));
        }
        List<Message> messageList = messageService.getMessages(goodsWantId);
        ArrayList<MessageVO> messageVOs = new ArrayList<>();
        for (Message message : messageList) {
            System.out.println(message.toString());
            String username = userService.getUserInfoById(message.getUserId()).getUsername();
            MessageVO messageVO = new MessageVO(username, message);
            messageVOs.add(messageVO);
        }
        Collections.sort(messageVOs);
        if (goodsWantService.updateBrowsedCount(goodsWantId)!=1) {
            return Result.failure().message("更新浏览量失败");
        }
        return Result.success().data("goodsWantInfo",goodsWantAndFavoriteVO).data("messageLists",messageVOs);
    }

    @ApiOperation(value = "发布商品/求购:输入goodsWant字段信息，插入一条商品/求购信息;同时在action表中插入一条消息")
    @PostMapping("releaseGoodsWant")
    public Result saveUserInfo(@RequestBody GoodsWant goodsWant){
        Boolean save;
        if (goodsWant.getGoodsWantImage()==null) {
             save = goodsWantService.save(goodsWant);
        }else {
            //写入图片
            String base64ImgStr=goodsWant.getGoodsWantImage();
            String imgPartName="/goodsWantImg/xxx";
            String imgName=ImgHandlerUtil.imgHandlerWrite(base64ImgStr,imgPartName);
            goodsWant.setGoodsWantImage(imgName);
            save = goodsWantService.save(goodsWant);
            goodsWantService.updateGoodsWantImage(goodsWant.getGoodsWantId(),imgName);
        }
        if (!save) {
            return Result.failure().message("发布失败");
        }
        //在action表中插入一条消息
        if (actionService.saveAction(goodsWant.getUserId(),goodsWant.getGoodsWantId(),goodsWant.getGoodsWant())!=1) {
            return Result.failure().message("更新action表失败");
        }

        return Result.success().data("goodsWantId",goodsWant.getGoodsWantId()).message("发布成功");
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

