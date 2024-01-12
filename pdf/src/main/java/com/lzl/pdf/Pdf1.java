package com.lzl.pdf;


import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * git@gitee.com:devilyuan/PDF.git
 */
public class Pdf1 {
    private final static Logger LOGGER = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static void main(String[] args) throws Exception {
        String str = "src/main/resources";
        String originalPdfUrlStr = "pdf/template/template.pdf";
        String sealImageUrlStr = "images/github.png";
//        String nowPdfUrlStr = "pdf/generate/generateV1-2-keyword.pdf";
        String nowPdfUrlStr = "pdf/generate/generateV1-1-position.pdf";
        String systemRootPath = System.getProperty("user.dir");
        String originalPdfUrl = systemRootPath + File.separator + str + File.separator + originalPdfUrlStr;
        originalPdfUrl = originalPdfUrl.replace("/", File.separator);
//        String originalPdfUrl = PdfV1.class.getClassLoader().getResource("PDF/template/template.pdf").getPath();
        System.out.println(originalPdfUrl);
        //盖章的图片
        String sealImageUrl = systemRootPath + File.separator + str + File.separator + sealImageUrlStr;
        sealImageUrl = sealImageUrl.replace("/", File.separator);
        System.out.println(sealImageUrl);
        //现在的PDF文件
        String nowPdfUrl = systemRootPath + File.separator + str + File.separator + nowPdfUrlStr;
        nowPdfUrl = nowPdfUrl.replace("/", File.separator);
        System.out.println(nowPdfUrl);
        signImageByPosition(originalPdfUrl, sealImageUrl, nowPdfUrl, 195f, 195f, 1);
//        signImageByKeyWord(originalPdfUrl, sealImageUrl, nowPdfUrl,"甲方（签字、盖章）");
        System.out.println("执行完成");
        LOGGER.info("执行完成");

    }

