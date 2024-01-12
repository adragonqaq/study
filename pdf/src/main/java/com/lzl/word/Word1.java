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

public class Word1 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        // 1.创建一个新的Word文档
        XWPFDocument document = new XWPFDocument();
        FileOutputStream fos = null;
        try {
            // 2.创建一个新的表格，11行7列
            XWPFTable table = document.createTable(11, 7);

            // 3.设置表格样式
            table.getCTTbl().addNewTblPr().addNewTblStyle().setVal("Table Grid");
            table.setWidth("100%"); // 设置表格宽度为100%

            // 4.填充表格内容
            XWPFTableCell cell = null;
            for (int row = 0; row < 11; row++) { // 循环遍历表格的行
                for (int col = 0; col < 7; col++) { // 循环遍历表格的列
                    // 获取当前单元格
                    cell = table.getRow(row).getCell(col);
                    // 设置单元格内容垂直居中
                    cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                    // 设置单元格内容水平居中(获取单元格中的第一个段落对象)
                    cell.getParagraphArray(0).setAlignment(ParagraphAlignment.CENTER);
                    // 设置单元格内容
                    cell.setText("行 " + (row + 1) + ", 列 " + (col + 1));
                }
            }

            /**

             // 5.合并单元格方式一 // 5.1 合并第1行的第2列到第4列
             table.getRow(0).getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
             table.getRow(0).getCell(2).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
             table.getRow(0).getCell(3).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);

             // 5.2 合并第1列的第3行到第4行
             table.getRow(2).getCell(0).getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
             table.getRow(3).getCell(0).getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);

             // 5.3 合并第4行第4列、第5列、第5行第4列、第5列为一个单元格 // 5.3.1 合并第4行的第4列到第5列
             table.getRow(3).getCell(3).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
             table.getRow(3).getCell(4).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
             // 5.3.2 合并第5行的第4列到第5列
             table.getRow(4).getCell(3).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
             table.getRow(4).getCell(4).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
             // 5.3.3 合并第4列的第4行到第5行
             table.getRow(3).getCell(3).getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
             table.getRow(4).getCell(3).getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);

             **/

            // 6.合并单元格方式二(推荐)
            // 6.1 水平合并第1行的第2列到第4列
            mergeCellsByHorizontal(table, 0, 1, 3);
            // 6.2 垂直合并第1列的第3行到第4行
            mergeCellsByVertically(table, 0, 2, 3);
            // 6.3 合并第4行第4列、第5列、第5行第4列、第5列为一个单元格
            // 6.3.1 合并第4行的第4列到第5列
            mergeCellsByHorizontal(table, 3, 3, 4);
            // 6.3.2 合并第5行的第4列到第5列
            mergeCellsByHorizontal(table, 4, 3, 4);
            // 6.3.3 合并第4列的第4行到第5行
            mergeCellsByVertically(table, 3, 3, 4);

            // 保存文档
            // 输出流，用于将文档写入磁盘
            fos = new FileOutputStream("f:/test.docx");
            // 将文档写入输出流
            document.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    // 关闭输出流
                    fos.close();
                    if (document != null) {
                        // 关闭Word文档
                        document.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Word文档生成成功！");
    }

    // ==================== private method ====================
    /**
     * <h5>描述:水平方向合并单元格<h5>
     * @param table 表格
     * @param rowIndex 合并列所在的行下标(从0开始)
     * @param startCellIndex 开始合并的列下标(从0开始)
     * @param endCellIndex 结束合并的列下标(从0开始)
     */
    private static void mergeCellsByHorizontal(XWPFTable table, int rowIndex, int startCellIndex, int endCellIndex) {
        String str = "";
        for (int i = startCellIndex; i <= endCellIndex; i++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(i);
            str = "table.getRow("+rowIndex+").getCell("+i+")";
            if (i == startCellIndex) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                System.out.println(str + ".getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);");
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
                System.out.println(str + ".getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);");
            }
        }
    }

    /**
     * <h5>描述:垂直方向合并单元格<h5>
     * @param table 表格
     * @param columnIndex 合并行所在列下标(从0开始)
     * @param startCell 开始合并的行下标(从0开始)
     * @param endCell 结束合并的行下标(从0开始)
     */
    private static void mergeCellsByVertically(XWPFTable table, int columnIndex, int startRowIndex, int endRowIndex) {
        String str = "";
        for (int i = startRowIndex; i <= endRowIndex; i++) {
            XWPFTableCell cell = table.getRow(i).getCell(columnIndex);
            str = "table.getRow("+i+").getCell("+columnIndex+")";
            if (i == startRowIndex) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
                System.out.println(str + ".getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);");
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
                System.out.println(str + ".getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);");
            }
        }
    }
}