package com.secondhand.xiaoyi.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.secondhand.xiaoyi.entity.Sort;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
public interface SortService extends IService<Sort> {
    /**
     * @author Gaosl
     * @description //得到所有的类别名称及ID
     * @date 15:47 2020/12/14
     * @param
     * @return java.util.List<com.secondhand.xiaoyi.entity.Sort>
     **/
    List<Sort> getAllSort();
}
