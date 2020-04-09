package com.maxlong.study.io;

import com.maxlong.common.Constant;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;

/**
 * Created on 2020/4/9.
 *
 * @author MaXiaolong
 */
public class WriteFileTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        File targetFile = new File("E:\\resources\\maxlong_1g.txt");
        targetFile.delete();
        Instant begin = Instant.now();
        writeBigFileByMapByteBuffer(targetFile, Constant._1G);
        System.out.println("耗时：" + Duration.between(begin, Instant.now()).toMillis());
    }

    private static void writeByMapByteBuffer(File targetFile, long size) {
        try (RandomAccessFile accessFile = new RandomAccessFile(targetFile, "rw")) {
            FileChannel fileChannel = accessFile.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, size);

            byte[] bytes = "1234567890哈哈哈发".getBytes("UTF-8");
            while (mappedByteBuffer.remaining() > bytes.length) {
                mappedByteBuffer.put(bytes);
            }
            mappedByteBuffer.force();
            System.out.println("Writing to Memory Mapped File is completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeBigFileByMapByteBuffer(File targetFile, long size) throws UnsupportedEncodingException {
        long position = 0;
        long partSize = Constant._1G;
        byte[] bytes = "1234567890哈哈哈发".getBytes("UTF-8");

        while (size > 0 && size > bytes.length) {
            try (RandomAccessFile accessFile = new RandomAccessFile(targetFile, "rw")) {
                FileChannel fileChannel = accessFile.getChannel();
                MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, position, partSize);

                while (mappedByteBuffer.remaining() > bytes.length) {
                    mappedByteBuffer.put(bytes);
                    position = position + bytes.length;
                    size = size - bytes.length;
                }
                mappedByteBuffer.force();
                System.out.println("Writing to Memory Mapped File is completed");
            } catch (Exception e) {
                e.printStackTrace();
            }
            partSize = size > Constant._1G ? Constant._1G : size;
        }
    }

}
