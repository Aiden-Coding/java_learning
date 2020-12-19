
package com.itmayiedu;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Test003 {

	public static void main(String[] args) throws DocumentException {
		// 创建SAXReader
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File("F:\\student.xml"));
		Element rootElement = document.getRootElement();
		getNodes(rootElement);
	}

	public static void getNodes(Element element) {
		System.out.println("当前节点名称:" + element.getName());
		// 获取当前节点所有属性
		List<Attribute> attributes = element.attributes();
		for (Attribute attribute : attributes) {
			System.out.println(attribute.getName() + "--" + attribute.getText());
		}
		String textTrim = element.getTextTrim();
		if(!textTrim.equals("")){
			System.out.println("当前节点内容:"+textTrim);
		}
		// 获取当前子节点
		Iterator<Element> elementIterator = element.elementIterator();
		while (elementIterator.hasNext()) {
			Element next = elementIterator.next();
			getNodes(next);
		}
	}

}
