package com.secondhand.xiaoyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.secondhand.xiaoyi.entity.User;
import com.secondhand.xiaoyi.mapper.GoodsWantMapper;
import com.secondhand.xiaoyi.mapper.UserMapper;
import com.secondhand.xiaoyi.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.xiaoyi.utils.exceptionhandler.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * @author Gaosl
     * @description //用户登录:根据手机号phonenum/用户名username+密码password,返回user对象
     * @date 18:38 2020/12/20
     * @param user
     * @return com.secondhand.xiaoyi.entity.User
     **/
    @Override
    public User getUserInfo(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("password",user.getPassword())
                .and(i -> i.eq("username", user.getUsername()).or().eq("phonenum", user.getPhonenum()));
        User oneUser = userMapper.selectOne(queryWrapper);
        return oneUser;
    }

    /**
     * @author Gaosl
     * @description //保存用户注册输入信息
     * @date 23:47 2020/12/6
     * @param user
     * @return java.lang.Integer
     **/
    @Override
    public String saveUserInfo(User user) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return "f用户名或密码为空";
        }

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("user_id")
                .eq("username", user.getUsername());
        if (userMapper.selectCount(wrapper) > 0) {
            return "f用户名已被占用";
        }

        if (!StringUtils.isEmpty(user.getPhonenum())) {
            QueryWrapper<User> wrapper1 = new QueryWrapper<>();
            wrapper1.select("user_id")
                    .eq("phonenum", user.getPhonenum());
            if (userMapper.selectCount(wrapper1) > 0) {
                return "f手机号已被占用";
            }
        }

        if (userMapper.insert(user) == 1) {
            return "t注册成功";
        }
        return "f注册失败";
    }

    /**
     * @param userId
     * @return com.secondhand.xiaoyi.entity.User
     * @author Gaosl
     * @description //根据用户ID查询用户个人信息
     * @date 14:27 2020/12/7
     **/
    @Override
    public User getUserInfoById(Long userId) {
        if(userId==null||userId<1L){
            return null;
        }
        User user = userMapper.selectById(userId);
        return user;
    }

    /**
     * @param userId
     * @return java.math.BigDecimal
     * @author Gaosl
     * @description //根据用户ID（userId）得到用户钱包余额（walletBalance）
     * @date 15:13 2020/12/11
     **/
    @Override
    public BigDecimal getWalletBalance(Long userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("wallet_balance")
                .eq("user_id",userId);
        return  userMapper.selectOne(queryWrapper).getWalletBalance();
    }

    /**
     * @param buyerId
     * @param sellerId
     * @param totalAmount
     * @return void
     * @author Gaosl
     * @description //根据参数更新买方、卖方钱包余额
     * @date 18:02 2020/12/11
     **/
    @Override
    public void updateBuyerAndSellerWalletBalance(Long buyerId, Long sellerId, BigDecimal totalAmount) {
        UpdateWrapper<User> buyerUpdateWrapper = new UpdateWrapper<>();
        buyerUpdateWrapper.setSql("wallet_balance=wallet_balance-"+totalAmount).eq("user_id",buyerId);
        userMapper.update(new User(), buyerUpdateWrapper);
        UpdateWrapper<User> sellerUpdateWrapper = new UpdateWrapper<>();
        sellerUpdateWrapper.setSql("wallet_balance=wallet_balance+"+totalAmount).eq("user_id",sellerId);
         userMapper.update(new User(), sellerUpdateWrapper) ;

    }


}
