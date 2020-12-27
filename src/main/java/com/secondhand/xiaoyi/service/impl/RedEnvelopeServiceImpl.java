package com.secondhand.xiaoyi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.secondhand.xiaoyi.service.RedEnvelopeService;
import com.secondhand.xiaoyi.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.*;


/**
 * @ClassName RedEnvelopeServiceImpl
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/12/26 14:31
 * @Version 1.0
 */
@Service
public class RedEnvelopeServiceImpl implements RedEnvelopeService {

    /**
     * @description //红包总金额
     **/
    public static Integer TOTAL_AMOUNT_OF_RED_ENVELOP=null;
    /**
     * @description //小红包数量
     **/
    public static int SMALL_RED_ENVELOP_NUM = 20;

    /**
     * @description //未消费红包队列
     **/
    public static Queue<HashMap<String,Object>> UNCONSUMED_RED_ENVELOP_QUEUE= new LinkedList<>();

    /**
     * @description //已消费红包队列
     **/
    public static Queue<HashMap<String,Object>> CONSUMPTION_RED_ENVELOP_QUEUE= new LinkedList<>();

    /**
     * @description //用户ID过滤map
     **/
    public static Set<Long> USERID_FILTER_SET=new HashSet<Long>();
    /**
     * @author Gaosl
     * @description //经过一个时间周期生成一个红包金额
     * @date 0:45 2020/12/27
     * @param
     * @return java.lang.Integer
     **/
    @Override
    @Scheduled(cron = "0 * * * * ?")
    public void createRedEnvelope() {
        // 生成50-150内的随机数
        TOTAL_AMOUNT_OF_RED_ENVELOP = new Random().nextInt(150 - 50 + 1) + 50;
        UNCONSUMED_RED_ENVELOP_QUEUE= new LinkedList<>();
        CONSUMPTION_RED_ENVELOP_QUEUE=new LinkedList<>();
        USERID_FILTER_SET=new HashSet<Long>();
        divideRedEnvelope();
    }

    /**
     * @return void
     * @author Gaosl
     * @description //线段分割法生成小红包，红包金额
     * @date 15:36 2020/12/26
     **/
    public static void divideRedEnvelope() {
        List<Double> list=new ArrayList();
        while (list.size()<SMALL_RED_ENVELOP_NUM-1) {
            double v = Math.random() * ((double)TOTAL_AMOUNT_OF_RED_ENVELOP - 0.01) + 0.01;
            if(list.indexOf(v)<0){
                list.add(v);
            }
        }
        Collections.sort(list);
        Double left=0.0;
        Double leftAmount=0.0;
        for (Double aDouble : list) {
            Double temp=aDouble-left;
            temp = (double)Math.round(temp*100)/100;
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("userId",0);
            map.put("money",temp);
            UNCONSUMED_RED_ENVELOP_QUEUE.add(map);
            left=aDouble;
            leftAmount+=temp;
        }
        Double temp1=(double) TOTAL_AMOUNT_OF_RED_ENVELOP-leftAmount;
        temp1 = (double)Math.round(temp1*100)/100;
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("userId",0);
        map.put("money",temp1);
        UNCONSUMED_RED_ENVELOP_QUEUE.add(map);
    }

    /**
     * @author Gaosl
     * @description //事务性操作抢红包
     * @date 1:15 2020/12/27
     * @param userId
     * @return java.lang.String
     **/
    @Override
    public HashMap<String, Object> getRedEnvelope(Long userId) {
        if (USERID_FILTER_SET.contains(userId)) {
            HashMap<String, Object> map = new HashMap<>(1);
            //表示已抢过红包
            map.put("money",null);
            return map;
        }
        HashMap<String, Object> redEnvelope = UNCONSUMED_RED_ENVELOP_QUEUE.poll();
        if (!redEnvelope.isEmpty()) {
            redEnvelope.put("userId",userId);
            USERID_FILTER_SET.add(userId);
            CONSUMPTION_RED_ENVELOP_QUEUE.add(redEnvelope);
        }
        return redEnvelope;
    }


}


