package com.project.side.controller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.JsonObject;

public class apiTest {
	public static JsonObject apiTest2(String answer) {
		
		JsonObject answerJson = new JsonObject();
		try {
			String key = "4D4F9F28A712D9D6A5321DE2315F7FC3";
			String word = answer;
			String url = "https://stdict.korean.go.kr/api/search.do?key=" + key + "&type_search=search&q=" + word;

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(url);

			NodeList nl = doc.getElementsByTagName("item");

			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.println("target_code : " + getValue("target_code", element));
					System.out.println("word : " + getValue("word", element));
					System.out.println("pos : " + getValue("pos", element));
					System.out.println("definition : " + getValue("definition", element));
					System.out.println("link : " + getValue("link", element));
					System.out.println();
					
					answerJson.addProperty("word", getValue("word", element));
					answerJson.addProperty("pos", getValue("pos", element));
					answerJson.addProperty("definition", getValue("definition", element));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("answerList" + answerJson);
		return answerJson;
	}

	public static String getValue(String tag, Element element) {
		NodeList nl = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node value = (Node) nl.item(0);
		return value.getNodeValue();
	}
}