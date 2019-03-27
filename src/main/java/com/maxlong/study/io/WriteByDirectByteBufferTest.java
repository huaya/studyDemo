package com.maxlong.study.io;

import sun.nio.ch.DirectBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

/**
 * @describe：
 * @author： ma.xl
 * @datetime： 2019-3-27 15:09
 */
public class WriteByDirectByteBufferTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
        System.in.read();
        ((DirectBuffer) buffer).cleaner().clean();
        new CountDownLatch(1).await();
    }
}