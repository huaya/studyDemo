package com.maxlong.annotation;

import java.lang.annotation.Annotation;
/**
 * @author 作者 maxlong:
 * @version 创建时间：2018-04-10 0010 16:44
 * 类说明
 */
@FirstAnnotation(id="123456",name="ma.xl")
public class People {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        Annotation[] annotations = People.class.getAnnotations();
        System.out.println(annotations.length);
        for(Annotation annotation : annotations){
            FirstAnnotation firstAnnotation = (FirstAnnotation) annotation;
            System.out.println(firstAnnotation);
            System.out.println(firstAnnotation.id());
            System.out.println(firstAnnotation.name());
        }


    }
}
