package com.secondhand.xiaoyi.service;

import java.util.HashMap;

/**
 * @InterfaceName RedEnvelopeService
 * @Description 红包抽奖，即抢红包
 * @Author Gaosl
 * @Date 2020/12/26 14:30
 * @Version 1.0
 */
public interface RedEnvelopeService {

    /**
     * @author Gaosl
     * @description //经过一个时间周期生成一个红包金额
     * @date 16:12 2020/12/27
     * @param
     * @return void
     **/
    void createRedEnvelope();




     /**
      * @author Gaosl
      * @description //执行Lua脚本文件，事务性操作抢红包
      * @date 1:15 2020/12/27
      * @param userId
      * @return java.lang.String
      **/
     HashMap<String, Object> getRedEnvelope(Long userId);

}
