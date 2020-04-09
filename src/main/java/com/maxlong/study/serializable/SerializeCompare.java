package com.maxlong.study.serializable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月23日 上午9:15:55
 * 类说明
 */
public class SerializeCompare {

    public static void jdkSerializable(int loop, UserInfo userInfo) {
        for (int i = 0; i < loop; i++) {
            try (
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream os = new ObjectOutputStream(bos)
            ) {
                os.writeObject(userInfo);
                os.flush();
                bos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void byteArraySerializable(int loop, UserInfo userInfo) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        for (int i = 0; i < loop; i++) {
            userInfo.codeC(buffer);
        }
    }

}
 