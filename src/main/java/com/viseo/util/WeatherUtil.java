package com.viseo.util;

/**
 * 오픈 API XML 파싱하는 클래스
 * 
 * @author	전다빈
 * @since	2022.05.25
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.25	-	담당자 : 전다빈
 * 								내	용 : 파싱 성공
 */

import java.io.*;
import java.net.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import com.viseo.vo.WeatherVO;


public class WeatherUtil {
	
	public String getXML() {
		String xml = "";
		
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
        try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=TRAM%2FRVxlSFcjL9YuCBCcbd5V%2F3A%2F08WsZ2L7%2FJIl20erQtdCniVDgrx0E8RnzPy5YdJMLGaXHq7TCHfCivjOA%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("XML", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
	        // ++++++++++++++++매개변수 오늘날짜 넣기
	        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20220526", "UTF-8")); /*‘21년 6월 28일 발표*/
	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*06시 발표(정시단위) */
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); /*예보지점의 X 좌표값*/
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        xml = sb.toString();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
        return xml;
    }
	
	// ++++++++++++++++매개변수 오늘날짜, 지금시간 받기 
	public WeatherVO getXMLTag() {
		WeatherVO wVO = new WeatherVO();
		
		// ++++++++++++++++getXML 오늘날짜 매개변수 문자열로 보내고
		String xml = getXML();
		
		InputSource is = new InputSource(new StringReader(xml));
		
		try {
			//Document 클래스로 xml데이터를 취득
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			
			//xPath 팩토리로 객체를 만들기
			XPath xpath = XPathFactory.newInstance().newXPath();
			
			//xPath를 컴파일한 후에 node단위로 데이터를 수집
			NodeList nodeList = (NodeList) xpath.compile("/response/body/items/item").evaluate(doc, XPathConstants.NODESET);
			int nodeListCount = nodeList.getLength();
			
			roop:
			for (int i = 0; i < nodeListCount; i++) {
			    Node node = nodeList.item(i);
			    
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // ++++++++++++++++지금시간으로 변경
                    if(!getValue("fcstTime", element).equals("0900")) {
                    	// 시간은 05시부터 나와서 컨티뉴로
                    	continue roop;
                    // ++++++++++++++++오늘날짜로 변경
                    } else if(!getValue("fcstDate", element).equals("20220526")) {
                    	break roop;
                    } else if(getValue("category", element).equals("TMP")) {
                    	wVO.setTMP(Integer.parseInt(getValue("fcstValue", element)));
                    } else if(getValue("category", element).equals("SKY")) {
                    	wVO.setSKY(Integer.parseInt(getValue("fcstValue", element)));
                    } else if(getValue("category", element).equals("PTY")) {
                    	wVO.setPTY(Integer.parseInt(getValue("fcstValue", element)));
                    }
                }
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return wVO;
	}
	
    private String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }
}