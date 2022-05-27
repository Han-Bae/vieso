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
 * 								내	용 : 클래스 제작, 파싱 성공
 * 
 * 				2022.05.26	-	담당자 : 전다빈
 * 								내	용 : api의 가까운 갱신 시간에 맞춰 파라미터 값을 정해주는 함수 제작 
 * 
 * 				2022.05.26	-	담당자 : 전다빈
 * 								내	용 : x, y 매개변수까지 모두 적용해서 계정 주소에 맞는 지역의 기온과 날씨 불러오기 성공
 */

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import com.viseo.vo.*;


public class WeatherUtil {
	private final int TODAY_DATE = 0;
	private final int YESTERDAY_DATE = 1;
	
	public String getXML(MainVO maVO) {
		String xml = "";
		
		maVO = getBaseTime(maVO);
		
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
        try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=TRAM%2FRVxlSFcjL9YuCBCcbd5V%2F3A%2F08WsZ2L7%2FJIl20erQtdCniVDgrx0E8RnzPy5YdJMLGaXHq7TCHfCivjOA%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("XML", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
	        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(maVO.getBaseDate(), "UTF-8")); /*‘21년 6월 28일 발표*/
	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(maVO.getBaseTime(), "UTF-8")); /*06시 발표(정시단위) */
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(maVO.getX() + "", "UTF-8")); /*예보지점의 X 좌표값*/
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(maVO.getY() + "", "UTF-8")); /*예보지점의 Y 좌표값*/
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
	
	public WeatherVO getXMLTag(MainVO maVO) {
		WeatherVO wVO = new WeatherVO();
		
		String xml = getXML(maVO);
		
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
                    if(!getValue("fcstTime", element).equals(maVO.getTime() + "00")) {
                    	// 시간은 한시간 단위로 있어서 컨티뉴로
                    	continue roop;
                    } else if(!getValue("fcstDate", element).equals(maVO.getYear() + maVO.getMonth() + maVO.getDate())) {
                    	continue roop;
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
    
    private MainVO getBaseTime(MainVO maVO) {
    	int time = Integer.parseInt(maVO.getTime());
    	if(2 < time && time <= 5) {
    		maVO.setBaseTime("0200");
    		maVO.setBaseDate(getBaseDate(TODAY_DATE));
    	} else if(5 < time && time <= 8) {
    		maVO.setBaseTime("0500");
    		maVO.setBaseDate(getBaseDate(TODAY_DATE));
    	} else if(8 < time && time <= 11) {
    		maVO.setBaseTime("0800");
    		maVO.setBaseDate(getBaseDate(TODAY_DATE));
    	} else if(11 < time && time <= 14) {
    		maVO.setBaseTime("1100");
    		maVO.setBaseDate(getBaseDate(TODAY_DATE));
    	} else if(14 < time && time <= 17) {
    		maVO.setBaseTime("1400");
    		maVO.setBaseDate(getBaseDate(TODAY_DATE));
    	} else if(17 < time && time <= 20) {
    		maVO.setBaseTime("1700");
    		maVO.setBaseDate(getBaseDate(TODAY_DATE));
    	} else if(20 < time && time <= 23) {
    		maVO.setBaseTime("2000");
    		maVO.setBaseDate(getBaseDate(TODAY_DATE));
    	} else if(23 < time || (0 <= time && time <= 2)) {
    		maVO.setBaseTime("2300");
    		maVO.setBaseDate(getBaseDate(TODAY_DATE));
    	} else {
    		maVO.setBaseTime("2300");

    		maVO.setBaseDate(getBaseDate(YESTERDAY_DATE));
    	}
    	
    	return maVO;
    }
    
    private String getBaseDate(int code) {
    	String date = "";
    	LocalDate today = LocalDate.now();
    	DateTimeFormatter datePat = DateTimeFormatter.ofPattern("yyyyMMdd");
    	switch(code) {
    	case TODAY_DATE:
    		date = datePat.format(today);
    		break;
    	case YESTERDAY_DATE:
    		LocalDate yesterday = today.minusDays(1);
    		date = datePat.format(yesterday);
    		break;
    	}
    	
    	return date;
    }
}