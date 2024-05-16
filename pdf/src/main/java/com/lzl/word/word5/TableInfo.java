package com.lzl.word.word5;



import lombok.Data;

@Data
public class TableInfo {

    @WordTableParams(colName = "名称",colNum = 1)
    private String name;

    @WordTableParams(colName = "年纪",colNum = 2)
    private String age;

    @WordTableParams(colName = "地址",colNum = 3)
    private String address;

    @WordTableParams(colName = "手机号",colNum = 4)
    private String telNo;

}
