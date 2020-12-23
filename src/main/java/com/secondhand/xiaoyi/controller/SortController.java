package com.secondhand.xiaoyi.controller;


import com.secondhand.xiaoyi.entity.Sort;
import com.secondhand.xiaoyi.service.SortService;
import com.secondhand.xiaoyi.utils.resultabout.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-05
 */
@RestController
@RequestMapping("/xiaoyi/sort")
public class SortController {

    @Resource
    private SortService sortService;
    @ApiOperation(value = "得到所有的类别名称及ID，返回所有sort记录")
    @GetMapping("showAllSort")
    public Result getAllSort(){
        List<Sort> allSort = sortService.getAllSort();
        return Result.success().data("items",allSort);
    }

}

