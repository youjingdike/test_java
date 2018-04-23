package com.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author xingqian
 * @����������ʽ
 */
public class TestReg {
    /**
     * ��֤������ַ����Ƿ�����ƥ��������ʽ
     * @param regex ������ʽ
     * @param decStr Ҫƥ����ַ���
     * @return ��ƥ�䣬�򷵻�true;���򣬷���false;
     */
    public static boolean validataAll(String regex, String decStr) {
        //���ʽ����
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        //����Matcher����
        Matcher m = p.matcher(decStr);
        //�Ƿ���ȫƥ��
        boolean b = m.matches();//�÷������Խ����������������ģʽƥ��
        return b;
    }
    
    /**
     * ��֤������ַ����Ƿ������ַ���ƥ��������ʽ
     * @param regex ������ʽ
     * @param decStr Ҫƥ����ַ���
     * @return ��ƥ�䣬�򷵻�true;���򣬷���false;
     */
    public static boolean validataSub(String regex, String decStr) {
        //���ʽ����
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        //����Matcher����
        Matcher m = p.matcher(decStr);
        //�Ƿ���ȫƥ��
        boolean b = m.find();//�÷���ɨ�����������Բ������ģʽƥ�����һ�������С�
        return b;
    }
    
    /**
     * �����ַ������Ƿ��з��ϸ���������ʽ�����ַ���������ƥ��ĵ�һ�����ַ���
     * @param regex��������ʽ
     * @param decStr��Ҫƥ����ַ���
     * @return :����ƥ��ĵ�һ���ַ���������ƥ����null
     */
    public static String search(String regex, String decStr) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(decStr);
        System.out.println("ƥ�䵽��������"+m.groupCount());
      //�Ƿ���ȫƥ��
        //boolean found = m.find();//�÷���ɨ�����������Բ������ģʽƥ�����һ�������С�
        String foundStr = "";
        try {
            
            /*if (found) {
                foundStr = m.group();
                String foundstring0 = m.group(0);  //group(),group(0)���ط����������ʽ�����ַ���
                String foundstring1 = m.group(1);  //group(1)���ط����������ʽ�����ַ�����ƥ���һ�����ʽ�����ַ���
                String foundstring2 = m.group(2);   //group(2)���ط����������ʽ�����ַ�����ƥ��ڶ������ʽ�����ַ���
                String foundstring3 = m.group(3);
                System.out.println("foundstring:"+foundStr);
                System.out.println("foundstring0:"+foundstring0);
                System.out.println("foundstring1:"+foundstring1);
                System.out.println("foundstring2:"+foundstring2);
                System.out.println("foundstring3:"+foundstring3);
            }*/
            while (m.find()) {
            	int groupCount = m.groupCount();
            	System.out.println("groupCount:"+groupCount);
                String foundstring0 = m.group(0);  //group(),group(0)���ط����������ʽ�����ַ���
                String foundstring1 = m.group(1);  //group(1)���ط����������ʽ�����ַ�����ƥ���һ�����ʽ�����ַ���
                System.out.println("foundstring:"+foundStr);
                System.out.println("foundstring0:"+foundstring0);
                System.out.println("foundstring1:"+foundstring1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundStr;
    }
    
    /**
     * ���ظ����ַ�����ƥ�����������ʽ�������ַ���
     * @param regex
     * @param decStr
     * @return List����������ƥ��������ʽ�����ַ���
     */
    public static List<String> searchSubStr(String regex,String decStr) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(decStr);  
        List<String> list = new ArrayList<String>();
        while(m.find()){
            list.add(m.group());
        }
        for (String string : list) {
System.out.println(string); 
        }
        return list;
    }

    /**
     * �滻�����ַ�����ƥ��������ʽ�����ַ���
     * @param regex��������ʽ
     * @param decStr����Ҫƥ����ַ���
     * @param replaceStr��������������ʽ���Ӵ��滻Ϊ���ַ���
     * @return�������滻�Ժ��µ��ַ���
     */
    public static String replace(String regex,String decStr,String replaceStr) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(decStr);
        // �滻
        String newstring = m.replaceAll(replaceStr);
System.out.println(newstring);
        return newstring;
    }

    public static void testSplit(String str,String regex) {
        // �ָ�
        String [] strs = str.split(regex);
        for(int i=0;i<strs.length;i++) {
            System.out.println(i+":::::::"+strs[i]);
        }
    }   
    
    @Test
    public void testValidataAll() {
        System.out.println(validataAll("\\w+��", "334455aaa��"));
        System.out.println(validataAll("\\d+", "334455aaa"));
    }
    
    @Test
    public void testValidataSub() {
        System.out.println(validataSub("\\d+", "334455aaa"));
        System.out.println(validataSub("\\d+", "hhhhhaaa"));
    }
    
    @Test
    public void testSearch() {
//        search("(\\d+)([a-z]+)(\\d+)", "334455aaa33--3232423bbb22-3232ccc411-3232ddd411"); 
    	String message = "";
    	message = "=====================>> ��1����2�� ���ظ�΢����Ϣ: <xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        search("(��)(.{1})(��)", message); 
    }
    
    @Test
    public void testSearchSub() {
        System.out.println(searchSubStr("(\\d+)([a-z]+)", "334455aaa--3232423aaa-32324bbb")); 
    }
    
    @Test
    public void testReplace() {
        replace("\\d", "dsfd;sa;ksd12a34b567c890d888e999f","*");
    }
    
    @Test
    public void testSp() {
//        testSplit("abc5Adefghi7Ajklmn","(\\d)A");
    	String log = "2017-12-21 15:57:06.754 [catalina-exec-5] - [INFO]     com.tzx.cc.service.rest.OrderRest(:81)     [ORDER -\u003e update]�ܲ��յ���������:{\"code\":0,\"data\":[{\"bill_num\":\"\",\"chanel\":\"WM10\",\"dispatch_time\":null,\"distribution_time\":null,\"finish_time\":null,\"order_code\":\"\",\"order_repayment\":[],\"order_state\":\"\",\"order_type\":\"OUTSIDE_ORDER\",\"receive_time\":\"\",\"receive_time_cancellation\":\"\",\"receive_time_dispatch\":null,\"receive_time_distribution\":null,\"receive_time_finish\":null,\"receive_time_qd\":\"2016-12-28 15:50:57\",\"take_time\":\"2016-12-28 15:50:57\",\"third_order_code\":\" \"}],\"msg\":\"\",\"oper\":\"update\",\"pagination\":{\"asc\":false,\"orderby\":\"\",\"pageno\":0,\"pagesize\":0,\"totalcount\":0},\"secret\":\"\",\"source\":\"SERVER\",\"store_id\":4,\"success\":true,\"t\":1513843172196,\"tenancy_id\":\"yunnby\",\"type\":\"ORDER\"}";
        testSplit(log,"\\s+");
    }
}
