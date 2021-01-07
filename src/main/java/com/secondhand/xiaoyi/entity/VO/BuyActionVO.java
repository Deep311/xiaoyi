package com.secondhand.xiaoyi.entity.VO;

import com.secondhand.xiaoyi.entity.Action;
import com.secondhand.xiaoyi.entity.GoodsWant;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BuyActionVO
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/12/27 23:34
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class BuyActionVO implements Serializable,Comparable<BuyActionVO> {

    private static final long serialVersionUID = 1L;

    public Action action;

    public GoodsWant goodsWant;

    @Override
    public int compareTo(BuyActionVO o) {
        Date date1=this.getAction().getGmtCreate();
        Date date2=o.getAction().getGmtCreate();
        if (date1.after(date2)) {
            return -1;
        }else if (date1.before(date2)){
            return 1;
        }
        return 0;
    }
}
