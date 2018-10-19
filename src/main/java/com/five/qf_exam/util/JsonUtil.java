package com.five.qf_exam.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @description json解析为键值对
 * @author: Guanpx Guanpeixiang@foxmail.com
 * @create: 2018-10-18 21:02:24
 **/
public class JsonUtil {

    /**
     * 原json串结构 {题目id:{题目答案:ans,题目得分:score}}
     * 父json对象 {题目id:{题目答案:ans,题目得分:score}}
     * 子json对象 题目答案:ans,题目得分:score
     * @param jsonString 输入的json串
     * @return 返回id-score字典
     */
    public static Map<String, String> parseJson(String jsonString) {
        //结果集
        Map<String, String> idScore = new TreeMap<>();
        //json 解析器
        JsonParser jsonParser = new JsonParser();
        //父json对象
        JsonObject fatherJsonObject = (JsonObject) jsonParser.parse(jsonString);
        //父json对象下的keySet
        Set<String> fatherSet = fatherJsonObject.keySet();
        for (String questionID : fatherSet) {
            //获取key就是题目id
            JsonObject sonJsonObject = (JsonObject) jsonParser.parse(String.valueOf(fatherJsonObject.get(questionID)));
            //根据获取的key得到json对象从而得到score
            String score = String.valueOf(sonJsonObject.get("score"));
            //加入到结果集
            idScore.put(questionID, score);
        }
        return idScore;
    }
}
