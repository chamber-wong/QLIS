package com.five.qf_exam.mapper;

import org.Guanpx.bigdata.qf_exam.util.MD5EncryptUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @description 测试mapper
 * @author: Guanpx Guanpeixiang@foxmail.com
 * @create: 2018-10-18 21:08:31
 **/
public class TestMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //
        String[] line = value.toString().trim().split("\001");
        //json串之前的数据 按需求拼接
        StringBuffer beforeRows = new StringBuffer();
        //拼接过程
        beforeRows.append(line[1]); //exam_id 考试id
        beforeRows.append("\001");
        beforeRows.append(line[6]); //class_id 班级id
        beforeRows.append("\001");
        //按需求拼接后的字符串
        String beforeRowString = beforeRows.toString();
        context.write(new Text(beforeRowString), NullWritable.get());

//        //解析json串 得到jsonMap
//        Map<String, String> jsonMap = JsonUtil.parseJson(line[16]);
//        //得到jsonMap的keySet
//        Set<String> keySet = jsonMap.keySet();
//        //拼接题目ID以及
//        for (String keys : keySet) {
//            context.write(new Text(beforeRowString+keys+","+jsonMap.get(keys)),NullWritable.get());
//        }

    }
}