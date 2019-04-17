package com.maxlong.study.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019/4/16 18:20
 * 类说明:
 */
public class LowerCaseInputStream extends FilterInputStream {
    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    public LowerCaseInputStream(InputStream in) {
        super(in);
    }

    public int read() throws IOException {
        int c = super.read();
        return (c == -1)? c : Character.toLowerCase((char)c);
    }

    public int read(byte[] bytes, int offerset, int len) throws IOException {
        int result = super.read(bytes, offerset, len);
        for(int i=0;i< offerset + result; i++ ){
            bytes[i] = (byte) Character.toLowerCase((char)bytes[i]);
        }
        return result;
    }
}
