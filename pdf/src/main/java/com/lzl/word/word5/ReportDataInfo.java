package com.lzl.word.word5;


import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReportDataInfo extends BaseFileTemp{

    @WordParams
    private String year;
    @WordParams
    private String month;
    @WordParams
    private String title;

    @WordParams(type=WordParamsType.FILE)
    private ImageInf image1;

    @WordParams(type=WordParamsType.FILE)
    private ImageInf image2;


    @WordParams(type=WordParamsType.LIST)
    private List<TableInfo> tableList1;

    @WordParams(type=WordParamsType.LIST)
    private List<UserInfo> tableList2;


}
