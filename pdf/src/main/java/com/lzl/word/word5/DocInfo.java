package com.lzl.word.word5;


import lombok.Data;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;

/**
 * 段落列表包装类
 * @author taolongqing
 *
 */
@Data
public class DocInfo {
    /**
     * 段落
     */
    private List<XWPFParagraph> xWPFParagraphs;
    /**
     * 表格
     */
    private XWPFTable xwpfTable;
    /**
     * 头部
     */
    private XWPFHeader xwpfHeader;

    public DocInfo(List<XWPFParagraph> xWPFParagraphs, XWPFTable xwpfTable) {
        super();
        this.xWPFParagraphs = xWPFParagraphs;
        this.xwpfTable = xwpfTable;
    }

    public DocInfo() {
        // TODO Auto-generated constructor stub
    }
}