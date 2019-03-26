package com.maxlong.study.consistenthash;

import com.maxlong.study.serializable.UserInfo;
import com.maxlong.study.service.UserService;
import com.maxlong.study.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: ma.xl E-mail: ma.xl@gjmetal.com
 * @version 创建时间：2019-3-13 11:53
 * 类说明:
 */
public class CommonTest {

    @Test
    public void jdkHash() {
        String a = "a";
        String ab = "ab";
        String abc = "abc";
        System.out.println(a.hashCode());
        System.out.println('b' + 0);
        System.out.println(ab.hashCode());
        System.out.println(abc.hashCode());

    }

    @Test
    public void substring() {
        String content = "ShowIPAddr('222.65.31.91','上海市浦东新区 电信','未知操作系统 未知浏览器 ');";
        int leftBracketIdx = content.indexOf("(");
        int rightBracketIdx = content.indexOf(")");
        String iPAddr = content.substring(leftBracketIdx + 1, rightBracketIdx);
        String[] iPAddrColumn = iPAddr.split(",");
        String fromArea = iPAddrColumn[1].replace("'","");
        System.out.println(fromArea);
    }

    @Test
    public void permSize () {
        String aaa = "xxxxx";
        for(int i = 0; i < Integer.MAX_VALUE; i++){
            String bbb = aaa + aaa;
            aaa = bbb;
        }
    }

    @Test
    public void interfaceTest () {
        System.out.println(UserService.class.isAssignableFrom(UserServiceImpl.class));
    }

    @Test
    public void requireNonNull () {
        UserInfo userInfo = new UserInfo();
        Objects.requireNonNull(userInfo, () -> userInfo.getUserId());
    }

    @Test
    public void string () {
        String aa = "123456";
        String bb = "123456";
        String cc = new String("123456");
        System.out.println(aa == bb);
        System.out.println(aa == cc);
    }

    @Test
    public void capacity () {
        System.out.println(1 << 30);
    }

    @Test
    public void hash () {
        String key = "11111";
        int h = key.hashCode();
        int i = (key == null) ? 0 : h ^ (h >>> 16);
        System.out.println(i);
    }

    @Test
    public void hash2 () {
        String key = "74747465646656446546";
        int hash = key.hashCode();
        int i = (16 - 1) & hash;
        System.out.println(hash);
        System.out.println(i);
    }

    @Test
    public void hash3 () {
        int key = 1200000;
        int i = 7 & key;
        System.out.println(i);
    }

    @Test
    public void hash4 () {
        String v = "74747465646656446546";
        int h = v.hashCode();
        int key = (h ^ (h >>> 16)) & 0x7fffffff;
        System.out.println(key);
    }


    @Test
    public void isNaN () {
        System.out.println(Float.isNaN(0.0f / 0.0f));
    }


    @Test
    public void containsKey () {
        Map<String, Object> map = new HashMap<>();
        map.put("maxlong", 1111);
        Object maxlong = map.compute("maxlong", (key, value) -> 1 + 1);
        System.out.println(maxlong.toString());
        System.out.println(map.get("maxlong"));
     }

    @Test
    public void offset1 () {
        UserInfo userInfo = new UserInfo("1000", "maxlong");
        String userId = (String) UserInfo.U.getObjectVolatile(userInfo, UserInfo.USERID);
        String userName = (String) UserInfo.U.getObjectVolatile(userInfo, UserInfo.USERNAME);
        System.out.println("userId: " + userId + ", userName: " + userName);
    }


    @Test
    public void offset2 () {
        final long count = 10000000L;
        UserInfo userInfo = new UserInfo();
        Instant start = Instant.now();
        for(long i = 0; i < count; i++){
            userInfo.setUserId("10000");
        }
        System.out.println("set method spend :" + Duration.between(start, Instant.now()).toMillis() + "ms");

        start = Instant.now();
        for(long i = 0; i < count; i++){
            UserInfo.U.putObject(userInfo, UserInfo.USERID, "10000");
        }
        System.out.println("Unsafe put spend :" + Duration.between(start, Instant.now()).toMillis() + "ms");
    }

    @Test
    public void offset2StopWatch () {
        final long count = 10000000L;
        UserInfo userInfo = new UserInfo();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("set method");
        for(long i = 0; i < count; i++){
            userInfo.setUserId("10000");
        }
        stopWatch.stop();

        stopWatch.start("Unsafe put");
        for(long i = 0; i < count; i++){
            UserInfo.U.putObject(userInfo, UserInfo.USERID, "10000");
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }


    @Test
    public void classLoad () {
        System.out.println(UserInfo.class.getClassLoader());
    }

    @Test
    public void getCallerClass () {
        UserInfo.testCallerSensitive();
    }

    @Test
    public void tableSizeFor () {
        int c = 65;
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println( n);
    }

    @Test
    public void mapNullTest () {
        Map<String, Object> map = new HashMap<>();
        map.put(null, null);

        Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        try {
            concurrentHashMap.put("1", null);
        }catch (NullPointerException e){
            System.out.println("concurrentHashMap value not null");
        }
        try {
            concurrentHashMap.put(null, "1");
        }catch (NullPointerException e){
            System.out.println("concurrentHashMap key not null");
        }
        try {
            concurrentHashMap.put(null, null);
        }catch (NullPointerException e){
            System.out.println("concurrentHashMap key and value not null");
        }

    }

    @Test
    public void test () {
        String aaa = "";
        String bbb = "";
        aaa = bbb = "xxxx";
        System.out.println( aaa);
        System.out.println( bbb);
    }

    @Test
    public void sizeCtl () throws NoSuchFieldException, IllegalAccessException {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1000", "100");
        Field sizeCtlF = ConcurrentHashMap.class.getDeclaredField("sizeCtl");
        sizeCtlF.setAccessible(true);
        int sizeCtl = (int) sizeCtlF.get(concurrentHashMap);
        System.out.println(sizeCtl);
        System.out.println(concurrentHashMap.get("1000"));
    }

    @Test
    public void compareAndSwapInt() {
        UserInfo userInfo = new UserInfo();
        UserInfo.U.compareAndSwapInt(userInfo, UserInfo.AGE, 0, -1);
        System.out.println(userInfo.getAge());
    }

}


