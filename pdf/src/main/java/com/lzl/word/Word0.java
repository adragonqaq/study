//package com.lzl.word;
//
//
//import com.alibaba.fastjson.JSON;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.xwpf.usermodel.*;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
//
//import java.io.FileOutputStream;
//import java.math.BigInteger;
//import java.util.*;
//
//public class Word0 {
//    // word文档中的待填参数
//    private Map<String,String> paramsMap;
//    // 规则列表,变化的,想展示哪些,就写对应序号,1~11
//    private List<Integer> ruleList;
//    // 动态表格数据
//    private List<ConfirmTable> confirmTableList;
//
//    private XWPFDocument xwpfDocument;
//    // 规则合并行索引记录
//    private int table1RowIndex=0;
//
//    public static void main(String[] args) throws Exception{
//        HashMap<String,String> objectObjectHashMap = new HashMap<>();
//        List<ConfirmTable> objects = JSON.parseArray("[{\n" +
//                "  \"vehicleColor\": \"黄\",\n" +
//                "  \"vehiclePlate\": \"皖A11111\",\n" +
//                "  \"cardInfoList\": [\n" +
//                "    {\n" +
//                "      \"cardType\": \"卡种1\",\n" +
//                "      \"openCardTime\": \"2022-01-01 10:00:00\",\n" +
//                "      \"productInfoList\": [\n" +
//                "        {\n" +
//                "          \"productType\": \"产品1\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        },{\n" +
//                "          \"productType\": \"产品2\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    },{\n" +
//                "      \"cardType\": \"卡种2\",\n" +
//                "      \"openCardTime\": \"2022-11-01 10:00:00\",\n" +
//                "      \"productInfoList\": [\n" +
//                "        {\n" +
//                "          \"productType\": \"产品2\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    }\n" +
//                "  ]\n" +
//                "},{\n" +
//                "  \"vehicleColor\": \"黄\",\n" +
//                "  \"vehiclePlate\": \"皖A22222\",\n" +
//                "  \"cardInfoList\": [\n" +
//                "    {\n" +
//                "      \"cardType\": \"卡种1\",\n" +
//                "      \"openCardTime\": \"2022-01-01 10:00:00\",\n" +
//                "      \"productInfoList\": [\n" +
//                "        {\n" +
//                "          \"productType\": \"产品2\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    },{\n" +
//                "      \"cardType\": \"卡种2\",\n" +
//                "      \"openCardTime\": \"2022-11-01 10:00:00\",\n" +
//                "      \"productInfoList\": [\n" +
//                "        {\n" +
//                "          \"productType\": \"产品1\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        },{\n" +
//                "          \"productType\": \"产品2\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    }\n" +
//                "  ]\n" +
//                "},{\n" +
//                "  \"vehicleColor\": \"黄\",\n" +
//                "  \"vehiclePlate\": \"皖A22222\",\n" +
//                "  \"cardInfoList\": [\n" +
//                "    {\n" +
//                "      \"cardType\": \"卡种1\",\n" +
//                "      \"openCardTime\": \"2022-01-01 10:00:00\",\n" +
//                "      \"productInfoList\": [\n" +
//                "        {\n" +
//                "          \"productType\": \"产品2\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        },{\n" +
//                "          \"productType\": \"产品1\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        },{\n" +
//                "          \"productType\": \"产品2\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    },{\n" +
//                "      \"cardType\": \"卡种2\",\n" +
//                "      \"openCardTime\": \"2022-11-01 10:00:00\",\n" +
//                "      \"productInfoList\": [\n" +
//                "        {\n" +
//                "          \"productType\": \"产品1\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        },{\n" +
//                "          \"productType\": \"产品2\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        },{\n" +
//                "          \"productType\": \"产品1\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        },{\n" +
//                "          \"productType\": \"产品2\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    }\n" +
//                "  ]\n" +
//                "},{\n" +
//                "  \"vehicleColor\": \"黄\",\n" +
//                "  \"vehiclePlate\": \"皖A33333\",\n" +
//                "  \"cardInfoList\": [\n" +
//                "    {\n" +
//                "      \"cardType\": \"卡种1\",\n" +
//                "      \"openCardTime\": \"2022-01-01 10:00:00\",\n" +
//                "      \"productInfoList\": [\n" +
//                "        {\n" +
//                "          \"productType\": \"产品2\",\n" +
//                "          \"startTime\": \"2022-01-01 09:00:00\",\n" +
//                "          \"endTime\": \"2022-09-01 10:00:00\"\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}]", ConfirmTable.class);
//        Word0 etcServiceConfirmingOrder = new Word0(objectObjectHashMap, Arrays.asList(1,2,3,4),objects);
//        etcServiceConfirmingOrder.build();
//    }
//
//    public Word0(Map<String,String> paramsMap, List<Integer> ruleList, List<ConfirmTable> confirmTableList){
//        this.paramsMap=paramsMap;
//        this.ruleList=ruleList;
//        this.confirmTableList=confirmTableList;
//    }
//
//    public byte[] build() throws Exception{
//        if(paramsMap!=null && ruleList!=null && confirmTableList!=null){
//            xwpfDocument = new XWPFDocument();
//            createTitle();
//            createNum();
//            // 创建1行3列的表格
//            XWPFTable table = xwpfDocument.createTable(1,3);
//            createHeader(table);
//            createFirst(table);
//            // 动态展示规则
//            for (int i = 0; i < ruleList.size(); i++) {
//                Integer integer = ruleList.get(i);
//                switch (integer){
//                    case 1:
//                        create1(table);
//                        break;
//                    case 2:
//                        create2(table);
//                        break;
//                    case 3:
//                        create3(table);
//                        break;
//                    case 4:
//                        create4(table);
//                        break;
//                    default:
//                        break;
//                }
//            }
//            createFooter(table);
//
//            createConfirm();
//            createConfirmTable();
//            createLastLine();
//            xwpfDocument.write(new FileOutputStream("output.docx"));
//            xwpfDocument.close();
//            return null;
//        }else{
//            throw new RuntimeException("参数异常");
//        }
//    }
//
//    /**
//     *
//     */
//    private void createConfirmTable() {
//        if(CollectionUtils.isNotEmpty(confirmTableList)) {
//            createBreak(1);
//            XWPFTable table = xwpfDocument.createTable(1, 7);
//            {
//                // 表头
//                XWPFTableRow row = table.getRow(0);
//                List<String> titleList = Arrays.asList("序号", "车牌号", "卡种", "办卡时间", "产品类型", "启用时间", "终止时间");
//                List<Integer> widthList = Arrays.asList(554, 1856, 1843, 2268, 2409, 2268, 2539);
//                for (int i = 0; i < titleList.size(); i++) {
//                    XWPFTableCell cell0 = row.getCell(i);
//                    CTTcPr tcpr0 = cell0.getCTTc().addNewTcPr();
//                    CTTblWidth cellw0 = tcpr0.addNewTcW();
//                    cellw0.setType(STTblWidth.DXA);
//                    cellw0.setW(BigInteger.valueOf(widthList.get(i)));
//                    XWPFParagraph xwpfParagraph0 = cell0.getParagraphArray(0);
//                    XWPFRun run0 = xwpfParagraph0.createRun();
//                    run0.setFontFamily("宋体");
//                    run0.setFontSize(9);
//                    xwpfParagraph0.setAlignment(ParagraphAlignment.CENTER);
//                    cell0.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
//                    run0.setText(titleList.get(i));
//                }
//            }
//            {
//                // 动态表格,合并单元格
//                int currentIndex0 = 1;
//                int currentIndex1 = 1;
//                for (int i = 0; i < confirmTableList.size(); i++) {
//                    ConfirmTable confirmTable = confirmTableList.get(i);
//                    List<CardInfo> cardInfoList = confirmTable.getCardInfoList();
//                    int productAllSize = 0;
//                    for (int j = 0; j < cardInfoList.size(); j++) {
//                        CardInfo cardInfo = cardInfoList.get(j);
//                        List<ProductInfo> productInfoList = cardInfo.getProductInfoList();
//                        int productSize = productInfoList.size();
//                        productAllSize+=productSize;
//
//                        for (int i1 = 0; i1 < productInfoList.size(); i1++) {
//                            ProductInfo productInfo = productInfoList.get(i1);
//                            List<Integer> widthList = Arrays.asList(554, 1856, 1843, 2268, 2409, 2268, 2539);
//                            List<String> textList = Arrays.asList((i+1)+"",
//                                    confirmTable.getVehicleColor()+confirmTable.getVehiclePlate(),
//                                    cardInfo.getCardType(),
//                                    cardInfo.getOpenCardTime(),
//                                    productInfo.getProductType(),
//                                    productInfo.getStartTime(), productInfo.getEndTime());
//                            XWPFTableRow row = table.createRow();
//                            for (int k = 0; k < widthList.size(); k++) {
//                                XWPFTableCell cell0 = row.getCell(k);
//                                CTTcPr tcpr0 = cell0.getCTTc().addNewTcPr();
//                                CTTblWidth cellw0 = tcpr0.addNewTcW();
//                                cellw0.setType(STTblWidth.DXA);
//                                cellw0.setW(BigInteger.valueOf(widthList.get(k)));
//                                XWPFParagraph xwpfParagraph0 = cell0.getParagraphArray(0);
//                                XWPFRun run0 = xwpfParagraph0.createRun();
//                                run0.setFontFamily("宋体");
//                                run0.setFontSize(9);
//                                xwpfParagraph0.setAlignment(ParagraphAlignment.CENTER);
//                                cell0.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
//                                run0.setText(textList.get(k));
//                            }
//                        }
//                        mergeCellsVertically(table,1,currentIndex1,currentIndex1+productSize-1);
//                        mergeCellsVertically(table,2,currentIndex1,currentIndex1+productSize-1);
//                        mergeCellsVertically(table,3,currentIndex1,currentIndex1+productSize-1);
//                        currentIndex1+=productSize;
//                    }
//                    mergeCellsVertically(table,0,currentIndex0,currentIndex0+productAllSize-1);
//                    currentIndex0+=productAllSize;
//                }
//            }
//        }
//    }
//
//    private void createLastLine() {
//        {
//            createNewParagraph("宋体",9,false,null,null,"xxx有限公司                                                                      （签章）",800);
//            createBreak(2);
//            createNewParagraph("宋体",9,false,null,null,"日期：${date}                                                                              日期：${date}",900);
//        }
//    }
//
//    /**
//     * 回车
//     * @param time
//     */
//    private void createBreak(int time){
//        XWPFParagraph paragraph = xwpfDocument.createParagraph();
//        XWPFRun run = paragraph.createRun();
//        for (int j = 0; j < time; j++) {
//            run.addBreak(BreakClear.ALL);
//        }
//    }
//
//    private void createConfirm() {
//        createNewPage();
//        {
//            createBreak(3);
//        }
//        {
//            createNewParagraph("宋体",12,false,null,null,"附表：",400);
//            createNewParagraph("宋体",12,true,ParagraphAlignment.CENTER,null,"xxx服务确认表",null);
//        }
//    }
//
//    /**
//     * 新页
//     */
//    private void createNewPage(){
////        // 首先获取 document 的 Section 信息
////        CTBody body = xwpfDocument.getDocument().getBody();
////        // 放心, 这个 body 肯定不为空, 否则这个文档就有问题了
////        XWPFParagraph paragraph = xwpfDocument.createParagraph();
////        // 新创建的段落肯定没有 PPr, 所以需要新创建一个
////        CTPPr ctpPr = paragraph.getCTP().addNewPPr();
////        // 这一句其实就是设置下一页的分页符了
////        CTSectPr sectPr = ctpPr.addNewSectPr();
////        // TODO 这里没弄好,创建的新页面,样式与上面的不一样
////        // https://blog.csdn.net/zhaokai0130/article/details/102732159
//        XWPFParagraph paragraph = xwpfDocument.createParagraph();
//        paragraph.setPageBreak(true);
//    }
//
//    /**
//     * @param fontFamily    字体
//     * @param fontSize  字体大小
//     * @param bold  是否加粗
//     * @param alignment 对齐方式
//     * @param underline 下划线
//     * @param text  文本
//     * @param indent 缩进
//     */
//    private void createNewParagraph(String fontFamily, Integer fontSize, Boolean bold, ParagraphAlignment alignment,UnderlinePatterns underline, String text,Integer indent){
//        XWPFParagraph xwpfParagraph0 = xwpfDocument.createParagraph();
//        if(alignment!=null) {
//            xwpfParagraph0.setAlignment(alignment);
//        }
//        if(indent!=null) {
//            xwpfParagraph0.setFirstLineIndent(indent);
//        }
//        XWPFRun run0 = xwpfParagraph0.createRun();
//        run0.setFontFamily(fontFamily);
//        run0.setFontSize(fontSize);
//        run0.setBold(bold);
//        if(underline!=null){
//            run0.setUnderline(underline);
//        }
//
//        run0.setText(text);
//    }
//
//    private void createHeader(XWPFTable table){
//        // TODO table创建后,第一行第一列是不用使用add的方式创建,通过get方式获取即可
//        XWPFTableRow row0 = table.getRow(0);
//        XWPFTableCell cell0 = row0.getCell(0);
//        CTTcPr tcpr0 = cell0.getCTTc().addNewTcPr();
//        // 设置单元格border
//        CTTcBorders ctTcBorders = tcpr0.addNewTcBorders();
//
//        CTBorder ctBorder = ctTcBorders.addNewTop();
//        ctBorder.setVal(STBorder.Enum.forString("single"));
//        ctBorder.setSz(new BigInteger("10"));
//        ctBorder.setColor("000000");
//
//        ctBorder = ctTcBorders.addNewLeft();
//        ctBorder.setVal(STBorder.Enum.forString("single"));
//        ctBorder.setSz(new BigInteger("10"));
//        ctBorder.setColor("000000");
//
//        ctBorder = ctTcBorders.addNewRight();
//        ctBorder.setVal(STBorder.Enum.forString("single"));
//        ctBorder.setSz(new BigInteger("10"));
//        ctBorder.setColor("000000");
//
//        // TODO 这是单元格宽度,这里要设置宽度,不然合并单元格后,宽度有问题，上面宽度小，下面没有合并单元格的宽度长
//        CTTblWidth cellw0 = tcpr0.addNewTcW();
//        cellw0.setType(STTblWidth.DXA);
//        cellw0.setW(new BigInteger("13737"));
//
//        XWPFParagraph xwpfParagraph0 = cell0.getParagraphArray(0);
//        XWPFRun run0 = xwpfParagraph0.createRun();
//        run0.setFontFamily("宋体");
//        run0.setFontSize(9);
//        xwpfParagraph0.setAlignment(ParagraphAlignment.LEFT);
//        run0.setText(StringUtils.replace("致:【${userName}】（下称“用户”或“您”）","${userName}",paramsMap.getOrDefault("userName","*******************")));
//
//        XWPFParagraph xwpfParagraph1 = cell0.addParagraph();
//        XWPFRun run1 = xwpfParagraph1.createRun();
//        run1.setFontFamily("宋体");
//        run1.setFontSize(9);
//        xwpfParagraph0.setAlignment(ParagraphAlignment.LEFT);
//        run1.setText(StringUtils.replace("\t根据您与xxx有限公司（以下简称“xxx”）签订的编号为【${num2}】《xxx服务协议》（以下简称“xxx协议”），相关标准确认如下：","${num2}",paramsMap.getOrDefault("num2","xxx")));
//        mergeCellsHorizontal(table,0,0,2);
//        table1RowIndex++;
//    }
//
//    private void createFooter(XWPFTable table){
//        {
//            createOneRow(table,"宋体",9,true,ParagraphAlignment.BOTH,null,"二、xxxxxxxxxxxxxxxxxx");
//        }
//        {
//            List<LineInfo> lineInfoList=new ArrayList<>();
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,null,"\t",false,false));
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,UnderlinePatterns.SINGLE,"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx。",false,false));
//            createOneRowMutileLine(table,lineInfoList);
//        }
//        {
//            createOneRow(table,"宋体",9,true,ParagraphAlignment.BOTH,null,"三、xxxxxxxxxxxxxxxxxx");
//        }
//        {
//            List<LineInfo> lineInfoList=new ArrayList<>();
//            lineInfoList.add(new LineInfo("宋体",9,false,ParagraphAlignment.LEFT,null,"\t1.",false,false));
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,UnderlinePatterns.SINGLE,"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,false,ParagraphAlignment.LEFT,null,"\t2.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",false,false));
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,UnderlinePatterns.SINGLE,"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,false,ParagraphAlignment.LEFT,null,"\t3.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",false,false));
//            createOneRowMutileLine(table,lineInfoList);
//        }
//        {
//            createOneRow(table,"宋体",9,true,ParagraphAlignment.BOTH,null,"四、xxxxxxxxxxxxxxxxxx");
//        }
//        {
//            List<LineInfo> lineInfoList=new ArrayList<>();
//            lineInfoList.add(new LineInfo("宋体",9,false,ParagraphAlignment.LEFT,null,"\t1.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,false,ParagraphAlignment.LEFT,null,"\t2.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,false,ParagraphAlignment.LEFT,null,"\t3.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,false,ParagraphAlignment.LEFT,null,"\t4.",false,false));
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,UnderlinePatterns.SINGLE,"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,false,ParagraphAlignment.LEFT,null,"\t5.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",false,false));
//            createOneRowMutileLine(table,lineInfoList);
//        }
//        {
//            createOneRow(table,"宋体",9,true,ParagraphAlignment.BOTH,null,"五、xxxxxxxxxxxxxxxxxx");
//        }
//        {
//            List<LineInfo> lineInfoList=new ArrayList<>();
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,null,"\txxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,null,"\t1．xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,null,"\t2．xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,null,"\t3．xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,null,"\t4．xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",true,false));
//
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,null,"",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,null,"",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,null,"",true,false));
//
//            lineInfoList.add(new LineInfo("宋体",9,false,ParagraphAlignment.RIGHT,null,"xxxxxxxxxxxxxxxxxxxxxxxxx",true,true));
//            lineInfoList.add(new LineInfo("宋体",9,false,ParagraphAlignment.RIGHT,null,"日期：${date}\t\t\t",true,false));
//            createOneRowMutileLine(table,lineInfoList);
//        }
//        {
//            List<LineInfo> lineInfoList=new ArrayList<>();
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,null,"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",true,false));
//
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,null,"",true,false));
//            lineInfoList.add(new LineInfo("宋体",9,true,ParagraphAlignment.LEFT,null,"",true,false));
//
//            lineInfoList.add(new LineInfo("宋体",9,false,ParagraphAlignment.RIGHT,null,"（签章）\t\t\t",true,true));
//            lineInfoList.add(new LineInfo("宋体",9,false,ParagraphAlignment.RIGHT,null,"日期：${date}\t\t\t",true,false));
//            createOneRowMutileLine(table,lineInfoList);
//        }
//    }
//
//    /**
//     * 创建一行，并且合并三列
//     * @param table 表格对象
//     * @param fontFamily    字体
//     * @param fontSize  字体大小
//     * @param bold  是否加粗
//     * @param alignment 对齐方式
//     * @param underline 下划线
//     * @param text  文本
//     */
//    private void createOneRow(XWPFTable table, String fontFamily, Integer fontSize, Boolean bold, ParagraphAlignment alignment,UnderlinePatterns underline, String text){
//        XWPFTableRow row0 = table.createRow();
//        XWPFTableCell cell0 = row0.getCell(0);
//        CTTcPr tcpr0 = cell0.getCTTc().addNewTcPr();
//        CTTblWidth cellw0 = tcpr0.addNewTcW();
//        cellw0.setType(STTblWidth.DXA);
//        cellw0.setW(new BigInteger("13737"));
//
//        XWPFParagraph xwpfParagraph0 = cell0.getParagraphArray(0);
//        XWPFRun run0 = xwpfParagraph0.createRun();
//        run0.setFontFamily(fontFamily);
//        run0.setFontSize(fontSize);
//        run0.setBold(bold);
//        if(underline!=null){
//            run0.setUnderline(underline);
//        }
//        xwpfParagraph0.setAlignment(alignment);
//        run0.setText(text);
//
//        mergeCellsHorizontal(table, table1RowIndex, 0, 2);
//        table1RowIndex++;
//    }
//
//    /**
//     * 单元格中内容需要换行处理
//     * @param table
//     * @param lineInfoList
//     */
//    private void createOneRowMutileLine(XWPFTable table, List<LineInfo> lineInfoList){
//        XWPFTableRow row0 = table.createRow();
//        XWPFTableCell cell0 = row0.getCell(0);
//        CTTcPr tcpr0 = cell0.getCTTc().addNewTcPr();
//        CTTblWidth cellw0 = tcpr0.addNewTcW();
//        cellw0.setType(STTblWidth.DXA);
//        cellw0.setW(new BigInteger("13737"));
//        Stack<XWPFParagraph> paragraphStack=new Stack<>();
//        for (int i = 0; i < lineInfoList.size() ; i++) {
//            LineInfo lineInfo = lineInfoList.get(i);
//            if(!lineInfo.getCreateNewParagraph() && paragraphStack.empty()) {
//                paragraphStack.push(cell0.getParagraphArray(0));
//            }else if(lineInfo.getCreateNewParagraph()){
//                paragraphStack.push(cell0.addParagraph());
//            }
//            XWPFParagraph xwpfParagraph0 = paragraphStack.peek();
//            XWPFRun run0 = xwpfParagraph0.createRun();
//            run0.setFontFamily(lineInfo.getFontFamily());
//            run0.setFontSize(lineInfo.getFontSize());
//            run0.setBold(lineInfo.getBold());
//            if(lineInfo.getUnderline()!=null){
//                run0.setUnderline(lineInfo.getUnderline());
//            }
//            if(lineInfo.getAlignment()!=null) {
//                xwpfParagraph0.setAlignment(lineInfo.getAlignment());
//            }
//            run0.setText(lineInfo.getText());
//            if(lineInfo.getBreakFlag()){
//                run0.addBreak(BreakClear.ALL);
//            }
//        }
//
//        mergeCellsHorizontal(table, table1RowIndex, 0, 2);
//        table1RowIndex++;
//    }
//
//    private void createFirst(XWPFTable table){
//        createOneRow(table,"宋体",9,true,ParagraphAlignment.BOTH,null,"一、xxxxxxxxxxxxxxxxxxxxxxx");
//    }
//
//    private void create1(XWPFTable table){
//        createRow(false,table,"规则1","描述1","规则1描述1");
//        createRow(false,table,"规则1","描述2","规则1描述2");
//        createRow(false,table,"规则1","描述3","规则1描述3");
//        createRow(false,table,"规则1","描述4","规则1描述4");
//        createRow(false,table,"规则1","描述5","规则1描述5");
//        createRow(false,table,"规则1","描述6","规则1描述6");
//
//        // TODO 合并单元格要注意
//        mergeCellsVertically(table,0,table1RowIndex,table1RowIndex+(6-1));
//        table1RowIndex+=6;
//    }
//    private void create2(XWPFTable table){
//        createRow(false,table,"规则2","描述1","规则2描述1");
//        createRow(false,table,"规则2","描述2","规则2描述2");
//        createRow(false,table,"规则2","描述3","规则2描述3");
//        createRow(false,table,"规则2","描述4","规则2描述4");
//        createRow(false,table,"规则2","描述5","规则2描述5");
//        createRow(false,table,"规则2","描述6","规则2描述6");
//
//        mergeCellsVertically(table,0,table1RowIndex,table1RowIndex+(6-1));
//        table1RowIndex+=6;
//    }
//    private void create3(XWPFTable table){
//        createRow(false,table,"规则3","描述1","规则3描述1");
//        createRow(false,table,"规则3","描述2","规则3描述2");
//        createRow(false,table,"规则3","描述3","规则3描述3");
//        createRow(false,table,"规则3","描述4","规则3描述4");
//        createRow(false,table,"规则3","描述5","规则3描述5");
//        createRow(false,table,"规则3","描述6","规则3描述6");
//
//        mergeCellsVertically(table,0,table1RowIndex,table1RowIndex+(6-1));
//        table1RowIndex+=6;
//    }
//    private void create4(XWPFTable table){
//        createRow(false,table,"规则4","描述1","规则4描述1");
//        createRow(false,table,"规则4","描述2","规则4描述2");
//        createRow(false,table,"规则4","描述3","规则4描述3");
//        createRow(false,table,"规则4","描述4","规则4描述4");
//        createRow(false,table,"规则4","描述5","规则4描述5");
//        createRow(false,table,"规则4","描述6","规则4描述6\n" +
//                "1.规则4描述6\n" +
//                "2.规则4描述6\n" +
//                "3.规则4描述6\n" +
//                "规则4描述6");
//
//        mergeCellsVertically(table,0,table1RowIndex,table1RowIndex+(6-1));
//        table1RowIndex+=6;
//    }
//
//    /**
//     *
//     * @param firstCreate 是否是首次创建,因为第一次不需要调用createRow,调用getRow即可
//     * @param table 表格对象
//     * @param cell0Value    值1
//     * @param cell1Value    值2
//     * @param cell2Value    值3
//     */
//    private static void createRow(Boolean firstCreate,XWPFTable table,String cell0Value,String cell1Value,String cell2Value) {
//        XWPFTableRow row0 = null;
//        if(firstCreate){
//            row0 = table.getRow(0);
//        }else{
//            row0 = table.createRow();
//        }
//
//        XWPFTableCell cell0 = row0.getCell(0);
//        CTTcPr tcpr0 = cell0.getCTTc().addNewTcPr();
//        CTTblWidth cellw0 = tcpr0.addNewTcW();
//        cellw0.setType(STTblWidth.DXA);
//        cellw0.setW(BigInteger.valueOf(1147));
//        XWPFParagraph xwpfParagraph0 = cell0.getParagraphArray(0);
//        XWPFRun run0 = xwpfParagraph0.createRun();
//        run0.setFontFamily("宋体");
//        run0.setFontSize(9);
//        xwpfParagraph0.setAlignment(ParagraphAlignment.CENTER);
//        cell0.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
//        run0.setText(cell0Value);
//
//
//        XWPFTableCell cell1 = row0.getCell(1);
//        CTTcPr tcpr1 = cell1.getCTTc().addNewTcPr();
//        CTTblWidth cellw1 = tcpr1.addNewTcW();
//        cellw1.setType(STTblWidth.DXA);
//        cellw1.setW(BigInteger.valueOf(2409));
//        XWPFParagraph xwpfParagraph1 = cell1.getParagraphArray(0);
//        XWPFRun run1 = xwpfParagraph1.createRun();
//        run1.setFontFamily("宋体");
//        run1.setFontSize(9);
//        xwpfParagraph1.setAlignment(ParagraphAlignment.CENTER);
//        cell1.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
//        run1.setText(cell1Value);
//
//
//        XWPFTableCell cell2 = row0.getCell(2);
//        XWPFParagraph xwpfParagraph2 = cell2.getParagraphArray(0);
//        String[] split = cell2Value.split("\n");
//        if(split.length==1){
//            XWPFRun run2 = xwpfParagraph2.createRun();
//            run2.setFontFamily("宋体");
//            run2.setFontSize(9);
//            run2.setText(cell2Value);
//        }else {
//            for (int i = 0; i < split.length; i++) {
//                XWPFRun run2 = xwpfParagraph2.insertNewRun(i);
//                run2.setFontFamily("宋体");
//                run2.setFontSize(9);
//                run2.setText(split[i]);
//                if(i!=split.length-1) {
//                    // 换行处理
//                    run2.addBreak(BreakClear.ALL);
//                }
//            }
//        }
//    }
//
//    private void createNum() {
//        XWPFParagraph paragraph = xwpfDocument.createParagraph();
//        XWPFRun run = paragraph.createRun();
//        run.setFontSize(9);
//        run.setFontFamily("宋体");
//        paragraph.setAlignment(ParagraphAlignment.RIGHT);
//        run.setText(StringUtils.replace("编号：${num1}","${num1}",paramsMap.getOrDefault("num1","xxxxxxxxxxxxxxxx-1")));
//    }
//
//    private void createTitle() {
//        XWPFParagraph paragraph = xwpfDocument.createParagraph();
//        XWPFRun run = paragraph.createRun();
//        run.setFontSize(20);
//        run.setFontFamily("等线");
//        paragraph.setAlignment(ParagraphAlignment.CENTER);
//        run.setText("xxxxxxxxxxxxxxx");
//    }
//
//    /**
//     * @Description: 跨列合并
//     * table要合并单元格的表格
//     * row要合并哪一行的单元格
//     * fromCell开始合并的单元格
//     * toCell合并到哪一个单元格
//     */
//    private void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
//        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
//            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
//            if ( cellIndex == fromCell ) {
//                // The first merged cell is set with RESTART merge value
//                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
//            } else {
//                // Cells which join (merge) the first one, are set with CONTINUE
//                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
//            }
//        }
//    }
//
//    /**
//     * @Description: 跨行合并
//     * table要合并单元格的表格
//     * col要合并哪一列的单元格
//     * fromRow从哪一行开始合并单元格
//     * toRow合并到哪一个行
//     */
//    private void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
//        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
//            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
//            if ( rowIndex == fromRow ) {
//                // The first merged cell is set with RESTART merge value
//                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
//            } else {
//                // Cells which join (merge) the first one, are set with CONTINUE
//                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
//            }
//        }
//    }
//
//    @Data
//    @AllArgsConstructor
//    class LineInfo{
//        String fontFamily;
//        Integer fontSize;
//        Boolean bold;
//        ParagraphAlignment alignment;
//        UnderlinePatterns underline;
//        String text;
//        Boolean breakFlag;
//        Boolean createNewParagraph;
//    }
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    static class ConfirmTable{
//        String vehicleColor;
//        String vehiclePlate;
//        List<CardInfo> cardInfoList;
//    }
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    static class CardInfo{
//        String cardType;
//        String openCardTime;
//        List<ProductInfo> productInfoList;
//    }
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    static class ProductInfo {
//        String productType;
//        String startTime;
//        String endTime;
//    }
//}