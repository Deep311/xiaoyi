package com.secondhand.xiaoyi.entity.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.entity.Message;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName MessageVO
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/12/25 15:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MessageVO implements Serializable ,Comparable<MessageVO>{

    private static final long serialVersionUID = 1L;

    private String username;

    private Message message;

    @Override
    public int compareTo(MessageVO o) {
        //升序
        int f1= this.message.getMessageFloor();
        int f2= o.message.getMessageFloor();
        return f1-f2;
    }
}
