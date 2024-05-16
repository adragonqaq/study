package com.lzl.word;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import lombok.SneakyThrows;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author:qinjinjin
 **/
public class Word3 {
    XWPFDocument document = null;

    List<String> ratio;

    List<InputStream> streamList;

    public Word3(List<InputStream> streamLis, List<String> ratio) {
        this.streamList = streamLis;
        this.ratio = ratio;
    }

    /**
     * 替换段落文本
     *
     * @param document docx解析对象
     */
    public void changeText(XWPFDocument document, int iii) {
        //获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        int z = 0;
        int h = 0;
        // 获取word的所有表格
        List<XWPFTable> xwpfTables = document.getTables();
        for (XWPFTable table : xwpfTables) {
            // 获取表格行
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                // 表格列
                List<XWPFTableCell> tableCells = row.getTableCells();
                for (XWPFTableCell cell : tableCells) {
                    // 列里面的内容
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun xwpfRun : runs) {
                            // 将列里面的$换成
                            if (xwpfRun.toString().equals("$")) {
                                xwpfRun.setSubscript(VerticalAlign.BASELINE);
                                xwpfRun.setText(ratio.remove(0), 0);
                            }
                        }
                    }
                }
            }
        }
        for (XWPFParagraph paragraph : paragraphs) {
            //获取到段落中的所有文本内容
            String text = paragraph.getText();
            // 记录图片需要放在哪个段落的下面
            if (text.equals("周期图形")) {
                h = z;
            }
            System.out.println(text);
            //判断此段落中是否有需要进行替换的文本
            if (checkText(text)) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    if (run.toString().equals("$")) {

                        //替换模板原来位置
                        run.setText(run.toString().replaceAll("\\$", ratio.remove(0)), 0);
                    }
                }
            }
            z++;
        }
        // 设置图片
        XWPFRun run = paragraphs.get(h).createRun();
        try {
            for (InputStream inputStream : streamList) {
                if (iii == 1) {
                    run.addPicture(inputStream,
                            XWPFDocument.PICTURE_TYPE_PNG,
                            "1.png",
                            Units.toEMU(560),
                            Units.toEMU(120));
                } else {
                    run.addPicture(inputStream,
                            XWPFDocument.PICTURE_TYPE_PNG,
                            "1.png",
                            Units.toEMU(540),
                            Units.toEMU(90));
                }

                run.addTab();
            }
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断文本中是否包含$
     *
     * @param text 文本
     * @return 包含返回true, 不包含返回false
     */
    public static boolean checkText(String text) {
        boolean check = false;
        if (text.contains("$")) {
            check = true;
        }
        return check;
    }

    /**
     * 生成文件的入口
     *
     * @param templatePath 模板的路径
     * @param outPath      输出路径
     * @param name         名字
     * @param i            作为输出图片宽高的标志
     */
    public void saveReportFile(String templatePath, String outPath, String name, int i) {
        InputStream in;
        try {
            in = new FileInputStream(templatePath);
            //获取docx解析对象
            document = new XWPFDocument(in);
            changeText(document, i);
            File file1 = new File("outPath");
            if (!file1.exists()) {
                file1.mkdirs();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(outPath + name + ".docx");
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(new FileOutputStream(outPath + name + ".docx"));
            document.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();

            String s = outPath + name + ".docx";

            String targetPath = s.substring(0, templatePath.lastIndexOf("."));
            documents4jWordToPdf(outPath + name + ".docx", targetPath + new Date().getTime() + ".pdf");
            // word转pdf
            // WordToPdfAsposeUtil.docToPdf(outPath + name + ".docx", outPath + name + ".pdf");
            // File file = new File(outPath + name + ".docx");
            // file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过documents4j 实现word转pdf
     *
     * @param sourcePath 源文件地址 如 /root/example.doc
     * @param targetPath 目标文件地址 如 /root/example.pdf
     */
    public static void documents4jWordToPdf(String sourcePath, String targetPath) {
        File inputWord = new File(sourcePath);
        File outputFile = new File(targetPath);
        try {
            InputStream docxInputStream = new FileInputStream(inputWord);
            OutputStream outputStream = new FileOutputStream(outputFile);
            IConverter converter = LocalConverter.builder().build();
            boolean execute = converter.convert(docxInputStream)
                    .as(DocumentType.DOCX)
                    .to(outputStream)
                    .as(DocumentType.PDF).schedule().get();
//                    .execute();
            outputStream.close();
            docxInputStream.close();

            System.out.println("转换完毕 targetPath = " + outputFile.getAbsolutePath());
            converter.shutDown();
            return;
        } catch (Exception e) {
            System.out.println("[documents4J] word转pdf失败:" + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * base64转为文件流
     */
    @SneakyThrows
    public static InputStream base64ToFile(String base64FileStr) {
        if (base64FileStr.contains("data:image")) {
            base64FileStr = base64FileStr.substring(base64FileStr.indexOf(",") + 1);
        }
        base64FileStr = base64FileStr.replace("\r\n", "");
        // 在用户temp目录下创建临时文件
        File file = File.createTempFile(UUID.randomUUID().toString(), "png");
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            // 用Base64进行解码后获取的字节数组可以直接转换为文件
            byte[] bytes = Base64.getDecoder().decode(base64FileStr);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new FileInputStream(file);
    }

    public static void main(String[] args) throws Exception{
        List<InputStream> streamList = new ArrayList<>();
        File file = new File("C:\\Users\\shiji\\Desktop\\test\\微信图片头像.jpg");
        streamList.add(new FileInputStream(file));

        List<String> ratio = new ArrayList<>();
        ratio.add("1");
        ratio.add("2");
        ratio.add("3");
        Word3 wordUtil = new Word3(streamList, ratio);
        wordUtil.saveReportFile("C:\\Users\\shiji\\Desktop\\test\\a.docx", "C:\\Users\\shiji\\Desktop\\test\\", "统计", 1);
    }

}

