package com.maxlong.service.impl;

import com.maxlong.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/6/19 21:55
 * 类说明:
 */
@Service
public class UserServiceImpl implements UserService {


    @Override
    public String getName(int id) {
        System.out.println("------getName------");
        return "Tom";
    }

    @Override
    public Integer getAge(int id) {
        System.out.println("------getAge------");
        return 10;
    }
}
