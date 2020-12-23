package com.secondhand.xiaoyi.utils.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName GuliException
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/11/30 21:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException {
    private Integer code;

    private String msg;


}
