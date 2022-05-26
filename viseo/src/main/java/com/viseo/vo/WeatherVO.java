package com.viseo.vo;

/**
 * 
 * @author	전다빈
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력	]
 * 				2022.05.26	-	담당자 : 전다빈
 * 								내	용 : 코드 보내기
 *
 */
public class WeatherVO {
	private int TMP, SKY, PTY;

	public int getTMP() {
		return TMP;
	}
	public void setTMP(int tMP) {
		TMP = tMP;
	}
	public int getSKY() {
		return SKY;
	}
	public void setSKY(int sKY) {
		SKY = sKY;
	}
	public int getPTY() {
		return PTY;
	}
	public void setPTY(int pTY) {
		PTY = pTY;
	}
}
