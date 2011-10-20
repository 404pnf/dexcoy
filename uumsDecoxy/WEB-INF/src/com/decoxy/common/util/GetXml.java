package com.decoxy.common.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public final class GetXml {

	public static String getUserXml(String username) {
		File file = new File(GetXml.class.getResource("/").getPath()+"users.xml");
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    String result = null;
	    DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}    
	    Document doc = null;
		try {
			doc = builder.parse(file);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    
	    NodeList nl = doc.getElementsByTagName("VALUE");    
	    for (int i = 0; i < nl.getLength(); i++) {
	    	if(doc.getElementsByTagName("USERNAME").item(i).getFirstChild().getNodeValue().equals(username)){
	    		result = doc.getElementsByTagName("PASSWORD").item(i).getFirstChild().getNodeValue();
	    	}
	    }
		return result;
	}
}