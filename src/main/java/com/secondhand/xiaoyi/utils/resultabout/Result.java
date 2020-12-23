package com.secondhand.xiaoyi.utils.resultabout;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Result
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/12/3 20:31
 * @Version 1.0
 */
@Data
public class Result {

    @ApiModelProperty(value = "标志位")
    private Boolean flag;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();
    private Result(){}
    public static Result success(){
        Result result = new Result();
        result.setFlag(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }
    public static Result failure(){
        Result result = new Result();
        result.setFlag(false);
        result.setCode(ResultCode.FAILURE);
        result.setMessage("失败");
        return result;
    }
    public Result flag(Boolean flag){
        this.setFlag(flag);
        return this;
    }
    public Result message(String message){
        this.setMessage(message);
        return this;
    }
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }
    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}