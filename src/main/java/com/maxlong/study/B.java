package com.maxlong.study;

/**
 * Created on 2020/8/20.
 *
 * @author xxxx
 * @Email xxxx
 */
public class B extends A {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.getEmail());
        System.out.println(b.getSiteId());

    }
}
