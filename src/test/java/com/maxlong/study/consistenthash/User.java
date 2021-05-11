package com.maxlong.study.consistenthash;

import com.maxlong.study.serializable.UserInfo;

import java.util.List;

/**
 * Created on 2020/10/9.
 *
 * @author xxxx
 * @Email xxxx
 */
public class User {

    private List<UserInfo> users;

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }
}
