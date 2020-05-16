package com.maxlong.study.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created on 2020/5/15.
 *
 * @author MaXiaolong
 * @Email hu5624548@163.com
 */
public class MaxTemperatureReduce extends MapReduceBase implements Reducer<LongWritable, Text, Text, IntWritable> {
    @Override
    public void reduce(LongWritable longWritable, Iterator<Text> iterator, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {

    }
}
