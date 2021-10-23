package com.lzl.transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzl.transactional.model.Result;
import com.sun.xml.internal.ws.api.client.WSPortInfo;

import java.io.IOException;
import java.util.*;

/**
 * @author liaozhilong
 * @date 2021/8/25 14:52
 * @Description
 */
public class Test {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        String s = strings.stream().filter(a -> a.equals("2")).findFirst().orElse("3");
        System.out.println(s);

        HashMap<String, String> map = new HashMap<>();
        map.put("2", "6");
        boolean b = map.containsKey("5");
        System.out.println(b);

        String str = "{\n" + "        \"212\":[\n" + "            837,\n" + "            342,\n" + "            1,\n"
            + "            304,\n" + "            32,\n" + "            37\n" + "        ]\n" + "   ,\n"
            + "        \"205\":[\n" + "            415\n" + "        ]\n" + "    ,\n" + "        \"270\":[\n"
            + "            172,\n" + "            147,\n" + "            415,\n" + "            93\n" + "        ]\n"
            + "}";
        String str2 = "{212:[837,342,1,304,32,37],205:[415], 270:[ 172,147, 415,  93  ]}";
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(3);
        integers.add(4);
        hashMap.put(1, integers);
        hashMap.put(2, integers);
        System.out.println(33);

        Map parseObject = JSONObject.parseObject(str, Map.class);
        Map parseObject2 = JSONObject.parseObject(str2, Map.class);

        JSONArray o = (JSONArray) parseObject.get("212");
        JSONArray o1 = (JSONArray) parseObject.get("205");
        JSONArray o2 = (JSONArray) parseObject.get("270");
        JSONArray o3 = (JSONArray) parseObject.get("333");
        JSONArray orDefault = (JSONArray) parseObject.getOrDefault("4444", new JSONArray());
        Integer cityId = 837;
        boolean contains = o.contains(cityId);
        System.out.println(contains);
        System.out.println(parseObject);

        JSONArray o22 = (JSONArray) parseObject2.get(212);
        JSONArray o12 = (JSONArray) parseObject2.get(205);
        JSONArray o222 = (JSONArray) parseObject2.get(270);
        JSONArray o32 = (JSONArray) parseObject2.get(333);
        JSONArray orDefault2 = (JSONArray) parseObject2.getOrDefault(4444, new JSONArray());
        Integer cityId2 = 837;
        boolean contains2 = o.contains(cityId2);
        System.out.println(contains2);
        System.out.println(parseObject2);

        String methodName = "add";
        String parameters = "123";
        String format = String.format("方法: %s,参数：%s,异常信息：", methodName, null);
        System.out.println(format);

        Long one = 100000000L;
        List<Long> idList = new ArrayList<>();
        idList.add(2000L);
        idList.add(3000L);

        List<Long> workerIncomeIdList = new ArrayList<>();
        workerIncomeIdList.add(one);
        workerIncomeIdList.addAll(idList);
        for (int i = 0, size = workerIncomeIdList.size(); i < size; i++) {
            System.out.println(workerIncomeIdList.get(i));
        }

        String config = "{\n" + "    \"212\": [\n"
            + "        837,342,1,102,148,304,3,202,79,837,265,541,669,172,606,2,188,241,740,93,158,319,415,342,37\n"
            + "    ],\n" + "    \"205\": [\n" + "        415\n" + "    ],\n" + "    \"270\": [\n"
            + "\t\t172,147,415,93\n" + "    ]\n" + "}";
        ObjectMapper om = new ObjectMapper();
        JavaType valueType = om.getTypeFactory().constructCollectionType(ArrayList.class, Integer.class);
        JavaType keyType = om.getTypeFactory().constructType(Integer.class);
        JavaType type = om.getTypeFactory().constructMapType(LinkedHashMap.class, keyType, valueType);
        try {
            Map<Integer, List<Integer>> objce = om.readValue(config, type);
            System.out.println(objce);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Result test = test();
        Object data = test.getData();

        Result result = test2();
        Result result1 = test3();

    }

    private static Result test() {
        return new Result<>(-1, "查询参数不能为空");
    }

    private static Result test2() {
        return new Result<>();
    }

    private static Result test3() {
        return new Result<>("成功");
    }

}
