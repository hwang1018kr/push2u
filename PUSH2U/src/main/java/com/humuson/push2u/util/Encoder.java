package com.humuson.push2u.util;

import java.security.MessageDigest;

public class Encoder {

	public Encoder() {
	}

	// 문자열을 전달받아 암호화하여 변환된 문자열을 반환하는 메소드
	// => MessageDigest 클래스 이용
	public static String encoding(String str, String hashType) {
		
		String encodeString = "";
		
		try {
			// MessageDigest 클래스를 이용하여 객체 생성
			// => 원하는 암호화 알고리즘을 선택
			// => 암호화 알고리즘 : MD5, SHA-1, SHA-256, SHA-512등
			MessageDigest md = MessageDigest.getInstance(hashType);

			// MessageDigest 객체에 암호화 하고자 하는 원본데이타를 Byte 배열로 변환 후 업데이트
			md.update(str.getBytes());

			// 원본데이타를 암호화하여 byte 배열로 반환
			byte[] encodeData = md.digest();

			// 암호화된 데이타를 String 객체에 저장
			for (int i = 0; i < encodeData.length; i++) {
				encodeString += Integer.toHexString(encodeData[i] & 0xFF);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return encodeString;
	}

}
