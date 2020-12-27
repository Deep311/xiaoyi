package com.secondhand.xiaoyi;
import com.alibaba.fastjson.JSONObject;
import com.secondhand.xiaoyi.entity.User;
import com.secondhand.xiaoyi.mapper.UserMapper;
import com.secondhand.xiaoyi.service.RedEnvelopeService;
import com.secondhand.xiaoyi.service.UserService;
import com.secondhand.xiaoyi.service.impl.RedEnvelopeServiceImpl;
import com.secondhand.xiaoyi.utils.RedisUtil;
import com.secondhand.xiaoyi.utils.resultabout.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class XiaoyiApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;
    @Test
    void contextLoads() {
    }


    @Test
    public void getUserInfo() {
        User user = new User();
        user.setUsername("小明");
        user.setPassword("xiaomingpwd");
        User userInfo = userMapper.getUserInfo(user);
        User userInfo1 = userService.getUserInfo(user);
    }



    @Test
    public void saveUserInfo() {
        User user = new User();
        user.setUsername("小乐");
        user.setPassword("xiaolepwd");
        user.setPhonenum("4567890125");
        String str=userService.saveUserInfo(user);
        if (str.charAt(0)=='f'){
            log.info(Result.failure().message(str.substring(1)).toString());
        }else {
            log.info(Result.success().message(str.substring(1)).toString());
        }
    }

    @Test
    public void getUserInfoById(){
        Long user_id=1L;
        User userInfo = userService.getUserInfoById(user_id);
        if (userInfo==null){
            System.out.println(Result.failure().message("查询失败").toString());
        }else{
            System.out.println(Result.success().data("items", userInfo).toString());
        }
    }

    @Test
    public void getImgType(){
        String  base64ImgStr="data:image/png;base64,ewtfdq2r4bcxfwhbvc/ewqrr data:image/jpg;base64,r";
        int l=0;
        int r=0;
        for (int i = 0; i < base64ImgStr.toCharArray().length; i++) {
            if (base64ImgStr.charAt(i)=='/') {
                l=i+1;
            }
            if (base64ImgStr.charAt(i)==';') {
                r=i;
            }
            if (l>0&&r>0) {
                break;
            }
        }
        System.out.println(base64ImgStr.substring(l, r));
    }






    @Test
    public void testcopyFile() throws Exception {
//        BufferedInputStream in = FileUtil.getInputStream("F:\\ideaproject\\xiaoyi\\src\\main\\resources\\static\\userImg\\1.txt");
//        BufferedOutputStream out = FileUtil.getOutputStream("F:\\ideaproject\\xiaoyi\\src\\main\\resources\\static\\userImg\\2.txt");
//        byte[] bytes = IoUtil.readBytes(in,true);
//        IoUtil.write(out,true,bytes);
    }



}
