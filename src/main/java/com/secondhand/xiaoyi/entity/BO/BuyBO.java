package com.secondhand.xiaoyi.entity.BO;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BuyBO
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/12/27 15:30
 * @Version 1.0
 */
@Data
public class BuyBO implements Serializable {

    private static final long serialVersionUID = 1L;

    Long userId;
    Long goodsWantId;
    Integer needCount;
    String paymentPassword;
}
