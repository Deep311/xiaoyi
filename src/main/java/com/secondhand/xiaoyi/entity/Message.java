package com.secondhand.xiaoyi.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.secondhand.xiaoyi.entity.VO.MessageVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Gaosl
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Message对象", description="")
public class Message implements Serializable,Comparable<Message> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "message_id", type = IdType.AUTO)
    private Long messageId;

    @ApiModelProperty(value = "商品/求购商品id")
    private Long goodsWantId;

    @ApiModelProperty(value = "留言内容")
    private String content;

    @ApiModelProperty(value = "留言楼层")
    private Integer messageFloor;

    @ApiModelProperty(value = "评论的用户id")
    private Long userId;


    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;



    @Override
    public int compareTo(Message o) {
        Date date1=this.getGmtCreate();
        Date date2=o.getGmtCreate();
        if (date1.after(date2)) {
            return -1;
        }else if (date1.before(date2)){
            return 1;
        }
        return 0;
    }
}
