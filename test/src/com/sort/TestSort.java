package com.sort;

import java.util.Arrays;

import org.junit.Test;

public class TestSort {
    
    @Test
    public void testSearch() {
        int[] arr = {234,245,77,3,543,67,78,95,378,678,205,753,457,2903,340} ;   
        int searchWord = 6780; 
        
        System.out.printf("��ͨѭ������%d�Ĵ�����%d",searchWord,genetalLoop(arr,searchWord));
        System.out.printf("���ַ�����%d�Ĵ�����%d",searchWord,binarySearch(arr,searchWord));
    }
    
    /**
     * ��ͨѭ������
     * @param arr
     * @param searchWord
     * @return
     */
    //��ͨ��ѭ������������Ҫ�Ƚ�һ�Σ��������1�������Ҫ�Ƚ�15�Σ�����8721
    static int genetalLoop(int[] arr,int searchWord){
        int searchCount = 0;
        for(int i=0;i<arr.length;i++){ 
            searchCount++; 
            if (searchWord==arr[i]) 
            break; 
        } 
        return searchCount;         
    }
    
    /**
     * ���ַ�����
     * @param arr
     * @param searchWord
     * @return
     */
    static int binarySearch(int[] arr,int searchWord){
        Arrays.sort(arr);   //�ȶԴ������������������
        System.out.println("\n"+Arrays.toString(arr));
        //���ַ����� 
        int iIndex=0;  
        int iStart=0;  
        int iEnd=arr.length-1; 
        int searchCount = 0;
        for(int i=0;i<arr.length/2;i++) { 
            searchCount++; 
            iIndex = (iStart+iEnd)/2; 
            if(arr[iIndex]<searchWord){ 
                System.out.println("aa");
                iStart = iIndex; 
            }else if(arr[iIndex]>searchWord){
                System.out.println("bb");
                iEnd = iIndex; 
            }else{ 
                break; 
            } 
        } 
        return searchCount; 
    }
    
    @Test
    public void testSort() {
        int[] values = { 3, 1, 6, 2, 9, 0, 7, 4, 5,8 };
        sort(values);
        System.out.println(Arrays.toString(values)); 
    }
    
    /**
     * ð������
     * @param values
     */
    static void sort(int[] values) {
        int temp;
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length - 1- i ; j++) {
                if (values[j] > values[j + 1]) {
                    temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }
            }
        }
    }
    
}
