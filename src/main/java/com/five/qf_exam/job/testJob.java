package com.five.qf_exam.job;

import org.Guanpx.bigdata.qf_exam.mapper.AnswerPaperAvailableMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @description 测试拆分json
 * @author: Guanpx Guanpeixiang@foxmail.com
 * @create: 2018-10-18 21:07:58
 **/
public class testJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        //HDFS远程连接信息
        System.setProperty("HADOOP_USER_NAME", "root");
        conf.set("fs.defaultFS", "hdfs://192.168.128.6:9000");

        //MR&YARN相关配置
        conf.set("mapreduce.app-submission.cross-platform", "true");//允许跨平台提交jar包
        conf.set("mapreduce.job.jar", "E:\\ideaProject\\bigdata\\target/bigdata-all.jar");


        Job job = Job.getInstance(conf, "MR_test");

        job.setJarByClass(testJob.class);

        job.setMapperClass(AnswerPaperAvailableMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
