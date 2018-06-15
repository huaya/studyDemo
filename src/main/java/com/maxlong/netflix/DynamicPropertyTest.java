package com.maxlong.netflix;

import cn.com.sand.component.config.ConfigBuilder;
import cn.com.sand.component.config.ConfigURLBuilder;
import com.netflix.archaius.api.Property;
import com.netflix.archaius.config.DefaultCompositeConfig;
import com.sand.abacus.config.DynamicPropertyHelper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2018-06-15 0015 11:27
 * 类说明
 */
public class DynamicPropertyTest {

    public static void main(String[] args) {
        try {
            String fileName = "config/config";
            String url = fileName + ".properties";
            ConfigBuilder dynamicLoadProperty = new ConfigURLBuilder(url);
            DefaultCompositeConfig.Builder builder = DefaultCompositeConfig.builder();
            builder.withConfig(fileName, dynamicLoadProperty.createConfig());
            cn.com.sand.component.config.DynamicPropertyHelper.addConfig(builder.build());

            for(int i = 0 ;i<5000 ;i ++) {
                Thread.sleep(100);
                String property =  DynamicPropertyHelper.getExtensionStringProperty("maxlong", null);
                System.out.println(property);
            }

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
