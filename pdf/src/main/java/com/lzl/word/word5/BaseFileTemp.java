package com.lzl.word.word5;

/**
 * @author eren.liao
 * @date 2024/1/18 18:17
 */

import org.apache.commons.lang3.StringUtils;

import java.io.File;


public class BaseFileTemp {
    //导出路径
    private String outPath;
    private String tempPath;

    public String getTempPath() {
        return tempPath;
    }
    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }
    public String getOutPath() {
        return outPath;
    }
    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public void vaildata() {
        if(StringUtils.isEmpty(tempPath)) {
            throw new RuntimeException("模板文件地址为空");
        }
        File file = new File(tempPath);
        if(!file.exists()) {
            throw new RuntimeException("模板文件不存在");
        }
        if(!file.isFile()) {
            throw new RuntimeException("模板地址异常，必须是文件地址");
        }

    }

}