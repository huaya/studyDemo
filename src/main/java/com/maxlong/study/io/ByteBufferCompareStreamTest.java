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
        File sourceFile = new File("E:\\软件备份\\ideaIU-2018.1.8.exe");
        File targetFile = new File("C:\\Users\\OrderPlus\\Desktop\\ideaIU-2018.1.8.exe");
        targetFile.delete();
        Instant begin = Instant.now();
//        copyByByteBuffer(sourceFile, targetFile);
        copyByStream(sourceFile, targetFile);
        System.out.println("耗时：" + Duration.between(begin, Instant.now()).toMillis());
    }

    private static void copyByStream(File sourceFile, File targetFile) {
        try (
                FileInputStream inputStream = new FileInputStream(sourceFile);
                FileOutputStream outputStream = new FileOutputStream(targetFile)
        ) {
            byte[] readb = new byte[1024 * 1024];
            while (inputStream.read(readb) != -1){
                outputStream.write(readb);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyByByteBuffer(File sourceFile, File targetFile) {
        try (
                FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
                FileChannel targetChannel = new FileOutputStream(targetFile).getChannel()
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
