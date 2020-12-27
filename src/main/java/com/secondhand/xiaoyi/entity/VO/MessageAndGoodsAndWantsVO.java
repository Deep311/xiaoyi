package com.secondhand.xiaoyi.entity.VO;

import com.secondhand.xiaoyi.entity.GoodsWant;
import com.secondhand.xiaoyi.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName MessageAndGoodsAndWantsVO
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/12/27 16:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class MessageAndGoodsAndWantsVO implements Serializable,Comparable<MessageAndGoodsAndWantsVO> {

    private static final long serialVersionUID = 1L;

    public Message message;

    public GoodsWant goodsWant;


    @Override
    public int compareTo(MessageAndGoodsAndWantsVO o) {
        Date date1=this.getMessage().getGmtCreate();
        Date date2=o.getMessage().getGmtCreate();
        if (date1.after(date2)) {
            return -1;
        }else if (date1.before(date2)){
            return 1;
        }
        return 0;
    }
}
