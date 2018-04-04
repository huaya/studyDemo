//package com.maxlong.netflix;
//
//import com.netflix.archaius.Config;
//import com.netflix.archaius.DefaultConfigLoader;
//import com.netflix.archaius.DefaultPropertyFactory;
//import com.netflix.archaius.api.Property;
//import com.netflix.archaius.api.exceptions.ConfigException;
//import com.netflix.archaius.config.DefaultCompositeConfig;
//import com.netflix.archaius.config.DefaultCompositeConfig.Builder;
//
///**
// * @author 作者 maxlong:
// * @version 创建时间：2016年7月4日 上午11:19:00
// * 类说明
// */
//public class NetflixProperty {
//
//	public static void main(String[] args) {
//		Config rootConfig;
//		boolean asd = false;
//		try {
//
//			if(asd){
//				Builder builder = DefaultConfigLoader.builder();
//				rootConfig = builder.build().newLoader().load("config.properties");
//			}else{
//				rootConfig = DefaultConfigLoader.builder().build().newLoader().load("config.properties");
//				Builder builder = DefaultCompositeConfig.builder();
//				builder.withConfig("config.properties",rootConfig);
//				//rootConfig = builder.build();
//			}
//
//			DefaultPropertyFactory defaultPropertyFactory = DefaultPropertyFactory.from(rootConfig);
//			Property<String> property = defaultPropertyFactory.getProperty("maxlong").asString("ddd");
//			String ddd = property.get();
//			System.out.println(ddd);
//		} catch (ConfigException e) {
//			e.printStackTrace();
//		}
//	}
//}
//