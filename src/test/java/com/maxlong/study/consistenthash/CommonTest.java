package com.maxlong.study.consistenthash;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.maxlong.study.collections.TreeMap;
import com.maxlong.study.serializable.UserInfo;
import com.maxlong.study.service.UserService;
import com.maxlong.study.service.impl.UserServiceImpl;
import com.maxlong.study.utils.DateFormat;
import com.maxlong.study.utils.DateUtil;
import com.maxlong.study.utils.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;
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
    public void string2() throws UnsupportedEncodingException {
        byte[] content = new byte[0];
        System.out.println(new String(content, "UTF-8"));
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
        int key = 7;
        int i = 2 & key;
        System.out.print(i);
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
        list.sort(Comparator.comparingInt(a -> a));
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
    public void json() {
        User user = new User();
        UserInfo userInfo = new UserInfo("mxl", "afefawer");
        List<UserInfo> users = new ArrayList<>();
        for(int i=0;i<200;i++){
            users.add(userInfo);
        }
        user.setUsers(users);
        System.out.println(JSONObject.toJSONString(user));
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

    @Test
    public void listlist() {
        String[] arrays = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};

        List<List<String>> siteIdList = new ArrayList<>();
        List<String> sites = new ArrayList<>();
        siteIdList.add(sites);
        for (String siteId : arrays) {
            if (sites.size() >= 5) {
                sites = new ArrayList<>();
                siteIdList.add(sites);
            }
            sites.add(siteId);
        }

    }

    @Test
    public void nullsLast() {
        List<UserInfo> userInfos = new ArrayList<>();
        userInfos.add(new UserInfo(null, "fsfsf"));
        userInfos.add(new UserInfo("1121", "fsfsf"));
        userInfos.add(new UserInfo(null, "fsfsf"));
        userInfos.add(new UserInfo("1121", "fsfsf"));
        userInfos.sort(Comparator.comparing(a -> a.getUserId(), Comparator.nullsLast(String::compareTo)));
        System.out.println(JSONObject.toJSONString(userInfos));
    }

    @Test
    public void join() {
        System.out.println(StringUtils.joinWith(",", "a", "b"));
        System.out.println(StringUtils.join("a", ","));
        System.out.println(StringUtils.joinWith(",", "a"));
    }

    @Test
    public void removeList() {
        List<String> sdsdd = Lists.newArrayList("fssd", "aafeefe", "dddddddd", "tttttttt");
        for (String s : sdsdd) {
            sdsdd.remove(s);
            System.out.println(sdsdd);
        }
    }

    @Test
    public void replaceTest() {
        String xxx = "2222-333-445";
        System.out.println(xxx.replaceAll("\\D", ""));
    }

    @Test
    public void uuid() {
        System.out.println(UUID.randomUUID().toString());
    }

    @Test
    public void writeFile() {
        try (
                RandomAccessFile randomTargetFile = new RandomAccessFile("C:\\Users\\OrderPlus\\Desktop\\xxxx.text", "rw");
                FileChannel fileChannel = randomTargetFile.getChannel()
        ) {
            ByteBuffer byteBuffer = ByteBuffer.wrap("fsdfefefaefwa".getBytes());
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void positiveNumber(){
        System.out.println(Integer.parseInt("null"));
    }

    @Test
    public void write() throws IOException {
        String filePath = "opstores-core-model/";
        FileUtils.write(new File(filePath + "test"), "fefefefgagae", "UTF-8");
    }

    @Test
    public void joinWith(){
        List<String> subAccs = new ArrayList<>();
        subAccs.add("dsdsds");
        subAccs.add("xdsfsfd");
        String result3 = subAccs.stream().collect(Collectors.joining("\n"));
        System.out.println(result3);
    }

    @Test
    public void joinWith2(){
        System.out.println(StringUtils.joinWith("-", "aaa", null));
    }

    @Test
    public void firstNonBlank(){
        String dd = StringUtils.firstNonBlank("", null,"1", "2");
        System.out.println(dd);
    }

    @Test
    public void getZore(){
        String personInCharge = "afsfwefwefwe";
        System.out.println(personInCharge.split(",")[0]);
    }

    @Test
    public void join2(){
        List<String> domains = Lists.newArrayList("a","b", "c");
        System.out.println(StringUtils.join(domains, ","));
    }


    @Test
    public void martch() throws UnsupportedEncodingException {
        String domain = "589715031475584 ";
        System.out.println(URLEncoder.encode(domain.trim(), "UTF-8"));
    }

    @Test
    public void intdsdsd(){
        Date date = new Date(-1);
        System.out.println(DateUtil.dateToStr(date, DateFormat.STYLE8));
    }

    @Test
    public void pattern(){
        byte a = -121;
        System.out.println(a & 0XFF);

    }

    @Test
    public void compare(){
        int n1 = 4200;
        int n2 = 1100;
        int a1 = 1230;
        int a2 = 410;

        float x1 = (float)n1/n2;
        float x2 = (float)a1/a2;
        System.out.println(x2);
        System.out.println(x1);
        System.out.println(x2 - x1);
        System.out.println(x2 - x1 > 0);
    }

    @Test
    public void regex(){
        String regex = "^((?i)(ap|sc|rs)|[0-9])[0-9].*";
        String applyId = "12121";
        System.out.println(applyId.matches(regex));
        System.out.println(applyId.substring(2));
    }

    @Test
    public void regex2() {
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
    public void febDays(){
        Date date = DateUtil.strToDate("2020-03-29", DateFormat.STYLE2);
        System.out.println(DateUtil.addMonth(date, -1, DateFormat.STYLE2));
    }
    private static final List<Integer> SITEIDS = org.assertj.core.util.Lists.newArrayList(
            41758, 1013, 1454, 43255, 1327, 859, 19879, 29957, 43256, 39052, 36178, 43255, 24610, 30235, 1630, 24895,
            27712, 20297, 24984, 38037, 32463, 34002, 31358, 39060, 29294, 24760, 43604, 27722, 20291, 30391);


    private static final String hostname = "https://admins.chimpone.com/api";

    @org.junit.jupiter.api.Test
    public void contextLoads() {
        List<String> currDatas = FileUtil.readLineFromFile("E:\\workspace-cloud\\finance-tools\\src\\test\\resources\\siteDomains.txt", "UTF-8");
        Map<Integer, List<JSONObject>> siteDataMap = new HashMap<>();
        for (String currData : currDatas) {
            String[] dataArray = StringUtils.splitPreserveAllTokens(currData, "|");
            Integer siteId = Integer.valueOf(dataArray[2]);

            List<JSONObject> siteDatas = siteDataMap.computeIfAbsent(siteId, k -> new ArrayList<>());

            JSONObject dt = new JSONObject();
            dt.put("id", dataArray[0]);
            dt.put("yn", dataArray[1]);
            dt.put("siteId", dataArray[2]);
            dt.put("domain", dataArray[3]);
            dt.put("logo", dataArray[4]);
            dt.put("favicon", dataArray[5]);
            String size = "0";
            if(StringUtils.isNotBlank(dataArray[4]) && dataArray[4].contains("?")){
                size = dataArray[4].substring(dataArray[4].indexOf("?") + 1);
            }
            dt.put("logoSize", size);
            siteDatas.add(dt);
        }

        File file = new File("C:\\Users\\OrderPlus\\Desktop\\data-prod.csv");

        // 创建CSV写对象
        CsvWriter csvWriter = new CsvWriter(file, "GBK", new CsvWriterSettings());

        csvWriter.writeHeaders("hostname", "siteId", "data");

        List<String> datas = FileUtil.readLineFromFile("E:\\workspace-cloud\\webflux\\src\\test\\resources\\data.txt", "UTF-8");

        int index = 0;
        for (Integer siteId : SITEIDS) {

            List<String> domains = datas.subList(index, index + 10);

            JSONObject data = new JSONObject();
            JSONArray childDomains = new JSONArray();
            data.put("childDomains", childDomains);

            List<JSONObject> siteDatas = siteDataMap.get(siteId);
            if(!CollectionUtils.isEmpty(siteDatas)){
                childDomains.addAll(siteDatas);
            }

            for (String domain : domains) {
                String[] dataArray = domain.split("\\|");
                JSONObject dt = new JSONObject();
                dt.put("domain", dataArray[0]);
                dt.put("logo", dataArray[1]);
                dt.put("favicon", "");
                dt.put("logoSize", "150");
                childDomains.add(dt);
            }
            String[] content = {hostname, siteId.toString(), data.toJSONString()};
            csvWriter.writeRow(content);
            index = index + 10;
        }
        csvWriter.close();
    }
}


