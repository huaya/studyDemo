package com.maxlong.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年7月4日 上午10:13:52
 * 类说明
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstAnnotation {
	public String value() default ""; //使用的时候 @FirstAnnotation(value="xxx")
}
 