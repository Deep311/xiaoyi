package com.secondhand.xiaoyi.utils.exceptionhandler;

import com.secondhand.xiaoyi.utils.resultabout.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobaExceptionHandler
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/12/4 23:23
 * @Version 1.0
 */
@ControllerAdvice
@Slf4j
public class GlobaExceptionHandler {
    /**
     * @Author gaosl
     * @Description //统一异常
     * @Date 23:24 2020/12/4
     * @Param [e]
     * @return com.xiaoyi.secondhand.utils.Result
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.failure().message("执行了"+e.getClass().toString().substring(16)+"异常处理...");
    }

//    //特定异常
//    @ExceptionHandler(ArithmeticException.class)
//    @ResponseBody//为了可以返回数据
//    public Result error(ArithmeticException e){
//        e.printStackTrace();
//        return Result.failure().message("执行了ArithmeticException特定异常处理...");
//    }
    //自定义异常
    @ExceptionHandler(BaseException.class)
    @ResponseBody//为了可以返回数据
    public Result error(BaseException e){
        e.printStackTrace();
        return Result.failure().code(e.getCode()).message(e.getMsg());
    }
}
