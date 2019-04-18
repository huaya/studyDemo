package com.maxlong.study.spring.ComponentProvider;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.Set;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年7月6日 下午1:57:28
 * 类说明
 */
public class ComponentProviderTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String packagePath = "com.maxlong.study.service.ComponentProvider";
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AssignableTypeFilter(IWarmup.class));
		Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents(packagePath);
		for(BeanDefinition beanDefinition : beanDefinitions) {
			String className = beanDefinition.getBeanClassName();
			Class<IWarmup> clazz = (Class<IWarmup>) Class.forName(className);
			IWarmup object = clazz.newInstance();
			System.out.println(object.sayHello());
		}
	}
}
 