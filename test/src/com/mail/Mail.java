package com.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.util.PublicUtil;
  
  
public class Mail {   
  
    private MimeMessage mimeMsg; //MIME�ʼ�����   
    private Session session; //�ʼ��Ự����   
    private Properties props; //ϵͳ����   
    //smtp��֤�û���������   
    private String username;   
    private String password;   
    private Multipart mp; //Multipart����,�ʼ�����,����,���������ݾ���ӵ����к�������MimeMessage����   
       
    /** 
     * Constructor 
     * @param smtp �ʼ����ͷ����� 
     */  
    public Mail(boolean need){   
      setSmtpHost(); 
      setNeedAuth(need);
      createMimeMessage();   
    }   
    /** 
     * Constructor 
     * @param smtp �ʼ����ͷ����� 
     */  
    public Mail(String smtp,boolean need){   
        setSmtpHost(smtp);   
        setNeedAuth(need);
        createMimeMessage();   
    }   
  
    /** 
     * �����ʼ����ͷ����� 
     * @param hostName String  
     */  
    private void setSmtpHost(String hostName) {   
        System.out.println("����ϵͳ���ԣ�mail.smtp.host = "+hostName);   
        if(props == null)  
            props = System.getProperties(); //���ϵͳ���Զ���    
        props.put("mail.smtp.host",hostName); //����SMTP����   
    }   
    /** 
     * �����ʼ����ͷ����� 
     * @param hostName String  
     */  
    private void setSmtpHost() {   
      if(props == null)  
        props = new PublicUtil().loadProperties("mail.properties");
      System.out.println("��ȡϵͳ���ԣ�mail.smtp.host = "+props.getProperty("mail.smtp.host"));   
    }   
  
  
    /** 
     * ����MIME�ʼ�����   
     * @return 
     */  
    private boolean createMimeMessage()   
    {   
        try {   
            System.out.println("׼����ȡ�ʼ��Ự����");   
            session = Session.getInstance(props,null); //����ʼ��Ự����   
        }   
        catch(Exception e){   
            System.err.println("��ȡ�ʼ��Ự����ʱ��������"+e);   
            return false;   
        }   
      
        System.out.println("׼������MIME�ʼ�����");   
        try {   
            mimeMsg = new MimeMessage(session); //����MIME�ʼ�����   
            mp = new MimeMultipart();   
          
            return true;   
        } catch(Exception e){   
            System.err.println("����MIME�ʼ�����ʧ�ܣ�"+e);   
            return false;   
        }   
    }     
      
    /** 
     * ����SMTP�Ƿ���Ҫ��֤ 
     * @param need 
     */  
    private void setNeedAuth(boolean need) {   
        System.out.println("����smtp�����֤��mail.smtp.auth = "+need);   
        if(props == null) props = System.getProperties();   
        if(need){   
            props.put("mail.smtp.auth","true");   
        }else{   
            props.put("mail.smtp.auth","false");   
        }   
    }   
  
    /** 
     * �����û��������� 
     * @param name 
     * @param pass 
     */  
    private void setNamePass(String name,String pass) {   
        username = name;   
        password = pass;   
    }   
  
    /** 
     * �����ʼ����� 
     * @param mailSubject 
     * @return 
     */  
    private boolean setSubject(String mailSubject) {   
        System.out.println("�����ʼ����⣡");   
        try{   
            mimeMsg.setSubject(mailSubject);   
            return true;   
        }   
        catch(Exception e) {   
            System.err.println("�����ʼ����ⷢ������");   
            return false;   
        }   
    }  
      
    /**  
     * �����ʼ����� 
     * @param mailBody String  
     */   
    private boolean setBody(String mailBody) {   
        try{   
            BodyPart bp = new MimeBodyPart();   
            bp.setContent(""+mailBody,"text/html;charset=GBK");   
            mp.addBodyPart(bp);   
          
            return true;   
        } catch(Exception e){   
        System.err.println("�����ʼ�����ʱ��������"+e);   
        return false;   
        }   
    }   
    /**  
     * ��Ӹ��� 
     * @param filename String  
     */   
    private boolean addFileAffix(String[] filename) {   
      
        try{   
          BodyPart bp = null;   
          FileDataSource fileds = null;   
          for (String string : filename) {
            System.out.println("�����ʼ�������"+string);   
            bp = new MimeBodyPart();   
            fileds = new FileDataSource(string);   
            bp.setDataHandler(new DataHandler(fileds));   
            bp.setFileName(MimeUtility.encodeText(fileds.getName(),"UTF-8","B"));  
            mp.addBodyPart(bp);   
          }
            
          return true;   
        } catch(Exception e){   
          System.err.println("�����ʼ�������"+filename+"��������"+e);   
          return false;   
        }   
    }   
      
