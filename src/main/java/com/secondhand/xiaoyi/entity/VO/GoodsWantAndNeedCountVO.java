package com.secondhand.xiaoyi.entity.VO;

import com.secondhand.xiaoyi.entity.GoodsWant;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName GoodsWantAndNeedCountVO
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/12/25 17:10
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class GoodsWantAndNeedCountVO implements Serializable{

    private Long shopId;

    private Integer needCount;

    private GoodsWant goodsWant;



}
