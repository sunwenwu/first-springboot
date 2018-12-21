package com.example.firstspringboot.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Tool {

	private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	@SuppressWarnings("unused")
	private static final char DIGITS_LOWER[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	private static String bytesToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		int t;
		for (int i = 0; i < 16; i++) {
			t = bytes[i];
			if (t < 0)
				t += 256;
			sb.append(hexDigits[(t >>> 4)]);
			sb.append(hexDigits[(t % 16)]);
		}
		return sb.toString();
	}

	public static String toHexString(byte[] byteArray) {
		if (null == byteArray || 0 == byteArray.length) {
			return null;
		}

		final StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			if ((byteArray[i] & 0xff) < 0x10) {
				// 0~F前面补零
				hexString.append("0");
			}
			hexString.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return hexString.toString();// .toLowerCase();
	}

	@SuppressWarnings("unused")
	private static char[] processBytes2Hex(byte[] bytes, char[] digits) {
		// bytes.length << 1肯定是32，左移1位是因为一个字节是8位二进制，一个十六进制用4位二进制表示
		// 当然可以写成l = 32，因为MD5生成的字节数组必定是长度为16的字节数组
		int l = bytes.length << 1;
		char[] rstChars = new char[l];
		int j = 0;
		for (int i = 0; i < bytes.length; i++) {
			// 得到一个字节中的高四位的值
			rstChars[j++] = digits[(0xf0 & bytes[i]) >>> 4];
			// 得到一个字节中低四位的值
			rstChars[j++] = digits[0x0f & bytes[i]];
		}
		return rstChars;
	}

	public static String md5To16(String input) {
		return code(input, 16);
	}

	public static String md5To32(String input) {
		return code(input, 32);
	}

	@SuppressWarnings("unused")
	private static byte[] code1(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] aa = md.digest(toByteArray(input));
		return aa;
	}

	private static String code(String input, int bit) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] aa = md.digest(toByteArray(input));
			return toHexString(aa);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String md5_3(String b) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(System.getProperty("MD5.algorithm", "MD5"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (md != null) {
			byte[] a = md.digest(b.getBytes());
			a = md.digest(a);
			a = md.digest(a);
			return bytesToHex(a);
		}
		return null;
	}

	public static byte[] toByteArray(String hexString) {

		if (null == hexString || hexString.length() == 0) {
			return null;
		}
		if (hexString.length() == 1) {
			hexString = "" + hexString.hashCode();
		}
		final byte[] byteArray = new byte[hexString.length() / 2];
		int k = 0;
		for (int i = 0; i < byteArray.length; i++) {
			// 因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
			byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
			byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
			byteArray[i] = (byte) (high << 4 | low);
			k += 2;
		}
		return byteArray;
	}

	public static byte[] getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		return byteArray;
	}

}