    /**  
     * ���÷����� 
     * @param from String  
     */   
    private boolean setFrom(String from) {   
        System.out.println("���÷����ˣ�");   
        try{   
            mimeMsg.setFrom(new InternetAddress(from)); //���÷�����   
            return true;   
        } catch(Exception e) {   
            return false;   
        }   
    }   
    
    /**  
     * ����������
     * @param to
     * @return
     */ 
    private boolean setTo(String[] to){   
        if(to == null)return false;   
        try{   
          List<InternetAddress> list = new ArrayList<InternetAddress>();
          for (String string : to) {
            list.add(new InternetAddress(string));
          }
          mimeMsg.setRecipients(Message.RecipientType.TO,list.toArray(new InternetAddress[list.size()]));   
            return true;   
        } catch(Exception e) {   
            return false;   
        }     
    }   
      
    /**
     * ���ó�����
     * @param copyto
     * @return
     */  
    private boolean setCopyTo(String[] copyto)   
    {   
        if(copyto == null)return false;   
        try{   
          List<InternetAddress> list = new ArrayList<InternetAddress>();
          for (String string : copyto) {
            list.add(new InternetAddress(string));
          }
          mimeMsg.setRecipients(Message.RecipientType.CC,list.toArray(new InternetAddress[list.size()]));   
        return true;   
        }   
        catch(Exception e)   
        { return false; }   
    }   
      
