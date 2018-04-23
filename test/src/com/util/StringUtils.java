package com.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import com.sinosoft.sysframework.exceptionlog.UserException;

/**
 * �ַ���������
 */
public class StringUtils {
	public static final double MAX_SET_VALUE = 1000000.0D;// ���ֵ
	public static final double PRE_SET_VALUE = 0.0D;

	/**
	 * �����ܡ�����˫�������͵��� <br>
	 * ������<br>
	 * 1�����Ϊnull��trim�󳤶�Ϊ0������0.0d�� <br>
	 * 2��ʹ��Double.parseDouble()�Ľ����<br>
	 * 	������NaNʱ������0.0d�� <br>
	 * 	������-0.0dʱ������0.0d��˵����-0.0d��С��0.0d�ġ��� <br>
	 * 	�޷�����ʱ��Ҳ���Ƿ����֣�����0.0d<br>
	 * 	�������ʱ�򣬱������1000000���׳��쳣��<br>
	 * ���汾��2007-08-16 ������
	 * @param value�������ַ���
	 * @return ˫������������
	 * @throws Exception
	 */
	public static double parseDouble(String value) throws Exception {
		double result = PRE_SET_VALUE;
		try {
			if (value != null && value.length() > 0) {
				result = Double.parseDouble(value);
				if (result == -0.0D) {
					result = PRE_SET_VALUE;
				} else if (Double.isNaN(result)) {// �����NaN
					result = PRE_SET_VALUE;
				} else if (result > MAX_SET_VALUE) {// ��ֵ�����׳��쳣
					throw new UserException(-98, 9201, "StringUtils", "�ӿ����ݴ�����ֵ����");
				}
			}
		} catch (NumberFormatException e) {// �����������
			result = PRE_SET_VALUE;
		} catch (UserException e1) {
			throw e1;
		}
		return result;
	}

	/**
	 * ȥ��ǰ��ո�
	 * 
	 * @param value
	 *            ���봮
	 * @return ����String
	 */
	public static String trim(String value) {
		String result = value;
		if (value == null) {
			result = "";
		} else {
			result = value.trim();
		}
		return result;
	}

	/**
	 * Trim ,���ݸ�ʽ��
	 * 
	 * @param value
	 *            byte[]
	 * @return ���� byte[]
	 */
	public static byte[] trim(byte[] value) {
		byte[] result = value;
		return result;
	}

	/**
	 * ��Nullת��Ϊ"0"
	 * 
	 * @param value
	 *            ���봮
	 * @return ����String
	 */
	public static String convertNullToZero(String value) {
		String result = value;
		if (value == null || value.trim().equals("")) {
			result = "0";
		}
		return result;
	}

	/**
	 * ��Nullת��Ϊ""
	 * 
	 * @param source
	 *            ���봮
	 * @return ����String
	 */
	public static String convertNullToZeroString(String source) {
		if (source == null) {
			source = "";
		}
		if (source.equalsIgnoreCase("null")) {
			source = "";
		}
		return source;
	}

	/**
	 * ���ַ����ĵ�һ���ַ�Сд
	 * 
	 * @param iString
	 *            �����ַ���
	 * @return �����ַ���
	 */
	public static String lowerCaseFirstChar(String iString) {
		String newString;
		newString = iString.substring(0, 1).toLowerCase()
				+ iString.substring(1);
		return newString;
	}

	/**
	 * ���ַ����ĵ�һ���ַ���д
	 * 
	 * @param iString
	 *            �����ַ���
	 * @return �����ַ���
	 */
	public static String upperCaseFirstChar(String iString) {
		String newString;
		newString = iString.substring(0, 1).toUpperCase()
				+ iString.substring(1);
		return newString;
	}

	/**
	 * �õ����ļ���
	 * 
	 * @param fileName
	 *            �ļ���
	 * @return ���ļ���
	 */
	public static String getShortFileName(String fileName) {
		String shortFileName = "";
		int pos = fileName.lastIndexOf('\\');
		if (pos == -1) {
			pos = fileName.lastIndexOf('/');
		}
		if (pos > -1) {
			shortFileName = fileName.substring(pos + 1);
		} else {
			shortFileName = fileName;
		}

		return shortFileName;
	}

