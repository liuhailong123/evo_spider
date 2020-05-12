package com.frameworks.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlUtils {
    /**
     * 字符串转换为DOCUMENT
     * 
     * @param xmlStr
     *            字符串
     * @return doc JDOM的Document
     * @throws Exception
     */
    public static Document string2Doc(String xmlStr) throws Exception {
        java.io.Reader in = new StringReader(xmlStr);
        Document doc = (new SAXBuilder()).build(in);
        return doc;
    }

    /**
     * Document转换为字符串
     * 
     * @param doc
     * @return string
     */
    public static String doc2String(Document doc) {
        Format format = Format.getPrettyFormat();
        format.setEncoding("UTF-8");// 设置xml文件的字符为UTF-8，解决中文问题
        XMLOutputter xmlout = new XMLOutputter(format);
        StringWriter out = new StringWriter();
        try {
            xmlout.output(doc,out); 
            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException("document转化String异常：", e);
        }
    }

}
