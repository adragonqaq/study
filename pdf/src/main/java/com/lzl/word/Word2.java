package com.lzl.word;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

public class Word2 {
    public static void main(String[] args) {
        readWord();
    }

    public static void readWord() {
        XWPFDocument document = null;
        FileOutputStream out = null;
        try {
            // 读取Word模板文件
            document = new XWPFDocument(new FileInputStream("f:/Word模板.docx"));

            // 获取第一个表格(表格下标从0开始)
            XWPFTable table = document.getTables().get(0);

            // --------------------在指定行下面添加一行,并创建指定数量的单元格start--------------------
            int rowIndex = 3;
            // 在指定行下面添加一行(下标从0开始),这里添加到第4行
            XWPFTableRow newRow = table.insertNewTableRow(rowIndex);
            // 新行创建单元格
            for (int i = 0; i < 6; i++) {
                if (i % 2 == 1) {
                    newRow.createCell(); // 仅创建新单元格
                    continue;
                }
                newRow.createCell().setText("新单元格" + (rowIndex + 1) + (i + 1)); // 创建新单元格并填充内容
            }
            // --------------------在指定行下面添加一行,并创建指定数量的单元格end--------------------

            // 填充表格内容
            XWPFTableRow tableRow = null;
            List<XWPFTableCell> cellList = null;

            List<XWPFTableRow> rowList = table.getRows();
            for (int i = 0; i < rowList.size(); i++) {
                // 如果是刚才新添加的行,则不填充表格内容
                if (i == rowIndex) {
                    continue;
                }
                tableRow = rowList.get(i);
                cellList = tableRow.getTableCells();
                for (int j = 0; j < cellList.size(); j++) {
                    cellList.get(j).setText("info " + (i + 1) + (j + 1));
                }
            }

            // --------------------在指定行下面添加一行,并创建指定数量的单元格start--------------------
            rowIndex = 5;
            // 在指定行下面添加一行(下标从0开始),这里添加到第6行
            XWPFTableRow newRow2 = table.insertNewTableRow(rowIndex);
            // 新行创建单元格
            for (int i = 0; i < 6; i++) {
                if (i % 2 == 0) {
                    newRow2.createCell(); // 仅创建新单元格
                    continue;
                }
                newRow2.createCell().setText("新单元格" + (rowIndex + 1) + (i + 1)); // 创建新单元格并填充内容
            }
            // --------------------在指定行下面添加一行,并创建指定数量的单元格end--------------------

            // 表格末尾添加一行
            XWPFTableRow newRow3 = table.createRow();

            // 设置新行的单元格内容
            newRow3.getCell(0).setText("新单元格 1");
            newRow3.getCell(1).setText("新单元格 2");
            newRow3.getCell(2).setText("新单元格 3");

            // 保存修改后的Word文件
            out = new FileOutputStream("f:/新word.docx");
            document.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    // 关闭输出流
                    out.close();
                    if (document != null) {
                        // 关闭Word文档
                        document.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("新Word文档生成成功！");
    }
}
