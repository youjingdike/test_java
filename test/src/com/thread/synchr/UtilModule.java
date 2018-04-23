package com.thread.synchr;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilModule {
	/** �������ɽ�������ˮ�ŵľ�̬��Ա���� */
    public static int i = 0;
	public static Integer ii = 0;

	
	public static synchronized String createTaxDealCode() {
		StringBuilder sb = new StringBuilder();

		// 6.�����λ�������ˮ�ţ�3λ��
		i++;
		if (i < 10) {
			sb.append("00" + i);
		} else if (i < 100) {
			sb.append("0" + i);
		} else if (i >= 100) {
			sb.append(i);
		}
		if (i == 999) {
			i = 0;
		}
		return sb.toString();
	}
	
	public static String createTaxDealCode2() {
        StringBuilder sb = new StringBuilder();

        // 6.�����λ�������ˮ�ţ�3λ��
        synchronized(UtilModule.class){
            i++;
            if (i < 10) {
                sb.append("00" + i);
            } else if (i < 100) {
                sb.append("0" + i);
            } else if (i >= 100) {
                sb.append(i);
            }
            if (i == 999) {
                i = 0;
            }
        }
        return sb.toString();
    }
	
	/**
     * ����������ģ��
     * 
     * @param flag
     *            ������ʶ����
     * @param companyCode
     *            ��˾����
     * @param areaCode
     *            �����������
     * @param serviceCode
     *            �������
     * @return taxDealCode ������
     */
    public static synchronized String createTaxDealCode(String flag,
            String companyCode, String areaCode, String serviceCode) {
        // ������ʶ���루1λ��+��˾���루4λ��+����������루6λ��+������루1λ��+���ڣ�8λ��+ʱ�䣨9λ��+��ˮ�ţ�3λ��
        StringBuilder sb = new StringBuilder();

        // 1.���ƽ̨��������ʶ����(1λ)
        sb.append(flag);

        // 2.��ӹ�˾���루4λ��
        companyCode = companyCode.toUpperCase();
        sb.append(companyCode);

        // 3.���ӹ���������루6λ��
        serviceCode = serviceCode.toUpperCase();
        sb.append(areaCode);

        // 4.��ӷ����־���루1λ��
        sb.append(serviceCode);

        // 5.����8λ���ں�9λʱ��
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyyMMddHHmmssSSS");
        String da = simpleDateFormat.format(date);
        sb.append(da.substring(0, da.length()-2));

        // 6.�����λ�������ˮ�ţ�3λ��
        i++;
        if (i < 10) {
            sb.append("00" + i);
        } else if (i < 100) {
            sb.append("0" + i);
        } else if (i >= 100) {
            sb.append(i);
        }
        if (i == 999) {
            i = 0;
        }
        return sb.toString();
    }
	
    public static String createTaxDealCode1() {
        StringBuilder sb = new StringBuilder();

        // 6.�����λ�������ˮ�ţ�3λ��
        synchronized(ii) {
            ii++;
            if (ii < 10) {
                sb.append("00" + ii);
            } else if (ii < 100) {
                sb.append("0" + ii);
            } else if (ii >= 100) {
                sb.append(ii);
            }
            if (ii == 999) {
                ii = 0;
            }
        }
        return sb.toString();
    }
    
	public static void main(String[] args) {
	    T1 t1 = new T1();
//	    T2 t2 = new T2();
	    Thread r1 = new Thread(t1,"r1");
	    Thread r2 = new Thread(t1,"r2");
	    
//	    Thread r2 = new Thread(t2);
	    r1.start();
	    r2.start();
	}
}

class T1 implements Runnable{

    public void run() {
//        UtilModule module = new UtilModule();
        for (int i=0; i<500; i++) {
            System.out.println(Thread.currentThread().getName() + "��" + UtilModule.createTaxDealCode1() + ",i=" + i);
//            System.out.println("T1��" + UtilModule.createTaxDealCode1());
//            System.out.println("T1��" + module.createTaxDealCode1());
        }
        
    }
    
}
class T2 implements Runnable{

    public void run() {
//        UtilModule module = new UtilModule();
        for (int i=0; i<100; i++) {
//            System.out.println("T2��" + module.createTaxDealCode1());
            System.out.println("T2��" + UtilModule.createTaxDealCode1());
        }
        
    }
    
}