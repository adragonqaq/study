package com.lzl.transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eren.liao
 * @date 2021/12/6 20:34
 */
public class Test2 {


    public static void main(String[] args) {

        List<StudentInfo> studentList = new ArrayList<>();
        studentList.add(new StudentInfo("1","李小明",true,BigDecimal.valueOf(1),1.76, LocalDate.of(2001,3,23)));
        studentList.add(new StudentInfo("2","张小丽",false,BigDecimal.valueOf(2),1.61,LocalDate.of(2001,6,3)));
        studentList.add(new StudentInfo("3","王大朋",true,BigDecimal.valueOf(3),1.82,LocalDate.of(2000,3,11)));
        studentList.add(new StudentInfo("3","陈小跑",false,BigDecimal.valueOf(4),1.67,LocalDate.of(2002,10,18)));


        BigDecimal reduce = studentList.stream().filter(a ->
                "1".equals(a.getType()) || "2".equals(a.getType())
        ).map(StudentInfo::getNum).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("reduce: "+reduce);
        BigDecimal limit = BigDecimal.valueOf(1);
        System.out.println("limit: "+limit);
        if(reduce.compareTo(limit)==1){
            System.out.println("reduce 比  limit 大");
        }else {
            System.out.println("444444");
        }


    }
}
