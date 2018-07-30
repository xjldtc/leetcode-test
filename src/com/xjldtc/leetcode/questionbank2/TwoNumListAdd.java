package com.xjldtc.leetcode.questionbank2;

import java.util.*;

public class TwoNumListAdd {

	public static void main(String[] args) {
		// System.out.println(twoNumListAdd(99,9));
		System.out.println(twoNumListAddXY(num2List(99), num2List(1)));
	}

	/**
	 * while 写法 符合提议 本质和twoNumListAdd是同一种实现
	 */
	public static int twoNumListAddXY(List<Integer> listA, List<Integer> listB) {
		String returnValue = "";
		List<Integer> returnList = new ArrayList<Integer>();
		int maxLenth = 0;
		if (listA.size() - listB.size() > 0) {
			maxLenth = listA.size();
			listB = complementList(listB, maxLenth);
		} else {
			maxLenth = listB.size();
			listA = complementList(listA, maxLenth);
		}
		int i = 0;
		int carry = 0;
		while ((listA != null || listB != null) && i < maxLenth) {
			int x = listA.get(i);
			int y = listB.get(i);
			int sum = carry + x + y;
			carry = sum / 10;
			returnList.add(sum % 10);
			i++;
		}
		if(carry>0){
			returnList.add(carry);
		}
		for (int j = 0; j < returnList.size(); j++) {
			returnValue += returnList.get(j);
		}
		return Integer.parseInt(returnValue);
	}

	/*
	 * 利用arrayList和char数组实现 测试用例覆盖 一表比另一个表长，单表为空,进位超过链表最大值
	 */
	public static int twoNumListAdd(int a, int b) {
		int returnNum = 0;
		String markStr = "";
		List<Integer> listA = num2List(a);
		List<Integer> listB = num2List(b);
		int maxLenth = 0;
		if (listA.size() - listB.size() > 0) {
			maxLenth = listA.size();
			listB = complementList(listB, maxLenth);
		} else {
			maxLenth = listB.size();
			listA = complementList(listA, maxLenth);
		}
		int markNum = 0;
		for (int i = 0; i < maxLenth; i++) {
			List<Integer> returnList = num2List(listA.get(i) + listB.get(i) + markNum);
			if (returnList.size() > 1) {
				markNum = returnList.get(1);
			} else {
				markNum = 0;
			}
			markStr += returnList.get(0);
		}
		if (markNum > 0) {
			markStr += markNum;
		}
		returnNum = Integer.parseInt(markStr);
		return returnNum;
	}

	private static List<Integer> complementList(List<Integer> listA, int maxLenth) {
		for (int i = listA.size(); i < maxLenth; i++) {
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
