package com.hello.demo.itext;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreatePdf<A, B, C> {
    private String filePath;
    private A aObj;
    private List<B> bList;
    private List<C> cList;

    public CreatePdf(String filePath, A aObj, List<B> bList, List<C> cList) {
        this.filePath = filePath;
        this.aObj = aObj;
        this.bList = bList;
        this.cList = cList;
    }

    private Font font = null;//正常字体
    private Font fontBold = null;//正常加粗字体
    private Font fontBig = null;//大字体
    private Font fontBigBold = null;//加粗大字体

    public void createPdf() throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(filePath));

        document.open();

        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        font = new Font(bfChinese);//正常字体
        fontBold = new Font(bfChinese, 12, Font.BOLD);//正常加粗字体
        fontBig = new Font(bfChinese, 20);//大字体
        fontBigBold = new Font(bfChinese, 20, Font.BOLD);//加粗大字体

        Paragraph theme = new Paragraph("还款计划表", fontBigBold);
        theme.setAlignment(Element.ALIGN_CENTER);
        document.add(theme);

        Paragraph peopleInfo = new Paragraph("借款人：张三    借款金额：100万元", font);
        peopleInfo.setAlignment(Element.ALIGN_CENTER);
        document.add(peopleInfo);

        this.createTable(document, bList);
        this.createTable(document, cList);

        document.close();
    }

    private void createTable(Document document, List<?> list) throws DocumentException {
        if (Objects.isNull(list)
                || (list = list.stream().filter(Objects::nonNull).collect(Collectors.toList())).isEmpty()) return;

        Class<?> classInfo = list.get(0).getClass();

        List<Field> fieldList = Stream.of(classInfo.getDeclaredFields())
                .filter(var0 -> Objects.nonNull(var0.getAnnotation(PdfTable.class)))
                .collect(Collectors.toList());
        if (fieldList.isEmpty()) return;

        PdfPTable table = new PdfPTable(fieldList.size());
        table.setWidthPercentage(90);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);

        //head
        PdfPCell[] headCell = new PdfPCell[fieldList.size()];
        PdfPRow headRow = new PdfPRow(headCell);
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            PdfTable pdfTable = field.getAnnotation(PdfTable.class);
            headCell[i] = new PdfPCell(new Paragraph(pdfTable.headName(), fontBold));
            headCell[i].setHorizontalAlignment(Element.ALIGN_CENTER);
            headCell[i].setVerticalAlignment(Element.ALIGN_CENTER);
            headCell[i].setFixedHeight(25f);
            headCell[i].setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        table.getRows().add(headRow);

        //content
        for (Object o : list) {
            PdfPCell[] contentCell = new PdfPCell[fieldList.size()];
            PdfPRow contentRow = new PdfPRow(contentCell);
            for (int i = 0; i < fieldList.size(); i++) {
                Field field = fieldList.get(i);
                Object value = this.readValue(field, o);
                String val = Objects.nonNull(value) ? (String) value : "";

                contentCell[i] = new PdfPCell(new Paragraph(val, font));
                contentCell[i].setHorizontalAlignment(Element.ALIGN_CENTER);
                contentCell[i].setVerticalAlignment(Element.ALIGN_CENTER);
                contentCell[i].setFixedHeight(25f);
            }
            table.getRows().add(contentRow);
        }

        document.add(table);
    }

    private Object readValue(Field field, Object obj) {
        Object res = null;
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), obj.getClass());
            Method method = descriptor.getReadMethod();
            res = method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
