package com.maxlong.netflix;

import com.netflix.archaius.DefaultConfigLoader;
import com.netflix.archaius.DefaultPropertyFactory;
import com.netflix.archaius.api.Config;
import com.netflix.archaius.api.Property;
import com.netflix.archaius.api.PropertyContainer;
import com.netflix.archaius.api.exceptions.ConfigException;
import com.netflix.archaius.property.DefaultPropertyContainer;
import io.netty.util.concurrent.EventExecutor;

import java.lang.reflect.Field;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年7月4日 上午11:19:00
 * 类说明
 */
public class NetflixProperty {

	public static void main(String[] args) {
		try {

			ExecutorService executorService = Executors.newCachedThreadPool();

			Config rootConfig= DefaultConfigLoader.builder().build().newLoader().load("config/config.properties");
			DefaultPropertyFactory defaultPropertyFactory = DefaultPropertyFactory.from(rootConfig);

			Semaphore semaphore = new Semaphore(20);
			for(int i = 0 ;i<5000 ;i ++) {
				semaphore.acquire();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Field cachField = DefaultPropertyContainer.class.getDeclaredField("cache");
							cachField.setAccessible(true);

							DefaultPropertyContainer container = (DefaultPropertyContainer) defaultPropertyFactory.getProperty("maxlong");
							CopyOnWriteArrayList cashList = (CopyOnWriteArrayList) cachField.get(container);

							Property<String> property = container.asString(null);
							System.out.println("元素大小：" + cashList.size() + "属相值： " + property.get());

						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}

					}
				});
				semaphore.release();
			}

		} catch (ConfigException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