    /**
     * 定位签章
     * @param originalPdfUrl 原pdf
     * @param sealImageUrl   签章图片
     * @param nowPdfUrl      签章后的pdf
     * @param x              x轴坐标
     * @param y              y轴坐标
     * @param pageIndex      盖章页面下标
     */
    private static void signImageByPosition(String originalPdfUrl, String sealImageUrl, String nowPdfUrl, float x, float y, int pageIndex) {
        PdfReader pdfReader = null;
        PdfStamper stamper = null;
        try {
            //原PDF文件内容
            pdfReader = new PdfReader(originalPdfUrl, "PDF".getBytes());
            //在原PDF文件的基础上添加额外的内容
            stamper = new PdfStamper(pdfReader, new FileOutputStream(nowPdfUrl));
            //获取图片的实例
            Image img = Image.getInstance(sealImageUrl);
            //图片缩放百分比
            img.scalePercent(50);
            //获取原PDF文件的总页数
            int pageSize = pdfReader.getNumberOfPages();
            if (pageIndex > pageSize) {
                return;
            }
            //设置印章位置，在某一页的xy坐标，x,y都是以左下角为圆点
            img.setAbsolutePosition(x, y);
            for (int i = 1; i <= pageSize; i++) {
                if (i == pageIndex) {
                    //PdfContentByte是一个对象，包含用户定位的页面文本和图形内容。它知道如何应用正确的字体编码。
                    // 获取一个PdfContentByte以覆盖原始文档的页面。文字被覆盖
                    PdfContentByte under = stamper.getOverContent(i);
                    //添加电子印章
                    under.addImage(img);
                }
            }
            LOGGER.info("signImage方法执行完成");
        } catch (IOException e) {
            LOGGER.error("IO异常" + e.getMessage());
        } catch (DocumentException e) {
            System.out.println("PdfStamper处理异常" + e.getMessage());
        } finally {
            if (stamper != null) {
                // 关闭
                try {
                    stamper.close();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pdfReader != null) {
                //关闭
                pdfReader.close();
            }
        }
    }

    /**
     * 关键字签章
     * @param originalPdfUrl 原pdf文件
     * @param sealImageUrl 签章图片
     * @param nowPdfUrl 签章后的pdf文件
     * @param key 关键字
     */
    public static void signImageByKeyWord(String originalPdfUrl, String sealImageUrl, String nowPdfUrl,String key) {
        List<String> keyWords = new ArrayList<String>();
        keyWords.add(key);//支持多关键字，默认选择第一个找到的关键字
        PdfReader pdfReader1 = null;
        PdfStamper pdfStamper1 = null;
        try {
            pdfReader1 = new PdfReader(originalPdfUrl, "PDF".getBytes());
            pdfStamper1 = new PdfStamper(pdfReader1, new FileOutputStream(nowPdfUrl));
            List<List<float[]>> arrayLists = findKeywords(keyWords, pdfReader1);//查找关键字所在坐标
            //一个坐标也没找到，就返回
            if (arrayLists.isEmpty()) {
                return;
            }
            if (!arrayLists.get(0).isEmpty()) {
                for (int i = 0; i < arrayLists.get(0).size(); i++) {
                    PdfContentByte overContent = pdfStamper1.getOverContent((int) arrayLists.get(0).get(i)[2]);
                    /*String imgPath = "/resource/lodop/sign.png";
                    float sealWidth = 150f;*/
                    /*float sealHeight = 95f;*/
                    //获取图片的实例
                    Image img = Image.getInstance(sealImageUrl);
                    //图片缩放百分比
//                    img.scalePercent(40);
                    img.scaleAbsolute(arrayLists.get(0).get(i)[0], arrayLists.get(0).get(i)[1]*arrayLists.get(0).get(i)[0]/img.getPlainWidth());

                    //设置绝对位置
                    img.setAbsolutePosition(arrayLists.get(0).get(i)[0],
                            (arrayLists.get(0).get(i)[1] +img.getScaledHeight())/ 2);
                    overContent.addImage(img);//将图片加入pdf的内容中
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //此处一定要关闭流，否则可能会出现乱码
            if (pdfStamper1 != null) {
                try {
                    pdfStamper1.close();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            pdfReader1.close();
        }
    }

    /**
     * 根据关键字返回对应的坐标
     *
     * @param keyWords
     * @param pdfReader
     * @return
     */
    private static List<List<float[]>> findKeywords(final List<String> keyWords, PdfReader pdfReader) {
        if (keyWords == null || keyWords.size() == 0) {
            return null;
        }
        int pageNum = pdfReader.getNumberOfPages();
        final List<List<float[]>> arrayLists = new ArrayList<List<float[]>>(keyWords.size());
        for (int k = 0; k < keyWords.size(); k++) {
            List<float[]> positions = new ArrayList<float[]>();
            arrayLists.add(positions);
        }
        PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
        try {
            for (int i = 1; i <= pageNum; i++) {
                final int finalI = i;
                //使用指定的侦听器处理指定页码中的内容
                pdfReaderContentParser.processContent(i, new RenderListener() {
                    private StringBuilder pdfsb = new StringBuilder();
                    private float yy = -1f;

                    @Override
                    public void renderText(TextRenderInfo textRenderInfo) {
                        //返回要呈现的文字
                        String text = textRenderInfo.getText();
                        //获取文字的线段的边界矩形
                        Rectangle2D.Float boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
                        if (yy == -1f) {
                            yy = boundingRectange.y;
                        }
                        if (yy != boundingRectange.y) {
                            yy = boundingRectange.y;
                            pdfsb.setLength(0);
                        }
                        pdfsb.append(text);
                        if (pdfsb.length() > 0) {
                            for (int j = 0; j < keyWords.size(); j++) {
                                //对获取字符串进行去除分隔符
                                List<String> key_words = parseList
                                        (keyWords.get(j), ",");
                                //假如配置了多个关键字，找到一个就跑
                                for (final String key_word : key_words) {
                                    /*if (arrayLists.get(j) != null) {
                                        break;
                                    }*/
                                    if (pdfsb.length() > 0 && pdfsb.toString().contains(key_word)) {
                                        float[] resu = new float[3];
//                                        resu[0] = boundingRectange.x +boundingRectange.width * (key_word.length() - 1);
                                        resu[0] = boundingRectange.x;
                                        resu[1] = boundingRectange.y;
                                        resu[2] = finalI;
                                        arrayLists.get(j).add(resu);
                                        pdfsb.setLength(0);
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void renderImage(ImageRenderInfo arg0) {
                        //renderImage
                    }

                    @Override
                    public void endTextBlock() {
                        //endTextBlock
                    }

                    @Override
                    public void beginTextBlock() {
                        //beginTextBlock
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayLists;
    }

    /**
     * 对字符串按照指定的分隔符进行分割
     * @param source  需要分割字符串
     * @param regex  分隔符
     * @return
     */
    public static List<String> parseList(String source, String regex) {
        if (source == null || "".equals(source)) {
            return null;
        }
        List<String> strList = new ArrayList<String>();
        if (regex == null || "".equals(regex)) {
            strList.add(source);
        } else {
            String[] strArr = source.split(regex);
            for (String str : strArr) {
                if (str != null || !"".equals(str)) {
                    strList.add(str);
                }
            }
        }
        return strList;
    }

    private static Image getImgByInputstream(InputStream is) {
        if (is == null) {
            return null;
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Image img = null;
        try {
            readInputStream(is, output);
            try {
                img = Image.getInstance(output.toByteArray());
            } catch (BadElementException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static void readInputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[2048];
        int n = 0;
        while (-1 != (n = inputStream.read(buffer))) {
            outputStream.write(buffer, 0, n);
        }
        inputStream.close();
    }

}
