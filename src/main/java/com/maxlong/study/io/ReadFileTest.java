package com.maxlong.study.io;

import com.maxlong.common.Constant;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;

/**
 * Created on 2020/4/9.
 *
 * @author MaXiaolong
 */
public class ReadFileTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        File targetFile = new File("E:\\resources\\maxlong_read.txt");
        Instant begin = Instant.now();
        readFileByMapByteBuffer(targetFile);
        System.out.println("耗时：" + Duration.between(begin, Instant.now()).toMillis());
    }

    private static void readFileByMapByteBuffer(File targetFile) {
        try (RandomAccessFile accessFile = new RandomAccessFile(targetFile, "rw")) {
            FileChannel fileChannel = accessFile.getChannel();
            MappedByteBuffer inByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            while (inByteBuffer.hasRemaining()) {
                int length = 10;
                if(inByteBuffer.remaining() < 10) {
                    length = inByteBuffer.remaining();
                }
                byte[] memo = new byte[length];
                inByteBuffer.get(memo, 0, length);
                System.out.println(new String(memo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
