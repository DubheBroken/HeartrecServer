package com.utils;

import java.util.ArrayList;
import java.util.List;

public class WordUtils { 
	//���õ���ģʽ 
	private static List<String> list =new ArrayList<String>();//���ǿ��Դ���������ݿ��д�����дʻ㣬��װ��list���� 
	static {
		//������д� 
		NioReadLines nioReadLines = new NioReadLines();
		list=nioReadLines.getList();
	} 
	public static List<String> getWord(){ 
	return list; 
	} 
	public static void addWord(String name){ 
	list.add(name); 
	} 
	public static void sava(){ 
	//�������list��������ݴ浽���ݿ��У�����ά������ȻҲ����д��ɾ�Ĳ�� 
	} 
	
}