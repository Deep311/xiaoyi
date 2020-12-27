package com.secondhand.xiaoyi.entity.VO;

import com.secondhand.xiaoyi.entity.GoodsWant;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName GoodsWantAndFavoriteVO
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/12/25 19:29
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class GoodsWantAndFavoriteVO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long favoriteId;

    private GoodsWant goodsWant;

}
