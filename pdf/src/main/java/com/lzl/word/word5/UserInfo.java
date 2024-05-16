package com.lzl.word.word5;


import lombok.Data;

@Data
public class UserInfo {

    @WordTableParams
    private String worker;

    @WordTableParams
    private String like;

    @WordTableParams
    private Integer workYear;

    @WordTableParams
    private String sex;

}
