package com.xjldtc.leetcode.questionbank2;

import java.util.*;

public class TwoNumListAdd {

	public static void main(String[] args) {
		System.out.println(twoNumListAdd(99,9));
	}

	/*
	 * 利用arrayList和char数组实现 
	 * 测试用例覆盖 一表比另一个表长，单表为空,进位超过链表最大值
	 * 时间复杂度为
	 */
	public static int twoNumListAdd(int a, int b) {
		int returnNum = 0;
		String markStr = "";
		List<Integer> listA = num2List(a);
		List<Integer> listB = num2List(b);
		int maxLenth = 0;
		if (listA.size() - listB.size() > 0) {
			maxLenth = listA.size();
			listB = complementList(listB,maxLenth);
		} else {
			maxLenth = listB.size();
			listA = complementList(listA,maxLenth);
		}
		int markNum = 0;
		for (int i = 0; i < maxLenth; i++) {
			List<Integer> returnList = num2List(listA.get(i) + listB.get(i) + markNum);
			if(returnList.size()>1){
				markNum = returnList.get(1);
			}else{
				markNum = 0;
			}
			markStr += returnList.get(0);
		}
		if(markNum>0){
			markStr += markNum;
		}
		returnNum = Integer.parseInt(markStr);
		return returnNum;
	}

	private static List<Integer> complementList(List<Integer> listA,int maxLenth) {
		for(int i = listA.size(); i < maxLenth;i++){
			listA.add(0);
		}
		return listA;
	}

	private static List<Integer> num2List(int num) {
		List<Integer> returnList = new ArrayList<Integer>();
		String numStr = num + "";
		char[] arry = numStr.toCharArray();
		for (int i = arry.length - 1; i >= 0; i--) {
			returnList.add(arry[i] - '0');
		}
		return returnList;
	}
}