	/**
	 * ���ַ�������ָ���ķָ��ַ����в��,���ز�ֺ���ַ�������
	 * 
	 * @param originalString
	 *            ����ֵ��ַ���
	 * @param delimiterString
	 *            �ָ��ַ���
	 * @return �ַ�������
	 */
	public static String[] split(String originalString, String delimiterString) {
		int index = 0;
		String[] returnArray = null; // ����ֵ�ַ�������
		int length = 0; // ����Ĵ�С

		// nullֵУ��
		if (originalString == null || delimiterString == null) {
			return null;
		}

		// �մ�У��
		if (originalString.equals("") || delimiterString.equals("")
				|| originalString.length() < delimiterString.length()) {
			return new String[] { originalString };
		}

		// �����ַ����ж��ٸ��ָ���
		String strTemp = originalString;
		while (strTemp != null && !strTemp.equals("")) {
			index = strTemp.indexOf(delimiterString);
			if (index == -1) {
				break;
			}
			length++;
			strTemp = strTemp.substring(index + delimiterString.length());
		}
		returnArray = new String[++length];

		// �����ַ�������
		for (int i = 0; i < length - 1; i++) {
			index = originalString.indexOf(delimiterString);
			returnArray[i] = originalString.substring(0, index);
			originalString = originalString.substring(index
					+ delimiterString.length());
		}
		returnArray[length - 1] = originalString;

		return returnArray;
	}

	/**
	 * �õ����ĳ���
	 * 
	 * @param inputStream
	 *            inputStream
	 * @return ���ĳ���
	 * @throws Exception
	 */
	public static int getStreamLength(InputStream inputStream) throws Exception {
		int length = 0;
		length = inputStream.available();
		return length;
	}

	public static String getStringFromInputStream(InputStream is)
			throws Exception {
		if (is == null || is.available() < 1) {
			return "";
		}
		// char[] buff = new char[4096];
		// int nch;
		// StringBuffer buffer = new StringBuffer();
		// BufferedReader in = new BufferedReader(new InputStreamReader(is));
		// while((nch = in.read(buff,0,buff.length)) != -1){
		// buffer.append(new String(buff,0,nch));
		// }
		//
		// String tempInfo = "";
		// String s;
		// while((s = in.readLine()) != null){
		// tempInfo += s + System.getProperty("line.separator");
		// }
		// in.close();
		// return buffer.toString();

		byte[] buff = new byte[8192];
		byte[] result = new byte[is.available()];
		int nch;
		BufferedInputStream in = new BufferedInputStream(is);
		int pos = 0;
		while ((nch = in.read(buff, 0, buff.length)) != -1) {
			System.arraycopy(buff, 0, result, pos, nch);
			pos += nch;
		}
		in.close();
		return new String(result);
	}

	/**
	 * �����еõ��ַ�����
	 * 
	 * @param is
	 *            InputStream
	 * @return�ַ�����
	 * @throws Exception
	 */
	public static byte[] getByteArrayFromInputStream(InputStream is)
			throws Exception {
		if (is == null || is.available() < 1) {
			return new byte[0];
		}
		byte[] buff = new byte[8192];
		byte[] result = new byte[is.available()];
		int nch;
		BufferedInputStream in = new BufferedInputStream(is);
		int pos = 0;
		while ((nch = in.read(buff, 0, buff.length)) != -1) {
			System.arraycopy(buff, 0, result, pos, nch);
			pos += nch;
		}
		in.close();
		return result;
	}

	/**
	 * ����һ������ַ���
	 * 
	 * @param value
	 *            value
	 * @param length
	 *            length
	 * @return ����ַ���
	 */
	public static String newString(String value, int length) {
		StringBuffer buffer = new StringBuffer();
		if (value == null) {
			return null;
		}
		for (int i = 0; i < length; i++) {
			buffer.append(value);
		}

		return buffer.toString();
	}

	/**
	 * ����
	 * 
	 * @param source
	 *            source Collection
	 * @param sampleCount
	 *            sampleCount
	 * @return result Collection
	 */
	public static Collection sample(Object[] source, int sampleCount) {
		Collection result = new ArrayList();
		if (source == null) {
			return null;
		}
		int count = source.length;
		int[] keys = sample(count, sampleCount);
		for (int i = 0; i < keys.length; i++) {
			result.add(source[i]);
		}
		return result;
	}

