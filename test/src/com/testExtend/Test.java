package com.testExtend;

public class Test extends Tt{
    String s = "wo shi son";
    String ss = s;//��ʼ����ʱ��s�ĳ�ʼ��ֵ��ʲô��ss����ʲô
    
    public Test() {
    }

    public Test(String s) {
        this.s = s;
    }
    
    
    private Test(String s, String ss) {
        super(ss);
        this.s = s;
    }

    public void saySS() {
        System.out.println(ss);
    }
    
    
    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public static void main(String[] args) {
        //System.out.println(isTrue());
//        Test t = new Test();
//        Test t = new Test("���");
        Test t = new Test("���","wo shi mi");
//        t.setS("sdfsad");
        System.out.println(t.getS());
        t.saySS();
        System.out.println(t.mi);
    }
    
    public static boolean isTrue() {
        int x = 1;
//        return x==1?true:false;
        return x==1;
    }
}

abstract class Tt {
    String s = "wo shi father";
    String mi;
    
    public Tt() {
    }

    public Tt(String mi) {
        this.mi = mi;
    }
    
}