    /**  
     * �����ʼ� 
     */   
    private boolean sendOut()   
    {   
        try{   
            mimeMsg.setContent(mp);   
            mimeMsg.saveChanges();   
            System.out.println("���ڷ����ʼ�....");   
            if(props.get("mail.smtp.auth").equals("true")){
              Transport transport = session.getTransport("smtp");   
              transport.connect((String)props.get("mail.smtp.host"),username,password);   
              transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.TO));   
              transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.CC));  
              transport.close();   
            } else {
              Transport.send(mimeMsg);   
            }
            System.out.println("�����ʼ��ɹ���");   
            return true;   
        } catch(Exception e) {   
            System.err.println("�ʼ�����ʧ�ܣ�"+e);   
            return false;   
        }   
    }   
  
    /** 
     * ����sendOut��������ʼ�����(�޳��ͺ͸���) 
     * @param smtp 
     * @param from 
     * @param to 
     * @param subject 
     * @param content 
     * @param username 
     * @param password 
     * @return boolean 
     */  
    public static boolean send(String from,String[] to,String subject,String content,String username,String password) {  
      Mail theMail = new Mail(true);  
      
      if(!theMail.setSubject(subject)) return false;  
      if(!theMail.setBody(content)) return false;  
      if(!theMail.setTo(to)) return false;  
      if(!theMail.setFrom(from)) return false;  
      theMail.setNamePass(username,password);  
      
      if(!theMail.sendOut()) return false;  
      return true;  
    }  
    
    /** 
     * ����sendOut��������ʼ�����(�޳��ͺ͸���)
     * @param smtp 
     * @param from 
     * @param to 
     * @param subject 
     * @param content 
     * @param username 
     * @param password 
     * @return boolean 
     */  
    public static boolean send(String smtp,String from,String[] to,String subject,String content,String username,String password) {  
        Mail theMail = new Mail(smtp,true);  
          
        if(!theMail.setSubject(subject)) return false;  
        if(!theMail.setBody(content)) return false;  
        if(!theMail.setTo(to)) return false;  
        if(!theMail.setFrom(from)) return false;  
        theMail.setNamePass(username,password);  
          
        if(!theMail.sendOut()) return false;  
        return true;  
    }  
      
    /** 
     * ����sendOut��������ʼ�����,������ 
     * @param smtp 
     * @param from 
     * @param to 
     * @param copyto 
     * @param subject 
     * @param content 
     * @param username 
     * @param password 
     * @return boolean 
     */  
    public static boolean sendAndCc(String from,String[] to,String[] copyto,String subject,String content,String username,String password) {  
      Mail theMail = new Mail(true);  
      
      if(!theMail.setSubject(subject)) return false;  
      if(!theMail.setBody(content)) return false;  
      if(!theMail.setTo(to)) return false;  
      if(!theMail.setCopyTo(copyto)) return false;  
      if(!theMail.setFrom(from)) return false;  
      theMail.setNamePass(username,password);  
      
      if(!theMail.sendOut()) return false;  
      return true;  
    }  
    
    /** 
     * ����sendOut��������ʼ�����,������ 
     * @param smtp 
     * @param from 
     * @param to 
     * @param copyto 
     * @param subject 
     * @param content 
     * @param username 
     * @param password 
     * @return boolean 
     */  
    public static boolean sendAndCc(String smtp,String from,String[] to,String[] copyto,String subject,String content,String username,String password) {  
        Mail theMail = new Mail(smtp,true);  
          
        if(!theMail.setSubject(subject)) return false;  
        if(!theMail.setBody(content)) return false;  
        if(!theMail.setTo(to)) return false;  
        if(!theMail.setCopyTo(copyto)) return false;  
        if(!theMail.setFrom(from)) return false;  
        theMail.setNamePass(username,password);  
          
        if(!theMail.sendOut()) return false;  
        return true;  
    }  
      
    /** 
     * ����sendOut��������ʼ�����,������ 
     * @param smtp 
     * @param from 
     * @param to 
     * @param subject 
     * @param content 
     * @param username 
     * @param password 
     * @param filename ����·�� 
     * @return 
     */  
    public static boolean send(String smtp,String from,String[] to,String subject,String content,String username,String password,String[] filename) {  
        Mail theMail = new Mail(smtp,true);  
          
        if(!theMail.setSubject(subject)) return false;  
        if(!theMail.setBody(content)) return false;  
        if(!theMail.addFileAffix(filename)) return false;   
        if(!theMail.setTo(to)) return false;  
        if(!theMail.setFrom(from)) return false;  
        theMail.setNamePass(username,password);  
          
        if(!theMail.sendOut()) return false;  
        return true;  
    }  
      
    /** 
     * ����sendOut��������ʼ�����,�������ͳ��� 
     * @param smtp 
     * @param from 
     * @param to 
     * @param copyto 
     * @param subject 
     * @param content 
     * @param username 
     * @param password 
     * @param filename 
     * @return 
     */  
    public static boolean sendAndCc(String smtp,String from,String[] to,String[] copyto,String subject,String content,String username,String password,String[] filename) {  
        Mail theMail = new Mail(smtp,true);  
          
        if(!theMail.setSubject(subject)) return false;  
        if(!theMail.setBody(content)) return false;  
        if(!theMail.addFileAffix(filename)) return false;   
        if(!theMail.setTo(to)) return false;  
        if(!theMail.setCopyTo(copyto)) return false;  
        if(!theMail.setFrom(from)) return false;  
        theMail.setNamePass(username,password);  
          
        if(!theMail.sendOut()) return false;  
        return true;  
    }  
    
    public static boolean sendMail(String to,String subject,String content) {
      Properties props = new PublicUtil().loadProperties("sendMailInfo.properties");
      to = to==null?"":to;
      String[] toArr = {to};
      String[] copytoArr = {props.getProperty("username")};
      return Mail.sendAndCc(props.getProperty("username"), toArr, copytoArr,subject, content, props.getProperty("username"), props.getProperty("password"));
    }
    
    
    public static void main(String[] args){  
      String smtp = "mail.163.com.cn";  
      String from = "xXXXX3@163.com.cn";
      String username="XXXXX@163.com.cn";  
      String password="XXXX";  
      
      
      String[] to = {"xxxxxx@163.com"};
      
      String[] copyto = {" cccc@@@.com"};
      
      String subject = " subject"; 
      String contentSend = "��test"";
      
      String content = " sdfsdfsdf"+contentSend;
      
      String[] filename = {"D:\\����.txt","D:\\����.txt"};//����·�����磺F:\\�ʼ�<a>\\struts2</a>��mvc.txt
//      Mail.sendAndCc(smtp, from, to, copyto, subject, content, username, password, filename);  
      //Mail.send(smtp, from, to, subject, content, username, password);  
      //Mail.sendMail(to, subject, content);
      //sendAndCc(smtp, from, to, copyto, subject, content, username, password);
      //sendAndCc(from, to, copyto, subject, content, username, password);
      //sendMail("XXXXXX@163.com", subject, content);
      sendAndCc(smtp, from, to, copyto, subject, content, username, password, filename);
  }  
}   