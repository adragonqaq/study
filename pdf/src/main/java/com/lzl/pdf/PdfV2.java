package com.lzl.pdf;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.*;
import java.util.*;


/**
 * git@gitee.com:devilyuan/PDF.git
 */
public class PdfV2 {
    public static void main(String[] args) {
        Map<String, String> data = new HashMap<String, String>();
        //key为pdf模板的form表单的名字，value为需要填充的值
        //甲方姓名
        data.put("personAName", "gitee");
        //甲方身份证号码
        data.put("personAId", "123456789");
        //乙方姓名
        data.put("personBName", "github");
        //乙方身份证号码
        data.put("personBId", "987654321");
        //人民币大写
        data.put("capital", "伍佰万元整");
        //人民币小写
        data.put("lowerCase", "500,0000");
        //借款用途
        data.put("loanUse", "Study");
        //借款交付时间-年
        data.put("year", "2019");
        //借款交付时间-月
        data.put("month", "05");
        //借款交付时间-日
        data.put("date", "14");
        //交付方式
        data.put("paymentMethod", "现金");
        //贷款利息
        data.put("loanInterest", "日利率万4");
        //借款期限
        data.put("loanTerm", "一年");
        //还款日期和方式
        data.put("repaymentDateAndMethod", "2020年05月14日 支付宝方式");
        //违约责任
        data.put("defaultResponsibility", "write demo");
        //法院
        data.put("court", "哈哈");
        //生效时间
        data.put("effectiveTime", "2019年05月14日");
        //签订日期
        data.put("signingDate", "2020年05月14日");
        String str = "src/main/resources";
        String originalPdfUrlStr = "pdf/template/template.pdf";
        String nowPdfUrlStr = "pdf/generate/generateV2-1-fillPdf.pdf";
        String systemRootPath = System.getProperty("user.dir");
        String originalPdfUrl = systemRootPath + File.separator + str + File.separator + originalPdfUrlStr;
        originalPdfUrl = originalPdfUrl.replace("/", File.separator);
        //现在的PDF文件
        String nowPdfUrl = systemRootPath + File.separator + str + File.separator + nowPdfUrlStr;
        nowPdfUrl = nowPdfUrl.replace("/", File.separator);
        generatePDF(originalPdfUrl,nowPdfUrl, data);
    }
    /**
     * 填充表单域模版PDF，生成新的PDF文档
     * @param templatePdfPath 模板pdf路径
     * @param generatePdfPath 生成pdf路径
     * @param data            数据
     */
    public static String generatePDF(String templatePdfPath, String generatePdfPath, Map<String, String> data) {
        OutputStream fos = null;
        ByteArrayOutputStream bos = null;
        try {
            PdfReader reader = new PdfReader(templatePdfPath);
            bos = new ByteArrayOutputStream();
            /* 将要生成的目标PDF文件名称 */
            PdfStamper ps = new PdfStamper(reader, bos);
            /* 使用中文字体 */
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

            ArrayList<BaseFont> fontList = new ArrayList<>();
            fontList.add(bf);
            /* 取出报表模板中的所有字段 */
            AcroFields fields = ps.getAcroFields();
            fields.setSubstitutionFonts(fontList);
            fillData(fields, data);
            /* 必须要调用这个，否则文档不会生成的  如果为false那么生成的PDF文件还能编辑，一定要设为true*/
            ps.setFormFlattening(true);
            ps.close();
            fos = new FileOutputStream(generatePdfPath);
            fos.write(bos.toByteArray());
            fos.flush();
            return generatePdfPath;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     * 填充表单域
     * @param fields 表单域集合
     * @param data 数据
     * @throws IOException
     * @throws DocumentException
     */
    private static void fillData(AcroFields fields, Map<String, String> data) throws IOException, DocumentException {
        List<String> keys = new ArrayList<>();
        Map<String, AcroFields.Item> formFields = fields.getFields();
        for (String key : data.keySet()) {
            if (formFields.containsKey(key)) {
                String value = data.get(key);
                fields.setField(key, value); // 为字段赋值,注意字段名称是区分大小写的
                keys.add(key);
            }
        }
        Iterator<String> itemsKey = formFields.keySet().iterator();
        while (itemsKey.hasNext()) {
            String itemKey = itemsKey.next();
            if (!keys.contains(itemKey)) {
                fields.setField(itemKey, " ");
            }
        }
    }
}
