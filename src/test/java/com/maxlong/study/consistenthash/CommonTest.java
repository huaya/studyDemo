package com.maxlong.study.consistenthash;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.maxlong.study.collections.TreeMap;
import com.maxlong.study.serializable.UserInfo;
import com.maxlong.study.service.UserService;
import com.maxlong.study.service.impl.UserServiceImpl;
import com.maxlong.study.utils.DateFormat;
import com.maxlong.study.utils.DateUtil;
import com.maxlong.study.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URLEncoder;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: ma.xl E-mail: ma.xl@gjmetal.com
 * @version 创建时间：2019-3-13 11:53
 * 类说明:
 */
public class CommonTest {

    private static final String[] aaa = new String[]{"xxx", "yyy", "zzz"};

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
        String fromArea = iPAddrColumn[1].replace("'", "");
        System.out.println(fromArea);
    }

    @Test
    public void permSize() {
        String aaa = "xxxxx";
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String bbb = aaa + aaa;
            aaa = bbb;
        }
    }

    @Test
    public void interfaceTest() {
        System.out.println(UserService.class.isAssignableFrom(UserServiceImpl.class));
    }

    @Test
    public void requireNonNull() {
        UserInfo userInfo = new UserInfo();
        Objects.requireNonNull(userInfo, () -> userInfo.getUserId());
    }

    @Test
    public void string() {
        String aa = "123456";
        String bb = "123456";
        String cc = new String("123456");
        System.out.println(aa == bb);
        System.out.println(aa == cc);
    }

    @Test
    public void capacity() {
        System.out.println(1 << 30);
    }

    @Test
    public void hash() {
        String key = "11111";
        int h = key.hashCode();
        int i = (key == null) ? 0 : h ^ (h >>> 16);
        System.out.println(i);
    }

    @Test
    public void hash2() {
        String key = "74747465646656446546";
        int hash = key.hashCode();
        int i = (16 - 1) & hash;
        System.out.println(hash);
        System.out.println(i);
    }

    @Test
    public void hash3() {
        int key = 1200000;
        int i = 7 & key;
        System.out.println(i);
    }

    @Test
    public void hash4() {
        String v = "74747465646656446546";
        int h = v.hashCode();
        int key = (h ^ (h >>> 16)) & 0x7fffffff;
        System.out.println(key);
    }


    @Test
    public void isNaN() {
        System.out.println(Float.isNaN(0.0f / 0.0f));
    }


    @Test
    public void containsKey() {
        Map<String, Object> map = new HashMap<>();
        map.put("maxlong", 1111);
        Object maxlong = map.compute("maxlong", (key, value) -> 1 + 1);
        System.out.println(maxlong.toString());
        System.out.println(map.get("maxlong"));
    }

    @Test
    public void offset1() {
        UserInfo userInfo = new UserInfo("1000", "maxlong");
        String userId = (String) UserInfo.U.getObjectVolatile(userInfo, UserInfo.USERID);
        String userName = (String) UserInfo.U.getObjectVolatile(userInfo, UserInfo.USERNAME);
        System.out.println("userId: " + userId + ", userName: " + userName);
    }


    @Test
    public void offset2() {
        final long count = 10000000L;
        UserInfo userInfo = new UserInfo();
        Instant start = Instant.now();
        for (long i = 0; i < count; i++) {
            userInfo.setUserId("10000");
        }
        System.out.println("set method spend :" + Duration.between(start, Instant.now()).toMillis() + "ms");

        start = Instant.now();
        for (long i = 0; i < count; i++) {
            UserInfo.U.putObject(userInfo, UserInfo.USERID, "10000");
        }
        System.out.println("Unsafe put spend :" + Duration.between(start, Instant.now()).toMillis() + "ms");
    }

    @Test
    public void offset2StopWatch() {
        final long count = 10000000L;
        UserInfo userInfo = new UserInfo();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("set method");
        for (long i = 0; i < count; i++) {
            userInfo.setUserId("10000");
        }
        stopWatch.stop();

        stopWatch.start("Unsafe put");
        for (long i = 0; i < count; i++) {
            UserInfo.U.putObject(userInfo, UserInfo.USERID, "10000");
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }


    @Test
    public void classLoad() {
        System.out.println(UserInfo.class.getClassLoader());
    }

    @Test
    public void getCallerClass() {
        UserInfo.testCallerSensitive();
    }

    @Test
    public void tableSizeFor() {
        int c = 65;
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n);
    }

    @Test
    public void mapNullTest() {
        Map<String, Object> map = new HashMap<>();
        map.put(null, null);

        Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        try {
            concurrentHashMap.put("1", null);
        } catch (NullPointerException e) {
            System.out.println("concurrentHashMap value not null");
        }
        try {
            concurrentHashMap.put(null, "1");
        } catch (NullPointerException e) {
            System.out.println("concurrentHashMap key not null");
        }
        try {
            concurrentHashMap.put(null, null);
        } catch (NullPointerException e) {
            System.out.println("concurrentHashMap key and value not null");
        }

    }

    @Test
    public void test() {
        String aaa = "";
        String bbb = "";
        aaa = bbb = "xxxx";
        System.out.println(aaa);
        System.out.println(bbb);
    }

    @Test
    public void sizeCtl() throws NoSuchFieldException, IllegalAccessException {
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


    @Test
    public void treeMap() {
        TreeMap<String, String> treeMap = new TreeMap<String, String>() {
            {
                put("1", "1");
                put("2", "2");
                put("3", "3");
                put("4", "4");
                put("5", "5");
                put("6", "6");
//                put("7","7");
//                put("8","8");
//                put("9","9");
//                put("10","10");
//                put("11","11");
//                put("12","12");
            }

        };
        TreeMap.Entry<String, String> entry = treeMap.root;
        Queue<TreeMap.Entry> queue = new LinkedList<>();
        queue.offer(entry);

        TreeMap.Entry<String, String> cur;
        TreeMap.Entry<String, String> last = entry;
        TreeMap.Entry<String, String> nlast = entry;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            TreeMap.Entry<String, String> parent = cur.parent;
            System.out.print((parent != null ? parent.getValue() : 0) + "-" + cur.getValue() + "     ");

            TreeMap.Entry<String, String> left = cur.left;
            if (left != null) {
                queue.offer(left);
                nlast = left;
            }
            TreeMap.Entry<String, String> right = cur.right;
            if (right != null) {
                queue.offer(right);
                nlast = right;
            }

            if (cur == last) {
                System.out.println();
                last = nlast;
            }

        }
    }

    @Test
    public void treeMap2() {
        TreeMap<String, String> treeMap = new TreeMap<String, String>() {
            {
                put("1", "1");
                put("2", "2");
                put("3", "3");
                put("4", "4");
                put("5", "5");
                put("6", "6");
                put("7", "7");
                put("8", "8");
                put("9", "9");
                put("10", "10");
                put("11", "11");
                put("12", "12");
            }

        };
        System.out.println(treeMap.ceilingKey("10"));
    }

    @Test
    public void commpare() {
        String c1 = "11";
        String c2 = "4";
        System.out.println(c1.compareTo(c2));
    }

    @Test
    public void readLines() throws IOException {
        List<String> list = FileUtil.readLineFromFile("C:\\Users\\guojin\\Desktop\\nohup.txt", 0, "UTF-8");
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void indexOf() throws IOException {
        File file = new File("C:/Us ers/guojin/Desktop/nohup.txt");
        System.out.println(file.getCanonicalPath());
    }

    @Test
    public void stream() {
        String mealsets = StringUtils.joinWith(",", "1008,1008", null);
        System.out.println(mealsets);
        mealsets = Arrays.stream(mealsets.split(",")).distinct().collect(Collectors.joining(","));
        System.out.println(mealsets);
    }

    @Test
    public void stream2() {
        String mealsets = StringUtils.joinWith(",", "1008,1008", null);
        System.out.println(mealsets);
        mealsets = Stream.of(mealsets)
                .flatMap(aaa -> Arrays.stream(aaa.split(",")))
                .distinct()
                .collect(Collectors.joining(","));
        System.out.println(mealsets);
    }

    @Test
    public void regex() {
        String regex = "(.*)(?i)zookeeper(.*)\\.java$|(.*)(?i)zk(.*)\\.java$";
        String mealset1 = "Zookeeper.java";
        String mealset2 = "Zookeeper111.java";
        String mealset3 = "sdszookeeper111.java";
        String mealset4 = "sdszk111.java";

        System.out.println(mealset1.matches(regex));
        System.out.println(mealset2.matches(regex));
        System.out.println(mealset3.matches(regex));
        System.out.println(mealset4.matches(regex));
    }

    @Test
    public void booleanTest() {
        System.out.println(new Date(Integer.MIN_VALUE));
    }

    @Test
    public void copyOnWriteArrayList() {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList(
                Lists.newArrayList(new Integer(1), new Integer(2), new Integer(3)));
    }

    @Test
    public void noCache() {

        final int LINE_NUM = 1024;
        final int COLUM_NUM = 1024;
        long[][] array = new long[LINE_NUM][COLUM_NUM];

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < COLUM_NUM; ++i) {
            for (int j = 0; j < LINE_NUM; ++j) {
                array[j][i] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("no cache time:" + (endTime - startTime));
    }

    @Test
    public void cache() {
        final int LINE_NUM = 1024;
        final int COLUM_NUM = 1024;
        long[][] array = new long[LINE_NUM][COLUM_NUM];

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; ++i) {
            for (int j = 0; j < COLUM_NUM; ++j) {
                array[i][j] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        long cacheTime = endTime - startTime;
        System.out.println("cache time:" + cacheTime);
    }

    @Test
    public void rxjava() {
        int cnt = 6;
        String begin = "2019-04-17";
        for (int i = 0; i < cnt; i++) {
            begin = DateUtil.addDay(begin, 28, DateFormat.STYLE2);
            System.out.println(begin);
        }
    }

    @Test
    public void hashed() {
        String name = "陈航科";
        long nameH = name.hashCode();
        System.out.println(nameH);
        nameH += 2147483647L + 1L;
        System.out.println(nameH);
        System.out.println(nameH % 116);
    }

    @Test
    public void computeIfAbsent() {
        List<UserInfo> userInfos = Lists.newArrayList(
                new UserInfo("1", "xxx"),
                new UserInfo("2", "xxx"),
                new UserInfo("3", "yyy"));

        Map<String, List<UserInfo>> userInfoMap = new HashMap<>();
        userInfos.forEach(userInfo -> userInfoMap.computeIfAbsent(userInfo.getUserName(), k -> new ArrayList<>()).add(userInfo));
        System.out.println(userInfoMap);
    }

    @Test
    public void integerSort() {
        List<Integer> list = Lists.newArrayList(10, 2, 5, 1000, 555, 148);
        System.out.println(list);
        list.sort((a, b) -> (a - b));
        System.out.println(list);
    }


    @Test
    public void week() {
        LocalDate localDate = LocalDate.now();
        LocalDate lastWeek = localDate.plusWeeks(-1);
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 1);
        int x = lastWeek.get(weekFields.weekOfWeekBasedYear());
        System.out.println(x);

    }

    @Test
    public void diamonds() {
        BigDecimal diamonds = new BigDecimal("123.1122");
        BigDecimal diamonds2 = new BigDecimal("123.5422");
        BigDecimal diamonds3 = new BigDecimal("123.8422");

        System.out.println(diamonds.intValue());
        System.out.println(diamonds2.intValue());
        System.out.println(diamonds3.intValue());
    }

    @Test
    public void multiply() {
        BigDecimal diamonds = new BigDecimal("123.1122");
        BigDecimal sss = diamonds.multiply(new BigDecimal(0.09)).setScale(4, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(sss.toString());
    }

    @Test
    public void content() {
        String content = "您成功消费%s钻石兑换%s一台";
        System.out.println(String.format(content, "sdfsf", "qwqwqwqwew"));
    }

    @Test
    public void encode() throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("{}", "UTF-8");
        System.out.println(encode);
    }


    @Test
    public void sub() {
        List<String> list = Lists.newArrayList("aaaa", "bbbb", "ccccc");
        list = list.subList(0, 2);
        System.out.println(list);
    }

    @Test
    public void remove() {
        List<String> list = Lists.newArrayList("aaaa", "bbbb", "ccccc");
        Queue queue = Queues.newArrayDeque(list);
        for (int i = 0; i < 8; i++) {
            System.out.println(queue.poll());
        }
    }

    @Test
    public void nextInt() {
        for (int i = 0; i < 20; i++) {
            System.out.println(ThreadLocalRandom.current().nextInt(5));
        }
    }

    @Test
    public void decoder() {
        String ssds = Base64.getEncoder().encodeToString("12345678".getBytes());
        System.out.println(ssds);
        byte[] xxxx = Base64.getDecoder().decode(ssds);
        String yyyy = new String(xxxx);
        System.out.println(yyyy);
    }

    @Test
    public void filter() {
        List<String> sdsdd = Lists.newArrayList("fssd", "aafeefe", "dddddddd", "tttttttt");
        List<String> bbbbb = sdsdd.stream().filter(s -> s.contains("f")).collect(Collectors.toList());
        System.out.println(bbbbb);
    }

    @Test
    public void replace() {
        String aaa = "fsfsferSPA";
        String bbb = "fsfsferspa";
        System.out.println(aaa.replace("SPA", ""));
        System.out.println(bbb.replace("SPA", ""));
    }

    @Test
    public void readJson1() {
        String json = FileUtil.readFileToStr("src/main/resources/json/temp.json", "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray data = jsonObject.getJSONArray("data");
        for (int i = 0; i < data.size(); i++) {
            JSONObject o = data.getJSONObject(i);
            System.out.println(o.getString("siteId"));
        }
    }

}


