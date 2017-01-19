/**
 * GIT Confidential
 * Licensed Materials - Property of GIT
 * Copyright (c) 1998-2016 Global InfoTech Corp. All Rights Reserved.
 * Global InfoTech, Inc. owns the copyright and other intellectual
 * property rights in this software.
 *
 * The source code for this program is not published.
 * Duplication or use of the Software is not permitted
 * except as expressly provided in a written agreement
 * between your company and Global InfoTech, Inc.
 */

package com.hull.utils.udmp;

import cn.com.git.udmp.common.exception.FrameworkException;
import com.google.common.collect.Lists;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Iterator;
import java.util.List;


/**
 * PDF工具类  
 * @description 用于操作PDF文档
 * @author Spring Cao
 * @date 2016年11月25日 下午4:36:54
 */
public class PDFUtil{
    
    /** 日志 */
    private static final Logger LOG = LoggerFactory.getLogger(PDFUtil.class);
    
    /**
     * 
     * @title 创建PdfStamper对象
     * @description 2参数，提供PdfReader和OutputStream对象，默认版本号为0
     * 
     * @param reader
     * @param os
     * @return
     */
    public static PdfStamper createPdfStamper(PdfReader reader,OutputStream os){
        PdfStamper ps = null;
        try {
            ps = new PdfStamper(reader, os,'\0');
        } catch (DocumentException e) {
            LOG.error("create PdfStamper failure : {}", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("create PdfStamper failure : {}", e.getMessage());
            e.printStackTrace();
        }
        return ps;
    }
    
    /**
     * 
     * @title 创建PdfStamper对象
     * @description 3参数，提供PdfReader、OutputStream对象和版本号，默认append为false
     * 
     * @param reader
     * @param os
     * @param pdfVersion
     * @return
     */
    public static PdfStamper createPdfStamper(PdfReader reader,OutputStream os,char pdfVersion){
        PdfStamper ps = null;
        try {
            ps = new PdfStamper(reader, os, pdfVersion,false);
        } catch (DocumentException e) {
            LOG.error("create PdfStamper failure : {}", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("create PdfStamper failure : {}", e.getMessage());
            e.printStackTrace();
        }
        return ps;
    }
    
    /**
     * 
     * @title 创建PdfStamper对象
     * @description 4参数，提供PdfReader、OutputStream对象、版本号和是否追加
     * 
     * @param reader
     * @param os
     * @param pdfVersion
     * @param append
     * @return
     */
    public static PdfStamper createPdfStamper(PdfReader reader,OutputStream os,char pdfVersion,boolean append){
        PdfStamper ps = null;
        try {
            ps = new PdfStamper(reader, os, pdfVersion, append);
        } catch (DocumentException e) {
            LOG.error("create PdfStamper failure : {}", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("create PdfStamper failure : {}", e.getMessage());
            e.printStackTrace();
        }
        return ps;
    }
    
    /**
     * 
     * @title 创建PdfReader
     * @description 传入pdf文件路径，获得一个PdfReader对象
     * 
     * @param pdfFilePath
     * @return
     */
    public static PdfReader createPdfReader(String pdfFilePath){
        PdfReader reader = null;
        try {
            reader = new PdfReader(pdfFilePath);
        } catch (IOException e) {
            LOG.error("create PdfReader failure : {}",e.getMessage());
            e.printStackTrace();
        }
        return reader;
    }

    /**
     * @description 导出PDF
     * @param content 导出内容
     * @param pdfPath 导出路径
     */
    public static void exportPdf(String content, String pdfPath) {
        Document document = null;
        OutputStream out = null;
        try {
            /* 1.建立com.lowagie.text.Document对象的实例 */
            document = new Document();
            /* 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中 */
            out = new FileOutputStream(pdfPath);
            PdfWriter.getInstance(document, out);
            /* 3.打开文档 */
            document.open();
            /* 4.向文档中添加内容 */
            document.add(new Paragraph(content)); // 会中文乱码
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close(); // 5.关闭文档
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @description 导出PDF
     * 
     * @param para 导出段落
     * @param pdfPath 导出路径
     * @param vertical 是否竖向创建
     */
    public static void exportPdf(Paragraph para, String pdfPath, boolean vertical) {
        Document doc = null;
        OutputStream out = null;
        try {
            /* 1.建立com.lowagie.text.Document对象的实例 */
            doc = buildDoc(vertical);
            /* 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中 */
            out = new FileOutputStream(pdfPath);
            PdfWriter.getInstance(doc, out);
            /* 3.打开文档 */
            doc.open();
            /* 4.向文档中添加内容 */
            doc.add(para);

        } catch (Exception e) {
            e.printStackTrace();
            LOG.debug("生成pdf出错");
        } finally {
            close(doc, out); // 5.关闭
        }
    }

    /**
     * @description 导出PDF
     * 
     * @param doc 文件
     * @param element 内容
     * @param pdfPath 路径
     */
    public static void exportPdf(Document doc, Element element, String pdfPath) {
        OutputStream out = null;
        try {
            /* 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中 */
            out = new FileOutputStream(pdfPath);
            PdfWriter.getInstance(doc, out);
            /* 3.打开文档 */
            doc.open();
            /* 4.向文档中添加内容 */
            doc.add(element);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.debug("生成pdf出错");
        } finally {
            /* 5.关闭 */
            close(doc, out);
        }
    }

    /**
     * @description 获得画笔对象
     * 
     * @param content 内容
     * @param font 字体
     * @return Chunk 画笔对象
     */
    public static Chunk getChunk(String content, Font font) {
        Chunk chunk = new Chunk(content, font);
        return chunk;
    }

    /**
     * @description 获得段落对象
     * 
     * @param content 内容
     * @param font 字体
     * @return Phrase 段落对象
     */
    public static Phrase getPhrase(String content, Font font) {
        Phrase phrase = new Phrase(content, font);
        return phrase;
    }

    /**
     * @description 获得图片对象
     * 
     * @param content 内容
     * @param font 字体
     * @return Paragraph 图片对象
     */
    public static Paragraph getPara(String content, Font font) {
        Paragraph para = new Paragraph(content, font);
        return para;
    }

    /**
     * @description 获得锚点对象
     * 
     * @param content 内容
     * @param font 字体
     * @param url 锚点Url
     * @return Anchor 锚点对象
     */
    public static Anchor getAnchor(String content, Font font, String url) {
        Anchor anchor = new Anchor(content, font);
        anchor.setReference(url);
        return anchor;
    }

    /**
     * @description 获得PDF com.itextpdf.text.List对象
     * 
     * @param list List
     * @param numbered boolean
     * @param symbolIndent float  listsymbol用到的indentation
     * @param font 字体
     * @return com.lowagie.text.List
     */
    public static com.itextpdf.text.List getPdfList(List list, boolean numbered, float symbolIndent, Font font) {
        com.itextpdf.text.List pdfList = new com.itextpdf.text.List(numbered, symbolIndent);
        ListItem li = null;
        if (list != null && !list.isEmpty()) {
            for (Iterator it = list.iterator(); it.hasNext();) {
                li = new ListItem(it.next().toString(), font);
                pdfList.add(li);
            }
        }
        return pdfList;
    }

    /**
     * @description 获得一个注释对象
     * 
     * @param title String类型 标题
     * @param text String类型 内容
     * @param x float 左下角x坐标
     * @param y float 右上角y坐标
     * @return  Annotation 注释对象
     */
    public static Annotation getAnnotation(String title, String text, float x, float y) {
        Annotation anno = new Annotation(title, text, x, 0f, 0f, y);
        return anno;
    }

    /**
     * @description 获得一个注释URL对象
     * 
     * @param llx float 左下x坐标
     * @param lly float 左下y坐标
     * @param urx float 右上x坐标
     * @param ury float 右上y坐标
     * @param url String 外部参考的URL
     * @return Annotation 注释url对象
     */
    public static Annotation getAnnotationUrl(float llx, float lly, float urx, float ury, String url) {
        Annotation anno = new Annotation(llx, lly, urx, ury, url);
        return anno;
    }

    /**
     * @description 获得一个章节
     * 
     * @param content 内容
     * @param font 字体
     * @param numberDepth int类型  章节数 
     * @return Chapter章节
     */
    public static Chapter getChapter(String content, Font font, int numberDepth) {
        Paragraph p = getPara(content, font);
        Chapter chapter = new Chapter(p, numberDepth);
        return chapter;
    }

    /**
     * @description 通过章节获得区域
     * 
     * @param chapter Chapter类型 章节
     * @param content String类型 内容
     * @param font 字体
     * @param numberDepth int类型  章节数
     * @return Section 区域
     */
    public static Section getSectionByChapter(Chapter chapter, String content, Font font, int numberDepth) {
        Paragraph p = getPara(content, font);
        Section s = chapter.addSection(p, numberDepth);
        return s;
    }

    /**
     * @description 直接获得区域
     * 
     * @param section Section 区域
     * @param content String 内容
     * @param font Font 字体
     * @param numberDepth int 章节数
     * @return Section 区域
     */
    public static Section getSectionBySection(Section section, String content, Font font, int numberDepth) {
        Paragraph p = getPara(content, font);
        Section s = section.addSection(p, numberDepth);
        return s;
    }
    
    /**
     * 
     * @title 创建Image对象
     * @description 传入图片文件路径
     * 
     * @param imgPath
     * @return
     */
    public static Image getImage(String imgPath) {
        if(StringUtils.isBlank(imgPath)){
            LOG.debug("Image file path is null value.");
            throw new FrameworkException(new IllegalArgumentException("Image file path is null value."));
        }
        Image img = null;
        try {
            img = Image.getInstance(imgPath); // 取得图片对象
        } catch (Exception e) {
            LOG.debug("Create Image Object failure. {}"+e.getMessage());
            e.printStackTrace();
        }
        return img;
    }

    /**
     * @description 得到图像
     *
     * @param imgPath String  图片路径
     * @param alignMent int 队列
     * @param absoluteX float 绝对位置
     * @param absoluteY float 绝对位置
     * @return Image图片
     */
    public static Image getImage(String imgPath, int alignMent, float absoluteX, float absoluteY) {
        Image img = null;
        try {
            img = getImage(imgPath); // 取得图片对象
            // 设置与文字对齐方式
            img.setAlignment(alignMent); // Image.RIGHT, MIDDLE, LEFT
            // ,TEXTWRAP(环绕）, UNDERLYING(背景）
            if (absoluteX != -1 && absoluteY != -1) {
                // 戓x, y均不等于－1
                img.setAbsolutePosition(absoluteX, absoluteY); // 设置绝对位置
            }
        } catch (Exception e) {
            LOG.debug("创建图片对象错误");
            e.printStackTrace();
        }
        return img;
    }


    /**
     *
     * @title 获得默认的PdfPTable对象及4个默认列
     * @description 获得默认的表格对象，4列
     *
     * @return
     */
    public static PdfPTable createPdfPTable() {
        PdfPTable table = null;
        int fontSize = 8;
        int column = 4;
        float spacing = 20f;
        int WPercentage = 100;
        int align = Element.ALIGN_CENTER;
        BaseColor baseColor = BaseColor.LIGHT_GRAY;

        List<PdfPCell> cells = Lists.newArrayList();

        table = createPdfPTable(column,spacing,WPercentage);

        cells.add(createPdfPCell(new Paragraph("Default_1",getBoldChineseFont(fontSize)),baseColor,align));
        cells.add(createPdfPCell(new Paragraph("Default_2",getBoldChineseFont(fontSize)),baseColor,align));
        cells.add(createPdfPCell(new Paragraph("Default_3",getBoldChineseFont(fontSize)),baseColor,align));
        cells.add(createPdfPCell(new Paragraph("Default_4",getBoldChineseFont(fontSize)),baseColor,align));

        addPdfPCellCollectionIn2PdfPtable(table, cells);

        return table;
    }

    /**
     *
     * @title 创建PdfPTable
     * @description 单参数，提供colSize
     *
     * @param colSize
     * @return
     */
    public static PdfPTable createPdfPTable(int colSize) {
        if(colSize <= 0){
            LOG.error("colSize less than zero.");
            throw new FrameworkException(new IllegalArgumentException("colSize less than zero."));
        }
        return new PdfPTable(colSize);
    }

    /**
     *
     * @title 创建PdfPTable
     * @description 三参数，提供colSize、spacingBefore和WPercentage
     *
     * @param colSize
     * @param spacingBefore
     * @param WPercentage
     * @return
     */
    public static PdfPTable createPdfPTable(int colSize,float spacingBefore,int WPercentage) {
        if(colSize <= 0 || spacingBefore <= 0 || WPercentage <= 0){
            LOG.error("colSize or spacingBefore or WPercentage less than zero.");
            throw new FrameworkException(new IllegalArgumentException("colSize less than zero."));
        }

        PdfPTable table = createPdfPTable(colSize);
        table.setSpacingBefore(spacingBefore);
        table.setWidthPercentage(WPercentage);

        return table;
    }

    /**
     *
     * @title 创建一个PdfPCell对象
     * @description
     *
     * @return
     */
    public static PdfPCell createPdfPCell(){
        return new PdfPCell();
    }

    /**
     *
     * @title 创建一个PdfPCell对象
     * @description 两参数，提供Paragraph对象的name和字体
     *
     * @param name
     * @param font
     * @return
     */
    public static PdfPCell createPdfPCell(String name,Font font){
        return new PdfPCell(new Paragraph(name,font));
    }

    /**
     *
     * @title 创建一个PdfPCell对象
     * @description 一个参数，提供一个Paragraph对象
     *
     * @param paragraph
     * @return
     */
    public static PdfPCell createPdfPCell(Paragraph paragraph,BaseColor baseColor,int align){
        PdfPCell cell = createPdfPCell(paragraph);
        cell.setBackgroundColor(baseColor == null?BaseColor.LIGHT_GRAY:baseColor);
        cell.setHorizontalAlignment(align <= -1 ? Element.ALIGN_CENTER:align);
        return cell;
    }

    /**
     *
     * @title 创建一个PdfPCell对象
     * @description 一个参数，提供一个Paragraph对象
     *
     * @param paragraph
     * @return
     */
    public static PdfPCell createPdfPCell(Paragraph paragraph){
        return new PdfPCell(paragraph);
    }

    /**
     *
     * @title 创建一个PdfPCell对象
     * @description 两参数，提供PdfPTable对象和PdfPCell对象
     *
     * @param table
     * @param cell
     * @return
     */
    public static PdfPTable addPdfPCellIn2PdfPtable(PdfPTable table,PdfPCell cell){
        if(table == null || cell == null){
            LOG.error("table or cell was null Object.");
            throw new FrameworkException(new IllegalArgumentException("table or cell was null Object."));
        }
        table.addCell(cell);
        return table;
    }

    /**
     *
     * @title 创建一个PdfPCell对象
     * @description 两参数，提供PdfPTable对象和PdfPCell集合对象
     *
     * @param table
     * @param cells
     * @return
     */
    public static PdfPTable addPdfPCellCollectionIn2PdfPtable(PdfPTable table,List<PdfPCell> cells){
        if(table == null || cells == null || cells.size() <= 0){
            LOG.error("table or cells was null Object.");
            throw new FrameworkException(new IllegalArgumentException("table or cells was null Object."));
        }
        for(PdfPCell cell : cells){
            table.addCell(cell);
        }
        return table;
    }

    /**
     *
     * @title 返回中文基础字体对象
     * @description 无参数，默认"STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDE，上述三个参数
     *
     * @return
     */
    public static BaseFont createChineseBaseFont(){
        return createChineseBaseFont(BaseFont.NOT_EMBEDDED);
    }

    /**
     *
     * @title 返回中文基础字体对象
     * @description 单参数，其他默认采用 "UniGB-UCS2-H", BaseFont.NOT_EMBEDDE，上述两个参数
     *
     * @param fontStyle
     * @return
     */
    public static BaseFont createChineseBaseFont(String fontStyle){
        return createChineseBaseFont(fontStyle, "UniGB-UCS2-H");
    }

    /**
     *
     * @title 返回中文基础字体对象
     * @description 单参数，提供Embedded的值
     *
     * @param embedded
     * @return
     */
    public static BaseFont createChineseBaseFont(boolean embedded){
        return createChineseBaseFont("STSong-Light", "UniGB-UCS2-H",embedded);
    }

    /**
     *
     * @title 返回中文基础字体对象
     * @description 双参数，默认采用 BaseFont.NOT_EMBEDDE，上述一个参数
     *
     * @param fontStyle
     * @param fontName
     * @return
     */
    public static BaseFont createChineseBaseFont(String fontStyle,String fontName){
        return createChineseBaseFont(fontStyle, fontName, BaseFont.NOT_EMBEDDED);
    }

    /**
     *
     * @title 返回中文基础字体对象
     * @description 三参数，提供fontStyle，fontName和embedded
     *
     * @param fontStyle
     * @param fontName
     * @param embedded
     * @return
     */
    public static BaseFont createChineseBaseFont(String fontStyle,String fontName,boolean embedded){
        BaseFont bfChinese = null;

        if(StringUtils.isBlank(fontStyle) || StringUtils.isBlank(fontName)){
            LOG.error("fontStyle or fontName was null value");
            throw new FrameworkException(new IllegalArgumentException("fontStyle or fontName was null value."));
        }
        
        try {
            bfChinese = BaseFont.createFont(fontStyle, fontName, embedded);
        } catch (DocumentException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return bfChinese;
    }
    
    /**
     * 
     * @title 创建自定义中文字体
     * @description 默认构造
     * 
     * @return
     */
    public static Font createChineseFont(){
        return createChineseFont(createChineseBaseFont());        
    }
    
    /**
     * 
     * @title 创建自定义中文字体
     * @description 单参数，需提供 BaseFont 对象
     * 
     * @param baseFont
     * @return
     */
    public static Font createChineseFont(BaseFont baseFont){
        return createChineseFont(baseFont, 8);           
    }
    
    /**
     * 
     * @title 创建自定义中文字体
     * @description 两参数构造，需提供BaseFont对象和fontSize
     * 
     * @param baseFont
     * @param fontSize
     * @return
     */
    public static Font createChineseFont(BaseFont baseFont,int fontSize){
        return createChineseFont(baseFont, fontSize, Font.NORMAL);           
    }
    
    /**
     * 
     * @title 创建自定义中文字体
     * @description 两参数构造，需提供fontSize和fontStyle
     * 
     * @param fontSize
     * @param fontStyle
     * @return
     */
    public static Font createChineseFont(int fontSize,int fontStyle){
        return createChineseFont(createChineseBaseFont(), fontSize, fontStyle);           
    }
    
    /**
     * 
     * @title 创建自定义中文字体
     * @description 三参数，需要传递，第一个参数为基础中文字体对象
     * 
     * @param baseFont
     * @param fontSize
     * @param fontStyle
     * @return
     */
    public static Font createChineseFont(BaseFont baseFont,int fontSize,int fontStyle){
        if(baseFont == null || fontSize <= 0 || fontStyle < Font.UNDEFINED){
            LOG.error("baseFont was null or fontSize less than zero or fontStyle less than Font.UNDEFINED's value.");
            throw new FrameworkException(new IllegalArgumentException("baseFont was null or fontSize less than zero or fontStyle less than Font.UNDEFINED's value."));
        }
        Font fontChinese = new Font(baseFont, fontSize, fontStyle);// 创建字体，设置family，size，style,还可以设置color 
        return fontChinese;        
    }
    
    /**
     * 
     * @title 创建标题中文字体
     * @description 默认构造
     * 
     * @return
     */
    public static Font getTitleChineseFont(){
        return createChineseFont(createChineseBaseFont(), 20, Font.BOLD);
    }
    
    /**
     * 
     * @title 创建标题中文字体
     * @description 单参数，提供BaseFont对象
     * 
     * @param baseFont
     * @return
     */
    public static Font getTitleChineseFont(BaseFont baseFont){
        return createChineseFont(baseFont, 20, Font.BOLD);
    }
    
    /**
     * 
     * @title  创建标题中文字体
     * @description 单参数，提供fontSize
     * 
     * @param fontSize
     * @return
     */
    public static Font getTitleChineseFont(int fontSize){
        return createChineseFont(createChineseBaseFont(), fontSize, Font.BOLD);
    }
    
    /**
     * 
     * @title  创建标题中文字体
     * @description 两参数，提供BaseFont对象和fontSize
     * 
     * @param baseFont
     * @param fontSize
     * @return
     */
    public static Font getTitleChineseFont(BaseFont baseFont,int fontSize){
        return createChineseFont(baseFont, fontSize, Font.BOLD);
    }
    
    /**
     * 
     * @title 创建标题中文字体
     * @description 三参数
     * 
     * @param baseFont
     * @param fontSize
     * @param fontStyle
     * @return
     */
    public static Font getTitleChineseFont(BaseFont baseFont,int fontSize,int fontStyle){
        return createChineseFont(baseFont, fontSize, fontStyle);
    }
    
    /**
     * 
     * @title 创建标题中文字体
     * @description 两参数，提供fontSize和fontStyle
     * 
     * @param fontSize
     * @param fontStyle
     * @return
     */
    public static Font getTitleChineseFont(int fontSize,int fontStyle){
        return createChineseFont(createChineseBaseFont(), fontSize, fontStyle);
    }
    
    /**
     * 
     * @title 创建粗体中文字体
     * @description 默认构造
     * 
     * @return
     */
    public static Font getBoldChineseFont(){
        return getTitleChineseFont(createChineseBaseFont(), 14, Font.BOLD);
    }
    
    /**
     * 
     * @title 创建粗体中文字体
     * @description 单参数，提供BaseFont对象
     * 
     * @param baseFont
     * @return
     */
    public static Font getBoldChineseFont(BaseFont baseFont){
        return createChineseFont(baseFont, 14, Font.BOLD);
    }
    
    /**
     * 
     * @title 创建粗体中文字体
     * @description 单参数,提供fontSize
     * 
     * @param fontSize
     * @return
     */
    public static Font getBoldChineseFont(int fontSize){
        return createChineseFont(createChineseBaseFont(), fontSize, Font.BOLD);
    }
    
    /**
     * 
     * @title 创建粗体中文字体
     * @description 两参数，提供BaseFont对象和fontSize
     * 
     * @param baseFont
     * @param fontSize
     * @return
     */
    public static Font getBoldChineseFont(BaseFont baseFont,int fontSize){
        return createChineseFont(baseFont, fontSize, Font.BOLD);
    }
    
    /**
     * 
     * @title 创建粗体中文字体
     * @description 两参数，提供fontSize和fontSize
     * 
     * @param baseFont
     * @param fontSize
     * @return
     */
    public static Font getBoldChineseFont(int fontSize,int fontStyle){
        return createChineseFont(createChineseBaseFont(), fontSize, fontStyle);
    }
    
    /**
     * 
     * @title 创建粗体中文字体
     * @description 三参数
     * 
     * @param baseFont
     * @param fontSize
     * @param fontStyle
     * @return
     */
    public static Font getBoldChineseFont(BaseFont baseFont,int fontSize,int fontStyle){
        return createChineseFont(baseFont, fontSize, fontStyle);
    }
    
    /**
     * 
     * @title 创建默认的表头
     * @description 
     *    为一个PdfPTable对象创建占一行的表头，4参数，提供PdfPTable对象、colsWidthPercentage数组、colsName数组、align对齐方式，注意：两个数组的length要一致。
     *    例如：int[] colsWidthPercentage = { 9, 4, 8, 10, 8, 11, 9, 7, 9, 10, 4, 10 }; String[] colsName={"","","",.....,""};
     *    align 例如：Element.ALIGN_CENTER
     *  
     * 
     * @param table
     * @param colWithPercentage
     * @param colsName
     * @param align
     * @return
     */
    public static PdfPTable createDefTableHeadWithTable(PdfPTable table,int[] colsWidthPercentage,String[] colsName,int align){
        int numOfCol = 0;
        if(table == null || colsWidthPercentage == null || colsName == null || (colsWidthPercentage.length != colsName.length) || align <= 0){
            LOG.error("table 、colsWidthPercentage 、 colsName was null or colsWidthPercentage.length not equals colsName.length or align less than zero.");
            throw new FrameworkException(new IllegalArgumentException("table 、colsWidthPercentage 、 colsName was null or colsWidthPercentage.length not equals colsName.length or align less than zero."));
        }
        
        numOfCol = table.getNumberOfColumns();
        
        try {
            table.setWidths(colsWidthPercentage);
        } catch (DocumentException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }  
        
        table.setWidthPercentage(100);
        table.getDefaultCell().setHorizontalAlignment(align);  
        
        for(int i = 0;i < numOfCol;i++){
            table.addCell(colsName[i]);
        }
        
        table.setHeaderRows(1);
        return table;
    }


    /**
     * @title 构建一个PDF Document对象
     * @description vertical为true时，返回默认Document构造对象，否则返回基于PageSize.A4.rotate()参数的横向Document构造对象
     * 
     * @param vertical 是否垂直创建
     * @return Document PDF文件
     */
    public static Document buildDoc(boolean vertical) {
        Document doc = null;
        if (vertical) {
            // 竖的，默认
            doc = new Document();
        } else {
            // 横向,A4大小
            doc = new Document(PageSize.A4.rotate());
        }
        return doc;
    }
    
    /**
     * 
     * @title 构建一个PDF Document对象
     * @description 单参数，提供Rectangle对象作为文档的页面尺寸，例如：PageSize.A4
     * 
     * @param pageSize
     * @return
     */
    public static Document buildDoc2A4(Rectangle pageSize){
        Document doc = null;
        if(pageSize != null){
            doc = new Document(pageSize);
        }else{
            doc = buildDoc(true);
        }
        return doc;
    }

    /**
     * 
     * @title  获得一个PdfWriter对象
     * @description 两参数，提供Document对象和OutputStream对象
     * 
     * @param doc 文件
     * @param out 输出流
     * @return PdfWriter 写入流
     */
    public static PdfWriter getPdfWriter(Document doc, OutputStream out) {
        if(doc == null || out == null){
            LOG.error("Document object or OutputStream object was null value.");
            throw new FrameworkException(new IllegalArgumentException("Document object or OutputStream object was null value."));
        }
        
        PdfWriter writer = null;
        
        try {
            writer = PdfWriter.getInstance(doc, out);
        } catch (DocumentException e) {
            LOG.error("Create PdfWriter object failure.");
            e.printStackTrace();
        }
        return writer;
    }
    
    /**
     * 
     * @title 获得一个PdfWriter对象
     * @description 两参数，提供Document对象和文件的路径
     * 
     * @param doc
     * @param fileAbsolutePath
     * @return
     */
    public static PdfWriter getPdfWriter(Document doc, String fileAbsolutePath) {
        PdfWriter writer = null;
        try {
            writer = getPdfWriter(doc, new FileOutputStream(fileAbsolutePath));
        } catch (FileNotFoundException e) {
            LOG.error("Create PdfWriter object failure, maybe file absolute path was wrong.");
            e.printStackTrace();
        }
        return writer;
    }
    
    /**
     * 
     * @title 获得一个PdfWriter对象
     * @description 两参数，提供Document对象和File文件句柄对象
     * 
     * @param doc
     * @param file
     * @return
     */
    public static PdfWriter getPdfWriter(Document doc, File file) {
        PdfWriter writer = null;
        try {
            writer = getPdfWriter(doc, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            LOG.error("Create PdfWriter object failure, maybe file absolute path was wrong.");
            e.printStackTrace();
        }
        return writer;
    }
    
    /**
     * @title PDF加密
     * @description 3参数，提供PdfWriter对象、使用者密码、拥有者密码
     * 
     * @param writer PdfWriter对象
     * @param userPsw String类型 用户密码
     * @param ownerPsw String类型 拥有者密码
     * @param permissions int类型  权限
     * @return PdfWriter
     */
    public static PdfWriter encrypt(PdfWriter writer, String userPwd, String ownerPwd) {
        encrypt(writer,userPwd, ownerPwd, PdfWriter.ALLOW_SCREENREADERS);
        return writer;
    }

    /**
     * @title PDF加密
     * @description 四参数，提供PdfWriter对象、使用者密码、拥有者密码、权限(例如：PdfWriter.ALLOW_SCREENREADERS)
     * 
     * @param writer PdfWriter对象
     * @param userPsw String类型 用户密码
     * @param ownerPsw String类型 拥有者密码
     * @param permissions int类型  权限
     * @return PdfWriter
     */
    public static PdfWriter encrypt(PdfWriter writer, String userPwd, String ownerPwd, int permissions) {
        encrypt(writer,userPwd, ownerPwd, permissions,PdfWriter.STANDARD_ENCRYPTION_128);
        return writer;
    }
    
    /**
     * 
     * @title PDF加密
     * @description 五参数，提供PdfWriter对象、使用者密码、拥有者密码、权限(例如：PdfWriter.ALLOW_SCREENREADERS)、加密位(例如：PdfWriter.STANDARD_ENCRYPTION_128)
     * 
     * @param writer
     * @param userPwd
     * @param ownerPwd
     * @param permissions
     * @param std_bit
     * @return
     */
    public static PdfWriter encrypt(PdfWriter writer, String userPwd, String ownerPwd, int permissions,int std_bit) {
        try {
            writer.setEncryption(userPwd.getBytes(), ownerPwd.getBytes(), permissions,std_bit);
        } catch (DocumentException e) {
            LOG.error("Encrypt pdf content failure.");
            e.printStackTrace();
        }
        return writer;
    }
    
    /**
     * 
     * @title 生成128位条形码
     * @description 2参数，提供PdfWriter，title
     * 
     * @param writer
     * @param title
     * @return
     */
    public static Image createBarcode128Img(PdfWriter writer,String title){
        return createBarcode128Img(writer,title,10,700);
    }
    
    /**
     * 
     * @title 生成128位条形码
     * @description 4参数，提供PdfWriter，title，x坐标，y坐标
     * 
     * @param writer
     * @param title
     * @param x
     * @param y
     * @return
     */
    public static Image createBarcode128Img(PdfWriter writer,String title,int x, int y){
        return createBarcode128Img(writer,title,x,y,50);
    }
    
    /**
     * 
     * @title 生成128位条形码
     * @description 5参数，提供PdfWriter，title，x坐标，y坐标以及条形码的拉伸百分比
     * 
     * @param writer
     * @param title
     * @param x
     * @param y
     * @param scalePercent
     * @return
     */
    public static Image createBarcode128Img(PdfWriter writer,String title,int x, int y,float scalePercent){
        Barcode128 barcode = new Barcode128();
        barcode.setCode(title != null?title.trim():"N/A");
        barcode.setCodeType(Barcode128.CODE128);
        Image code128Image = barcode.createImageWithBarcode(writer.getDirectContent(), null, null);
        code128Image.setAbsolutePosition(x,y);  
        code128Image.scalePercent(scalePercent);
        return code128Image;
    }
    
    /**
     * 
     * @title 生成二维码
     * @description 1参数，提供codeContent
     * 
     * @param codeContent
     * @return
     */
    public static Image createQRCodeImg(String codeContent){
        return createQRCodeImg(codeContent,1,1);
    }
    
    /**
     * 
     * @title 生成二维码
     * @description 3参数，提供codeContent、width、height
     * 
     * @param codeContent
     * @param width
     * @param height
     * @return
     */
    public static Image createQRCodeImg(String codeContent,int width,int height){
        return createQRCodeImg(codeContent,width,height,10,600);
    }
    
    /**
     * 
     * @title 生成二维码
     * @description 5参数，提供codeContent、width、height、x坐标、y坐标
     * 
     * @param codeContent
     * @param width
     * @param height
     * @param x
     * @param y
     * @return
     */
    public static Image createQRCodeImg(String codeContent,int width,int height,int x, int y){
        return createQRCodeImg(codeContent,width,height,x,y,200);
    }
    
    /**
     * 
     * @title 生成二维码
     * @description 6参数，提供codeContent、width、height、x坐标、y坐标以及拉伸百分比
     * 
     * @param codeContent
     * @param width
     * @param height
     * @param x
     * @param y
     * @param scalePercent
     * @return
     */
    public static Image createQRCodeImg(String codeContent,int width,int height,int x, int y,float scalePercent){
        BarcodeQRCode qrcode = new BarcodeQRCode(codeContent != null?codeContent.trim():"N/A", width, height, null);  
        Image qrcodeImage = null;
        try {
            qrcodeImage = qrcode.getImage();
            qrcodeImage.setAbsolutePosition(x,y);  
            qrcodeImage.scalePercent(scalePercent);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }  
        return qrcodeImage;
    }
    
    /**
     * 
     * @title 关闭流对象
     * @description 2参数，提供输出流对象和PdfStamper对象
     * 
     * @param out
     * @param ps
     */
    public static void close(OutputStream out1,OutputStream out2) {
        try {
            if(out1 != null){
                out1.close();
            }
            if(out2 != null){
                out2.close();
            }
        } catch (IOException e) {
            LOG.error("Close OutputStream failure.");
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @title 关闭流对象
     * @description 2参数，提供输出流对象和PdfStamper对象
     * 
     * @param out
     * @param ps
     */
    public static void close(OutputStream out,PdfStamper ps) {
        try {
            if(ps != null){
                ps.close();
            }
            if(out != null){
                out.close();
            }
        } catch (IOException e) {
            LOG.error("Close Document object or PdfStamper failure.");
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @title 
     * @description 3参数，提供Document对象、输出流对象和PdfStamper对象
     * 
     * @param doc
     * @param out
     * @param ps
     */
    public static void close(Document doc, OutputStream out,PdfStamper ps) {
        try {
            if(ps != null){
                ps.close();
            }
            if(doc != null){
                doc.close(); // 关闭文档
            }
            if(out != null){
                out.close();
            }
        } catch (IOException e) {
            LOG.error("Close Document object、OutputStream or PdfStamper failure.");
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @title 关闭流对象
     * @description 2参数，提供Document和OutputStream对象
     * 
     * @param doc
     * @param out
     */
    public static void close(Document doc, OutputStream out) {
        try {
            if(doc != null){
                doc.close(); // 关闭文档
            }
            if(out != null){
                out.close();
            }
        } catch (IOException e) {
            LOG.error("Close Document object or OutputStream failure.");
            e.printStackTrace();
        }
    }
}
