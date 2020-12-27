package com.secondhand.xiaoyi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.secondhand.xiaoyi.service.RedEnvelopeService;
import com.secondhand.xiaoyi.utils.RedisUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

    @Resource
    RedisUtil redisUtil;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;
    /**
     * @description //小红包数量
     **/
    public static int SMALL_RED_ENVELOP_NUM = 20;

    /**
     * @description //未消费红包队列
     **/
    public static String UNCONSUMED_RED_ENVELOP_QUEUE="unconsumedRedEnvelopeQueue";

    /**
     * @description //已消费红包队列
     **/
    public static String CONSUMPTION_RED_ENVELOP_QUEUE="consumptionRedEnvelopeQueue";

    /**
     * @description //用户ID过滤map
     **/
    public static String USERID_FILTER_MAP="userIdFilterMap";


    /**
     * @author Gaosl
     * @description   //	-- 函数：尝试获得红包，如果成功，则返回json字符串，如果不成功，则返回空
     *                //	-- 参数：未消费红包队列名， 已消费的队列名，去重的Map名，用户ID
     *                //	-- 返回值：nil 或者 json字符串，包含用户ID：userId，红包ID：id，红包金额：money
     * @date 0:47 2020/12/27
     * @param
     * @return
     **/
    public static String GET_RED_ENVELOP_SCRIPT =
            "if redis.call('hexists', KEYS[3], ARGV[1]) ~= 0 then\n"
                    + "return nil\n"
                    + "else\n"
                    + "local redEnvelope = redis.call('rpop', KEYS[1]);\n"
                    + "if redEnvelope then\n"
                    + "local x = cjson.decode(redEnvelope);\n"
                    + "x['userId'] = ARGV[1];\n"
                    + "local re = cjson.encode(x);\n"
                    + "redis.call('hset', KEYS[3], ARGV[1], ARGV[1]);\n"
                    + "redis.call('lpush', KEYS[2], re);\n"
                    + "return re;\n"
                    + "end\n"
                    + "end\n"
                    + "return nil";


    /**
     * @author Gaosl
     * @description //经过一个时间周期生成一个红包金额
     * @date 0:45 2020/12/27
     * @param
     * @return java.lang.Integer
     **/
    @Override
    @Scheduled(cron = "0 * * * * ?")
    public Integer createRedEnvelope() {
        // 生成50-150内的随机数
        Integer amount = new Random().nextInt(150 - 50 + 1) + 50;
        System.out.println(amount);
        System.out.println("hello..........."+new Date());
        return amount;
    }

    /**
     * @return void
     * @author Gaosl
     * @description //线段分割法生成小红包，红包金额
     * @date 15:36 2020/12/26
     **/
    @Override
    public void divideRedEnvelope() {
        /*new RedEnvelopeServiceImpl().createRedEnvelope()*/
        Integer totalAmount=123;
        List<Double> list=new ArrayList<Double>();
        while (list.size()<SMALL_RED_ENVELOP_NUM-1) {
            double v = Math.random() * ((double)totalAmount - 0.01) + 0.01;
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
            JSONObject object = new JSONObject();
            object.put("userId",0);
            object.put("money",temp);
            redisUtil.lLeftPush(UNCONSUMED_RED_ENVELOP_QUEUE,object.toJSONString());
            left=aDouble;
            leftAmount+=temp;
        }
        Double temp=(double) totalAmount-leftAmount;
        temp = (double)Math.round(temp*100)/100;
        JSONObject object = new JSONObject();
        object.put("userId",0);
        object.put("money",temp);
        redisUtil.lLeftPush(UNCONSUMED_RED_ENVELOP_QUEUE,object.toJSONString());
    }

    /**
     * @author Gaosl
     * @description //执行Lua脚本文件，事务性操作抢红包
     * @date 1:15 2020/12/27
     * @param userId
     * @return java.lang.String
     **/
    @Override
    public String getRedEnvelope(Long userId) {
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(String.class);
        redisScript.setScriptText(GET_RED_ENVELOP_SCRIPT);
        List<String> keyList = new ArrayList<>();
        keyList.add(UNCONSUMED_RED_ENVELOP_QUEUE);
        keyList.add(CONSUMPTION_RED_ENVELOP_QUEUE);
        keyList.add(USERID_FILTER_MAP);
        String arg1=userId.toString();
        return stringRedisTemplate.execute(redisScript,keyList,arg1);
    }
}


