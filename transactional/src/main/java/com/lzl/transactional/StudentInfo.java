package com.lzl.transactional;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author eren.liao
 * @date 2021/12/6 20:39
 */
@Data
public class StudentInfo  {

    private String type;

    //名称
    private String name;

    //性别 true男 false女
    private Boolean gender;

    private BigDecimal num;

    //身高
    private Double height;

    //出生日期
    private LocalDate birthday;

    public StudentInfo(String type,String name, Boolean gender, BigDecimal num, Double height, LocalDate birthday){
        this.type = type;
        this.name = name;
        this.gender = gender;
        this.num = num;
        this.height = height;
        this.birthday = birthday;
    }



    public static void printStudents(List<StudentInfo> studentInfos){
        System.out.println("[姓名]\t\t[性别]\t\t[年龄]\t\t[身高]\t\t[生日]");
        System.out.println("----------------------------------------------------------");
        studentInfos.forEach(s->System.out.println(s.toString()));
        System.out.println(" ");
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
