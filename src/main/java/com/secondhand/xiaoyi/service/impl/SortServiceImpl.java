package com.secondhand.xiaoyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.xiaoyi.entity.Sort;
import com.secondhand.xiaoyi.mapper.SortMapper;
import com.secondhand.xiaoyi.service.SortService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
@Service
public class SortServiceImpl extends ServiceImpl<SortMapper, Sort> implements SortService {

    @Resource
    private SortMapper sortMapper;
    /**
     * @return java.util.List<com.secondhand.xiaoyi.entity.Sort>
     * @author Gaosl
     * @description //得到所有的类别名称及ID
     * @date 15:47 2020/12/14
     **/
    @Override
    public List<Sort> getAllSort() {
        return  sortMapper.selectList(new QueryWrapper<Sort>().select("sort_id", "goods_want_sort"));
    }
}
