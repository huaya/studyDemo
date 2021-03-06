package com.maxlong.study.itextpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/7/22 18:14
 * 类说明:
 */
@Log4j2
public class PdfGenerate {

    public final static String PAPER_DIIR = "C:\\Users\\huaya\\Desktop\\风云全集";

    public static void main(String[] args) {
        Rectangle rectPageSize = new Rectangle(PageSize.A4);// A4纸张

        File file = new File("C:\\Users\\huaya\\Desktop\\风云全集_1.pdf");
        Document document = new Document();
        PdfWriter writer = null;
        try {

            writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.addCreationDate();
            document.addCreator("huaya");
            document.addTitle("风云全集-马荣成");
            document.addKeywords("风云,漫画,马荣成,步惊云,聂风");
            document.addSubject("风云全集");
            document.open();
            File[] papers = getOrderFiles();

            for (File paper : papers) {
                FileInputStream fis = new FileInputStream(paper);
                byte[] bytes;
                try {
                    bytes = new byte[(int) paper.length()];
                    fis.read(bytes);
                } catch (IOException iOException) {
                    throw iOException;
                } finally {
                    fis.close();
                }

                if (bytes != null && bytes.length > 0) {
                    try {
                        Image image = Image.getInstance(bytes);
                        image.setAbsolutePosition(0, 0);
                        PdfContentByte canvas = writer.getDirectContent();
                        writer.getPageNumber();
                        canvas.addImage(image, rectPageSize.getWidth(), 0, 0, rectPageSize.getHeight(), 0, 0);

                        document.newPage();
                    } catch (IOException e){
                        log.info("加载图片失败，文件名：" + paper.getName());
                        e.printStackTrace();
                    }
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
            if (writer != null) {
                writer.close();
            }

        }
    }

    public static File[] getOrderFiles() throws Exception {
        File file = new File(PAPER_DIIR);
        File[] papers = file.listFiles();
        Arrays.sort(papers, (file1, file2) -> compareFileName(file1, file2));
        return papers;
    }


    public static int compareFileName(File file1, File file2) {
        String fileName1 = file1.getName();
        String fileName2 = file2.getName();

        if (fileName1.substring(0, 1).equals(fileName2.substring(0, 1))) {
            int begin1 = fileName1.indexOf("第") + 1;
            int begin2 = fileName2.indexOf("第") + 1;
            String huiNum1 = "";
            String huiNum2 = "";

            while (Character.isDigit(fileName1.charAt(begin1))) {
                huiNum1 = huiNum1 + fileName1.charAt(begin1);
                begin1++;
            }

            while (Character.isDigit(fileName2.charAt(begin2))) {
                huiNum2 = huiNum2 + fileName2.charAt(begin2);
                begin2++;
            }

            if (Integer.valueOf(huiNum1).compareTo(Integer.valueOf(huiNum2)) > 0) {
                return 1;
            } else if (Integer.valueOf(huiNum1).compareTo(Integer.valueOf(huiNum2)) < 0) {
                return -1;
            } else {
                begin1 = fileName1.indexOf("_") + 1;
                begin2 = fileName2.indexOf("_") + 1;
                huiNum1 = "";
                huiNum2 = "";

                while (Character.isDigit(fileName1.charAt(begin1))) {
                    huiNum1 = huiNum1 + fileName1.charAt(begin1);
                    begin1++;
                }

                while (Character.isDigit(fileName2.charAt(begin2))) {
                    huiNum2 = huiNum2 + fileName2.charAt(begin2);
                    begin2++;
                }
                if (Integer.valueOf(huiNum1).compareTo(Integer.valueOf(huiNum2)) > 0) {
                    return 1;
                } else if (Integer.valueOf(huiNum1).compareTo(Integer.valueOf(huiNum2)) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }

        } else {
            return !fileName1.substring(0, 1).equals("第")? 1 : -1;
        }

    }
}

