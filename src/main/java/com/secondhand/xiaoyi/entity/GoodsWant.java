package com.secondhand.xiaoyi.entity;

import java.math.BigDecimal;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 * @author Gaosl
 * @since 2020-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodsWant对象", description="")
public class GoodsWant implements Serializable ,Comparable<GoodsWant>{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "goods_want_id", type = IdType.AUTO)
    private Long goodsWantId;

    @ApiModelProperty(value = "分类：g商品 w求购")
    private String goodsWant;

    @ApiModelProperty(value = "商品名称/求购商品名称")
    private String goodsWantName;

    @ApiModelProperty(value = "商品成色/求购商品成色")
    private Integer goodsWantLevel;

    @ApiModelProperty(value = "商品信息/求购商品信息")
    private String goodsWantInfo;

    @ApiModelProperty(value = "商品价格/求购商品价格")
    private BigDecimal goodsWantPrice;

    @ApiModelProperty(value = "商品类别/求购商品类别id")
    private Long sortId;

    @ApiModelProperty(value = "商品数量/求购商品数量")
    private Integer goodsWantCount;

    @ApiModelProperty(value = "商品图片存放路径")
    private String goodsWantImage;

    @ApiModelProperty(value = "商品销量")
    private Integer goodsSales;

    @ApiModelProperty(value = "发表的用户id")
    private Long userId;

    @ApiModelProperty(value = "浏览量")
    private Integer browsedCount;

    @ApiModelProperty(value = "收藏量")
    private Integer collectedCount;

    @ApiModelProperty(value = "逻辑删除：1删除 0未删除")
    private Boolean  isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @Override
    public int compareTo(GoodsWant o) {
        int i1 = handler(o);
        int i2 = handler(this);
        return i2-i1;
    }
    public static int  handler(GoodsWant o){
        int betweenDay = (int) DateUtil.between(o.getGmtCreate(), new Date(), DateUnit.DAY);
        int i = o.getBrowsedCount() + o.getCollectedCount() * 2;
        float s=(i==0?0:(i - 0.5f * 0.5f * betweenDay * betweenDay)/i);
        return (int)(i*s);
    }
}
