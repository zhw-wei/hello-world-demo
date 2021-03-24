package com.hello.demo.pdf;

import com.itextpdf.html2pdf.HtmlConverter;

import java.io.FileOutputStream;
import java.io.IOException;

public class Html2Pdf {

    public static final String HTML = "<h1>Hello</h1>"
            + "<p style='color:red'>This was created using iText</p>"
            + "<a href='hmkcode.com'>hmkcode.com</a>";

    public static void main(String[] args) throws IOException {

        HtmlConverter.convertToPdf(HTML, new FileOutputStream("string-to-pdf.pdf"));
        System.out.println( "PDF Created!" );
    }
}
