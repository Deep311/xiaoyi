package com.secondhand.xiaoyi.service;

import com.secondhand.xiaoyi.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
public interface UserService extends IService<User> {


    User getUserInfo(User user);

    /**
     * @author Gaosl
     * @description //保存用户注册输入信息
     * @date 23:45 2020/12/6
     * @param user
     * @return java.lang.Boolean
     **/
    String saveUserInfo(User user);

    /**
     * @author Gaosl
     * @description //根据用户ID（userId）查询用户个人信息
     * @date 14:27 2020/12/7
     * @param userId
     * @return com.secondhand.xiaoyi.entity.User
     **/
    User getUserInfoById(Long userId);


    /**
     * @author Gaosl
     * @description //根据用户ID（userId）得到用户钱包余额（walletBalance）
     * @date 15:13 2020/12/11
     * @param userId
     * @return java.math.BigDecimal
     **/
    BigDecimal getWalletBalance(Long userId);


    /**
     * @author Gaosl
     * @description //根据参数更新买方、卖方钱包余额
     * @date 18:02 2020/12/11
     * @param buyerId, sellerId, totalAmount
     * @return void
     **/
    void updateBuyerAndSellerWalletBalance(Long buyerId,Long sellerId,BigDecimal totalAmount);


    /**
     * @author Gaosl
     * @description //更新用户钱包余额
     * @date 22:27 2020/12/27
     * @param userId, walletBalance
     * @return void
     **/
    boolean updateWalletBalanceByUserId(Long userId,BigDecimal walletBalance);





}