	/**
	 * ȡ���������
	 * 
	 * @param maxNo
	 *            maxNo
	 * @param sampleCount
	 *            sampleCount
	 * @return int[]
	 */
	public static int[] sample(int maxNo, int sampleCount) {
		Hashtable hash = new Hashtable();

		if (sampleCount > maxNo) {
			sampleCount = maxNo;
		}

		int[] keys = new int[sampleCount];
		int sampleIndex = 0;
		int index = 0;

		for (int i = 0; i < sampleCount; i++) {
			while (true) {
				sampleIndex = (int) Math.round(Math.random() * (maxNo - 1));
				if (!hash.containsKey("" + sampleIndex)) {
					hash.put("" + sampleIndex, "" + sampleIndex);
					break;
				}
			}
			keys[index++] = sampleIndex;
		}
		Arrays.sort(keys);
		return keys;
	}

	/**
	 * �ַ��� ת����HTML��ʽ
	 * 
	 * @param strInValue
	 *            �����ַ���
	 * @return String �����ַ���
	 */
	public static String toHTMLFormat(String strInValue) {
		String strOutValue = "";
		char c;
		for (int i = 0; i < strInValue.length(); i++) {
			c = strInValue.charAt(i);
			switch (c) {
			case '<':
				strOutValue += "&lt;";
				break;
			case '>':
				strOutValue += "&gt;";
				break;
			case '\n':
				strOutValue += "<br>";
				break;
			case '\r':
				break;
			case ' ':
				strOutValue += "&nbsp;";
				break;
			default:
				strOutValue += c;
				break;
			}
		}
		return strOutValue;
	}

	/**
	 * ��ָ�����ַ�������������󳤶Ƚ��зָ���طָ����ַ�������
	 * 
	 * @param strMain
	 *            ����ֵĴ�
	 * @param intMaxLength
	 *            ÿһ�н�����ĳ������ֵ
	 * @return Object �ָ��ַ�������ַ�������
	 */
	public static Object split(String strMain, int intMaxLength) {
		// �������
		Vector vector = new Vector(); // ��Žغ���ַ���
		String strText = ""; // ��ʱ����ַ���
		byte[] arrByte = null; // ����ֵ��ַ������ɵ�Byte����
		int intStartIndex = 0; // �α���ʼλ��
		int intEndIndex = 0; // �α���ֹλ��
		int index = 0;
		int count = 0;
		String[] arrReturn = null; // ����

		// ����ֵ��������<=1���ա����ַ�����
		if (intMaxLength <= 1) {
			System.err.println("error: intMaxLength <= 1");
			return null;
		}
		if (strMain == null) {
			return new String[0]; // ������
		}
		if (strMain.trim().equals("")) {
			return new String[] { "" }; // ���ַ���
		}

		// ��������
		arrByte = strMain.getBytes();
		intEndIndex = 0; // �������ֵ

		while (true) {
			// ���������α�λ��
			intStartIndex = intEndIndex;
			intEndIndex = intStartIndex + intMaxLength;

			// ��ʼλ���Ѿ��������鳤��
			if (intStartIndex >= arrByte.length) {
				break;
			}

			// ��ֹλ���Ѿ��������鳤��
			if (intEndIndex > arrByte.length) {
				intEndIndex = arrByte.length;
				strText = new String(arrByte, intStartIndex, intEndIndex
						- intStartIndex);
				vector.add(strText);
				break;
			}

			// ���ĩβ�İ����������
			count = 0;
			for (index = intStartIndex; index < intEndIndex; index++) {
				if (arrByte[index] < 0) {
					count++;
				}
			}
			// ���ְ������
			if (count % 2 != 0) {
				intEndIndex--;

				// �����ִ�
			}
			strText = new String(arrByte, intStartIndex, intEndIndex
					- intStartIndex);
			vector.add(strText);
		} // end while

		// ת���ַ�������
		arrReturn = new String[vector.size()];
		for (index = 0; index < vector.size(); index++) {
			arrReturn[index] = (String) vector.get(index);
		}

		// ����
		return arrReturn;
	}

	/**
	 * Main
	 * 
	 * @param args
	 *            args
	 */
	public static void main(String[] args) {

	}

	public static String right(String s, int length) {
		// ����
		if (s == null) {
			return s;
		}

		if (length <= 0) {
			return "";
		}

		// ���ȹ�С
		int count = s.length();

		if (count <= length) {
			return s;
		}

		return s.substring(s.length() - length);
	}
	
	/**
	 *  �ж�һ���ַ����Ƿ�Ϊ��
	 * @param str
	 * @return
	 * @author wmz
	 * @version 2008-12-18
	 */
	public static boolean isEmptyString(String str) {
		return (null == str || str.trim().length() == 0);
	}
}