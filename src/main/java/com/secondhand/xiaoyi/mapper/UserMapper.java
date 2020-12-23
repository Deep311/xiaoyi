package com.secondhand.xiaoyi.mapper;

import com.secondhand.xiaoyi.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * @author Gaosl
     * @description //得到用户信息
     * @date 17:57 2020/12/5
     * @param user
     * @return java.util.List<com.secondhand.xiaoyi.entity.User>
     **/
    User getUserInfo(@Param("user") User user);




}
