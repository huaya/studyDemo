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

/***
 @Documented –注解是否将包含在JavaDoc中
 @Retention  –什么时候使用该注解
	 ● RetentionPolicy.SOURCE : 在编译阶段丢弃。这些注解在编译结束之后就不再有任何意义，所以它们不会写入字节码。@Override, @SuppressWarnings都属于这类注解。
	 ● RetentionPolicy.CLASS : 在类加载的时候丢弃。在字节码文件的处理中有用。注解默认使用这种方式
	 ● RetentionPolicy.RUNTIME : 始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
 @Target     –注解用于什么地方。默认任何地方
	 ● ElementType.CONSTRUCTOR:用于描述构造器
	 ● ElementType.FIELD:成员变量、对象、属性（包括enum实例）
	 ● ElementType.LOCAL_VARIABLE:用于描述局部变量
	 ● ElementType.METHOD:用于描述方法
	 ● ElementType.PACKAGE:用于描述包
	 ● ElementType.PARAMETER:用于描述参数
	 ● ElementType.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 @Inherited  –是否允许子类继承该注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstAnnotation {

	//使用的时候 @FirstAnnotation(value="xxx")
	public String name() default "";

	public String id() default "";

	public String[] names() default "";

}
 