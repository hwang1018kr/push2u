package com.humuson.push2u.util;

import java.security.MessageDigest;

public class Encoder {

	public Encoder() {
	}

	// ���ڿ��� ���޹޾� ��ȣȭ�Ͽ� ��ȯ�� ���ڿ��� ��ȯ�ϴ� �޼ҵ�
	// => MessageDigest Ŭ���� �̿�
	public static String encoding(String str, String hashType) {
		
		String encodeString = "";
		
		try {
			// MessageDigest Ŭ������ �̿��Ͽ� ��ü ����
			// => ���ϴ� ��ȣȭ �˰����� ����
			// => ��ȣȭ �˰��� : MD5, SHA-1, SHA-256, SHA-512��
			MessageDigest md = MessageDigest.getInstance(hashType);

			// MessageDigest ��ü�� ��ȣȭ �ϰ��� �ϴ� ��������Ÿ�� Byte �迭�� ��ȯ �� ������Ʈ
			md.update(str.getBytes());

			// ��������Ÿ�� ��ȣȭ�Ͽ� byte �迭�� ��ȯ
			byte[] encodeData = md.digest();

			// ��ȣȭ�� ����Ÿ�� String ��ü�� ����
			for (int i = 0; i < encodeData.length; i++) {
				encodeString += Integer.toHexString(encodeData[i] & 0xFF);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return encodeString;
	}

}
