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
  
    private MimeMessage mimeMsg; //MIME邮件对象   
    private Session session; //邮件会话对象   
    private Properties props; //系统属性   
    //smtp认证用户名和密码   
    private String username;   
    private String password;   
    private Multipart mp; //Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象   
       
    /** 
     * Constructor 
     * @param smtp 邮件发送服务器 
     */  
    public Mail(boolean need){   
      setSmtpHost(); 
      setNeedAuth(need);
      createMimeMessage();   
    }   
    /** 
     * Constructor 
     * @param smtp 邮件发送服务器 
     */  
    public Mail(String smtp,boolean need){   
        setSmtpHost(smtp);   
        setNeedAuth(need);
        createMimeMessage();   
    }   
  
    /** 
     * 设置邮件发送服务器 
     * @param hostName String  
     */  
    private void setSmtpHost(String hostName) {   
        System.out.println("设置系统属性：mail.smtp.host = "+hostName);   
        if(props == null)  
            props = System.getProperties(); //获得系统属性对象    
        props.put("mail.smtp.host",hostName); //设置SMTP主机   
    }   
    /** 
     * 设置邮件发送服务器 
     * @param hostName String  
     */  
    private void setSmtpHost() {   
      if(props == null)  
        props = new PublicUtil().loadProperties("mail.properties");
      System.out.println("读取系统属性：mail.smtp.host = "+props.getProperty("mail.smtp.host"));   
    }   
  
  
    /** 
     * 创建MIME邮件对象   
     * @return 
     */  
    private boolean createMimeMessage()   
    {   
        try {   
            System.out.println("准备获取邮件会话对象！");   
            session = Session.getInstance(props,null); //获得邮件会话对象   
        }   
        catch(Exception e){   
            System.err.println("获取邮件会话对象时发生错误！"+e);   
            return false;   
        }   
      
        System.out.println("准备创建MIME邮件对象！");   
        try {   
            mimeMsg = new MimeMessage(session); //创建MIME邮件对象   
            mp = new MimeMultipart();   
          
            return true;   
        } catch(Exception e){   
            System.err.println("创建MIME邮件对象失败！"+e);   
            return false;   
        }   
    }     
      
    /** 
     * 设置SMTP是否需要验证 
     * @param need 
     */  
    private void setNeedAuth(boolean need) {   
        System.out.println("设置smtp身份认证：mail.smtp.auth = "+need);   
        if(props == null) props = System.getProperties();   
        if(need){   
            props.put("mail.smtp.auth","true");   
        }else{   
            props.put("mail.smtp.auth","false");   
        }   
    }   
  
    /** 
     * 设置用户名和密码 
     * @param name 
     * @param pass 
     */  
    private void setNamePass(String name,String pass) {   
        username = name;   
        password = pass;   
    }   
  
    /** 
     * 设置邮件主题 
     * @param mailSubject 
     * @return 
     */  
    private boolean setSubject(String mailSubject) {   
        System.out.println("设置邮件主题！");   
        try{   
            mimeMsg.setSubject(mailSubject);   
            return true;   
        }   
        catch(Exception e) {   
            System.err.println("设置邮件主题发生错误！");   
            return false;   
        }   
    }  
      
    /**  
     * 设置邮件正文 
     * @param mailBody String  
     */   
    private boolean setBody(String mailBody) {   
        try{   
            BodyPart bp = new MimeBodyPart();   
            bp.setContent(""+mailBody,"text/html;charset=GBK");   
            mp.addBodyPart(bp);   
          
            return true;   
        } catch(Exception e){   
        System.err.println("设置邮件正文时发生错误！"+e);   
        return false;   
        }   
    }   
    /**  
     * 添加附件 
     * @param filename String  
     */   
    private boolean addFileAffix(String[] filename) {   
      
        try{   
          BodyPart bp = null;   
          FileDataSource fileds = null;   
          for (String string : filename) {
            System.out.println("增加邮件附件："+string);   
            bp = new MimeBodyPart();   
            fileds = new FileDataSource(string);   
            bp.setDataHandler(new DataHandler(fileds));   
            bp.setFileName(MimeUtility.encodeText(fileds.getName(),"UTF-8","B"));  
            mp.addBodyPart(bp);   
          }
            
          return true;   
        } catch(Exception e){   
          System.err.println("增加邮件附件："+filename+"发生错误！"+e);   
          return false;   
        }   
    }   
      
    /**  
     * 设置发信人 
     * @param from String  
     */   
    private boolean setFrom(String from) {   
        System.out.println("设置发信人！");   
        try{   
            mimeMsg.setFrom(new InternetAddress(from)); //设置发信人   
            return true;   
        } catch(Exception e) {   
            return false;   
        }   
    }   
    
    /**  
     * 设置收信人
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
     * 设置抄送人
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
     * 发送邮件 
     */   
    private boolean sendOut()   
    {   
        try{   
            mimeMsg.setContent(mp);   
            mimeMsg.saveChanges();   
            System.out.println("正在发送邮件....");   
            if(props.get("mail.smtp.auth").equals("true")){
              Transport transport = session.getTransport("smtp");   
              transport.connect((String)props.get("mail.smtp.host"),username,password);   
              transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.TO));   
              transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.CC));  
              transport.close();   
            } else {
              Transport.send(mimeMsg);   
            }
            System.out.println("发送邮件成功！");   
            return true;   
        } catch(Exception e) {   
            System.err.println("邮件发送失败！"+e);   
            return false;   
        }   
    }   
  
    /** 
     * 调用sendOut方法完成邮件发送(无抄送和附件) 
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
     * 调用sendOut方法完成邮件发送(无抄送和附件)
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
     * 调用sendOut方法完成邮件发送,带抄送 
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
     * 调用sendOut方法完成邮件发送,带抄送 
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
     * 调用sendOut方法完成邮件发送,带附件 
     * @param smtp 
     * @param from 
     * @param to 
     * @param subject 
     * @param content 
     * @param username 
     * @param password 
     * @param filename 附件路径 
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
     * 调用sendOut方法完成邮件发送,带附件和抄送 
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
      String smtp = "mail.cnpc.com.cn";  
      String from = "xingqian3@cnpc.com.cn";
      String username="xingqian3@cnpc.com.cn";  
      String password="198522";  
      
      
      String[] to = {"xingqianmail@163.com"};
      
      String[] copyto = {"xingqian3@cnpc.com.cn"};
      
      String subject = "《集团公司工程技术标准体系信息网（井下作业部分）》用户账号授权回执"; 
      String contentSend = "，您好：<br/>"+
      "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您在《集团公司工程技术专业标准化信息网（井下作业部分）》的账号已授权，可以正常使用。欢迎您通过如下链接登录访问：<br/>"+
      "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.登录网址：http://10.171.1.240:9090/DHS/index.jsp<br/>"+
      "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.登录用户名：您的集团公司或股份公司邮箱<br/>"+
      "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.登录密码：您的邮箱密码<br/><br/>"+
      "【备注】<br/>"+
      "1．一般用户只能在线阅读、查询标准和标准全文本检索。<br/>"+
      "2. 管理员除了在线阅读、查询标准和标准全文本检索外，还可以下载、统计、分析所有标准。<br/>"+
      "3. 在使用过程中，如果有任何意见或建议，请联系超级管理员。<br/>"+
      "联系人：刘伟<br/>"+
      "联系方式：010-5928 6306<br/>"+
      "邮箱：liuwei.gwdc@cnpc.com.cn<br/>"+
      "单位：长城钻探工程有限公司工程技术部<br/>"+
      "祝工作顺利！<br/>"+
      "集团公司工程技术标准体系信息网项目组<br/><br/>" +
      "邮箱：technology.gwdc@cnpc.com.cn";
      
      String content = "邢谦"+contentSend;
      
      String[] filename = {"D:\\测试.txt","D:\\测试.txt"};//附件路径，如：F:\\笔记<a>\\struts2</a>与mvc.txt
//      Mail.sendAndCc(smtp, from, to, copyto, subject, content, username, password, filename);  
      //Mail.send(smtp, from, to, subject, content, username, password);  
      //Mail.sendMail(to, subject, content);
      //sendAndCc(smtp, from, to, copyto, subject, content, username, password);
      //sendAndCc(from, to, copyto, subject, content, username, password);
      //sendMail("xingqianmail@163.com", subject, content);
      sendAndCc(smtp, from, to, copyto, subject, content, username, password, filename);
  }  
}   