package com.maxlong.study.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;

/**
 * Created on 2020/4/8.
 *
 * @author MaXiaolong
 */
public class ByteBufferCompareStreamTest {

    public static void main(String[] args) throws IOException {
        File sourceFile = new File("C:\\Users\\OrderPlus\\Desktop\\xxxxx.rar");
        File targetFile = new File("C:\\Users\\OrderPlus\\Desktop\\xxxxx222.rar");
        targetFile.delete();
        Instant begin = Instant.now();
        copyByByteBuffer(sourceFile, targetFile);
//        copyByStream(sourceFile, targetFile);
        System.out.println("耗时：" + Duration.between(begin, Instant.now()).toMillis());
    }

    public static void copyByStream(File sourceFile, File targetFile) {
        try (
                FileInputStream inputStream = new FileInputStream(sourceFile);
                FileOutputStream outputStream = new FileOutputStream(targetFile)
        ) {
            byte[] readb = new byte[1024 * 1024];
            while (inputStream.read(readb) != -1) {
                outputStream.write(readb);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyByByteBuffer(File sourceFile, File targetFile) {
        try (
                RandomAccessFile sourceRFile = new RandomAccessFile(sourceFile, "r");
                RandomAccessFile targetRFile = new RandomAccessFile(targetFile, "rw");
                FileChannel sourceChannel = sourceRFile.getChannel();
                FileChannel targetChannel = targetRFile.getChannel()
        ) {

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
            while (sourceChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                targetChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
