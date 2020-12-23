package com.secondhand.xiaoyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhand.xiaoyi.entity.Action;
import com.secondhand.xiaoyi.mapper.ActionMapper;
import com.secondhand.xiaoyi.service.ActionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
public class ActionServiceImpl extends ServiceImpl<ActionMapper, Action> implements ActionService {

    @Resource
    private ActionMapper actionMapper;
    /**
     * @param userId
     * @param goodsWantId
     * @param actionSort
     * @return java.lang.Integer
     * @author Gaosl
     * @description //向action表插入一条行为信息
     * @date 23:41 2020/12/9
     **/
    @Override
    public Integer saveAction(Long userId, Long goodsWantId, String actionSort) {
        Action action = new Action();
        action.setUserId(userId);
        action.setGoodsWantId(goodsWantId);
        action.setActionSort(actionSort);
        return actionMapper.insert(action);
    }

    /**
     * @param userId
     * @param actionSort
     * @return com.secondhand.xiaoyi.entity.Action
     * @author Gaosl
     * @description //根据userId, actionSort，查询action记录
     * @date 20:20 2020/12/11
     **/
    @Override
    public List<Action> getByUserIdAndActionSort(Long userId, String actionSort) {
        QueryWrapper<Action> queryWrapper = new QueryWrapper<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id",userId);
        map.put("action_sort",actionSort);
        queryWrapper.allEq(map);
        return actionMapper.selectList(queryWrapper);
    }
}
