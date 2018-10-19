package com.five.qf_exam.mapper;


import org.Guanpx.bigdata.qf_exam.util.MD5EncryptUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;


/**
 * @description 将answer_paper可用列提取到answer_paper_available
 * @author: Guanpx Guanpeixiang@foxmail.com
 * @create: 2018-10-19 15:07:19
 **/
public class AnswerPaperAvailableMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

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
        beforeRows.append(line[3]); //examinee_id 学号
        beforeRows.append("\001");
        try {
            beforeRows.append(MD5EncryptUtils.encryptMD5(line[4])); //examinee_name_MD5 姓名
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("md5 编码失败!");
        }
        beforeRows.append("\001");
        beforeRows.append(line[9]); //exam_time 答题耗时
        beforeRows.append("\001");
        beforeRows.append(line[10]); //submit_time 交卷时间
        beforeRows.append("\001");
        beforeRows.append(line[11]); //objective_mark 客观成绩
        beforeRows.append("\001");
        beforeRows.append(line[12]); //subject_mark 主观成绩
        beforeRows.append("\001");
        beforeRows.append(line[16]); //objective_answer_json 客观答题串
        beforeRows.append("\001");
        //按需求拼接后的字符串
        String beforeRowString = beforeRows.toString();
        context.write(new Text(beforeRowString), NullWritable.get());
    }
}