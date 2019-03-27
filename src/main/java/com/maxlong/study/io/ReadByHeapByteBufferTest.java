package com.maxlong.study.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-27 14:59
 */

public class ReadByHeapByteBufferTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        File data = new File("/tmp/data.txt");
        FileChannel fileChannel = new RandomAccessFile(data, "rw").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(4 * 1024 * 1024);
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(1000);
            new Thread(() -> {
                try {
                    fileChannel.read(buffer);
                    buffer.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}