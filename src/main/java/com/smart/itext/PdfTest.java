package com.smart.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class PdfTest {
    public static final void main(String[] args) throws IOException, DocumentException {
        // step 1
        Document document = new Document();

        // step 2
        String filename = "C:\\Users\\wang\\Desktop\\pdftest.pdf";


        PdfWriter.getInstance(document, new FileOutputStream(filename));

        // step 3
        document.open();

        // step 4
        document.add(new Paragraph("Hello World!"));

        Image image = Image.getInstance(new URL("http://pic23.nipic.com/20120920/9410911_233355695159_2.jpg"));
        image.setAbsolutePosition(450,50);
        image.scaleAbsolute(100, 100);

        document.add(image);

        // step 5
        document.close();
    }
}
