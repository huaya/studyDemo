package com.maxlong.lambda;

import com.maxlong.serializable.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2018-12-24 10:30
 */
public class OptionalDemo {

    private static final Logger logger = LoggerFactory.getLogger(OptionalDemo.class);

    public static void main(String[] args) {
//        orElseGet("orElseGet:notNull", new UserInfo("1002","huangjing"));
//        orElseGet("orElseGet:Null", null);

//        orElse("orElse:notNull", new UserInfo("1002","huangjing"));
//        orElse("orElse:Null", null);

//        ifPresent("ifPresent:notNull", new UserInfo("1002","huangjing"));
//        ifPresent("ifPresent:Null", null);

        orElseThrow("orElseThrow:notNull", new UserInfo("1002","huangjing"));
        try {
            orElseThrow("orElseThrow:Null", null);
        } catch (Exception e){
            logger.error("OptionalDemo orElseThrow errot:{}", e);
        }


//        map("orElseThrow:notNull", new UserInfo("1002","huangjing"));
////        map("orElseThrow:Null", null);

    }

    public static void orElseGet(String msg ,UserInfo userInfo){
        Optional<UserInfo> optional = Optional.ofNullable(userInfo);
        UserInfo user = optional.orElseGet(() -> new UserInfo("1000","maxlong"));
        System.out.println(msg + ":" + user.toString());
    }


    public static void orElse(String msg ,UserInfo userInfo){
        Optional<UserInfo> optional = Optional.ofNullable(userInfo);
        UserInfo user = optional.orElse(new UserInfo("1000","maxlong"));
        System.out.println(msg + ":" + user.toString());
    }

    public static void ifPresent(String msg ,UserInfo userInfo){
        Optional<UserInfo> optional = Optional.ofNullable(userInfo);
        optional.ifPresent(user -> System.out.println(msg + ":" + user.toString()));
    }

    public static void orElseThrow(String msg ,UserInfo userInfo){
        Optional<UserInfo> optional = Optional.ofNullable(userInfo);
        optional.orElseThrow(() -> new NullPointerException("11111"));
    }


    public static void map(String msg ,UserInfo userInfo){
        Optional<UserInfo> optional = Optional.ofNullable(userInfo);
        Optional o = optional.map(user -> user.toString());
        System.out.println(msg + ":" + o.get());
    }
}
