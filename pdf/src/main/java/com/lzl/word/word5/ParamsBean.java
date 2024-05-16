package com.lzl.word.word5;

import lombok.Data;

@Data
public class ParamsBean {

    private int type;//类型
    private String field;//预备替换的字段
    private Object value;//预备替换的内容

    private BaseFileTemp baseWordTemp;

    public BaseFileTemp getBaseWordTemp() {
        return baseWordTemp;
    }
    public void setBaseWordTemp(BaseFileTemp baseWordTemp) {
        this.baseWordTemp = baseWordTemp;
    }
    @Override
    public String toString() {
        return "ParamsBean [type=" + type + ", field=" + field + ", value=" + value + "]";
    }





}
