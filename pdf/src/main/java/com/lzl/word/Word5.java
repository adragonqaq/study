package com.lzl.word;

import com.lzl.word.word5.ImageInf;
import com.lzl.word.word5.ReportDataInfo;
import com.lzl.word.word5.TableInfo;
import com.lzl.word.word5.TableLoopReplaceHandle;
import com.lzl.word.word5.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eren.liao
 * @date 2024/1/18 18:16
 */
public class Word5 {


    public  static void main(String[] args) {
        ReportDataInfo reportDataInfo = new ReportDataInfo();
        Word5 wordTester = new Word5();
        reportDataInfo.setYear("2019");//文本
        reportDataInfo.setMonth("10");//文本
        reportDataInfo.setImage1(new ImageInf(400,100,"C:\\Users\\shiji\\Desktop\\test\\微信图片头像.jpg")); //图片
        reportDataInfo.setImage2(new ImageInf(400,100,"C:\\Users\\shiji\\Desktop\\test\\微信图片头像.jpg"));//图片
        reportDataInfo.setTempPath("C:\\Users\\shiji\\Desktop\\test\\word示例模板.docx");    //word 模板路径
        reportDataInfo.setOutPath("C:\\Users\\shiji\\Desktop\\test\\testResult1.docx"); //替换后word的导出路径
        //word替换工具的实例，该类继承自AbstractWordTemple，具体细节见《Word模板工具类API.docx》
        wordTester.buildListData(reportDataInfo);
        TableLoopReplaceHandle wordUtil = new TableLoopReplaceHandle();
        //调用主方法执行报表数据导出到word
        wordUtil.findLabelAndReplace(reportDataInfo);
    }


    private void buildListData(ReportDataInfo reportDataInfo) {
        // TODO Auto-generated method stub
        List<TableInfo> tableList1 = new ArrayList<TableInfo>();
        for(int i=0;i<10;i++) {
            TableInfo tableInfo = new TableInfo();
            tableInfo.setName("张" + i);
            tableInfo.setAddress("福田保税区");
            tableInfo.setAge("18");
            tableInfo.setTelNo("1888885"+i+"278");
            tableList1.add(tableInfo);
        }
        reportDataInfo.setTableList1(tableList1);


        List<UserInfo> tableList2 = new ArrayList<UserInfo>();
        for(int i=0;i<10;i++) {
            UserInfo tableInfo = new UserInfo();
            tableInfo.setWorker("CEO");
            tableInfo.setLike("女");
            tableInfo.setWorkYear(5+i);
            tableInfo.setSex("男");
            tableList2.add(tableInfo);
        }
        reportDataInfo.setTableList2(tableList2);
    }
}
