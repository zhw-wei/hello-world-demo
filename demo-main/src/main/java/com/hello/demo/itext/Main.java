package com.hello.demo.itext;

import com.hello.demo.util.Common;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, DocumentException {
        File file = Common.filePathPdf();

        CreatePdf createPdf = new CreatePdf(file.getPath(), new PdfObjectOne(),
                Arrays.asList(new PdfObjectTwo(), new PdfObjectTwo(), new PdfObjectTwo()),
                Arrays.asList(new PdfObjectThree(), new PdfObjectThree(), new PdfObjectThree()));
        createPdf.createPdf();
    }
}
