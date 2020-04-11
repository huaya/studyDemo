package com.maxlong.study.io;

import com.maxlong.common.Constant;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
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
        File sourceFile = new File("E:\\resources\\maxlong_1g.txt");
        File targetFile = new File("E:\\resources\\maxlong_1g_bak.txt");
        targetFile.delete();
        targetFile.deleteOnExit();
        Instant begin = Instant.now();
        copyByMapByteBuffer(sourceFile, targetFile);
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

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
            while (sourceChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                targetChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyByMapByteBuffer(File sourceFile, File targetFile) {
        long size = sourceFile.length();
        long position = 0;
        int bufferSize = (int) (size > Constant._1G ? Constant._1G : size);
        long partSize = size > Constant._1G ? Constant._1G : size;

        while (size > 0) {
            try (
                    RandomAccessFile sourceRFile = new RandomAccessFile(sourceFile, "r");
                    RandomAccessFile targetRFile = new RandomAccessFile(targetFile, "rw")
            ) {
                MappedByteBuffer inByteBuffer = sourceRFile.getChannel().map(FileChannel.MapMode.READ_ONLY, position, bufferSize);
                MappedByteBuffer outByteBuffer = targetRFile.getChannel().map(FileChannel.MapMode.READ_WRITE, position, partSize);

                int length = inByteBuffer.remaining();
                byte[] memo = new byte[length];
                inByteBuffer.get(memo, 0, length);
                outByteBuffer.put(memo);

                outByteBuffer.force();

                size = size - length;
                position = position + length;
                partSize = size > Constant._1G ? Constant._1G : size;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
