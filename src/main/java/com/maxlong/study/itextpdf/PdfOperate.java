package com.maxlong.study.itextpdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/7/22 19:17
 * 类说明:
 */
public class PdfOperate {

    public final static String PAPFILE = "F:\\风云全集.pdf";

    public final static String OUTDIR = "C:\\Users\\huaya\\Desktop\\";


    public static void main(String[] args) throws Exception {

        PdfReader pdfReader = new PdfReader(PAPFILE);
        int pageNum = pdfReader.getNumberOfPages();

        Document document = null;
        PdfCopy copy = null;
        int countNum = 1;
        int fileMark = 1;
        while (countNum <= pageNum) {
            File file = new File(OUTDIR + "风云全集_0" + fileMark + ".pdf");
            document = new Document(pdfReader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(file));
            document.open();

            while ((file.length()/1048576) < 450 && countNum <= pageNum) {

                document.newPage();
                PdfImportedPage page = copy.getImportedPage(pdfReader, countNum);
                copy.addPage(page);
                countNum++;

            }
            document.close();
            fileMark++;

        }

        System.out.println(countNum);
    }

}
