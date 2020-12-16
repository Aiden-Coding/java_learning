package com.aiden.json_demo;

import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class Dom4jDemo {
  public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
    //1.1  建立一个解析器工厂:
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    //1.2  获得一个DocumentBuilder对象，这个对象代表了具体的DOM解析器
    DocumentBuilder db = dbf.newDocumentBuilder();
    //1.3 将xml文件加载到内存中,得到表示整个文档的Document对象
    InputStream inStream = Dom4jDemo.class.getClassLoader().getResourceAsStream("demo.xml");
    org.w3c.dom.Document w3cdoc = db.parse(inStream);
    //2.创建一个DOMReader
    DOMReader domReader = new DOMReader();
    //3.将org.w3c.dom.Document转成org.dom4j.Document
    org.dom4j.Document doc = domReader.read(w3cdoc);
    Element ele = doc.getRootElement();//3.获得根节点
    //4.使用迭代器对子节点进行迭代
    Iterator<Element> it = ele.elementIterator();
    while (it.hasNext()) {
      Element bookEle = it.next();//取出当前的迭代的元素
      System.out.println("id:"+bookEle.attributeValue("id"));
    }
  }
}
