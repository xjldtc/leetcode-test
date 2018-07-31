package com.xjldtc.leetcode.questionbank2;

public class TwoNumListAdd4Submit {
	public static void main(String[] args) {
		ListNode markNode = new ListNode(0);
		ListNode listA = new ListNode(1);
		markNode.val = 8;
		listA.next = markNode;

		markNode = new ListNode(0);
		ListNode listB = new ListNode(0);
		System.out.println(addTwoNumbers(listA, listB).toString());
	}
	
	/*
	 * 问题长时间卡在如何迭代生成新的listNode 
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode returnList = new ListNode(0);
		ListNode nextList = returnList;
		ListNode list1 = l1;
		ListNode list2 = l2;
		int x = 0;
		int y = 0;
		int carry = 0;
		while (list1 != null || list2 != null) {
			if(list1!=null){x = list1.val;}else{x=0;}
			if(list2!=null){y = list2.val;}else{y=0;}
			int sum = x + y + carry;
			carry = sum / 10;
			nextList.next = new ListNode(sum % 10);//如何做好递归值传递
			nextList = nextList.next;//如何做好递归值传递
			if(list1!=null){list1 = list1.next;}else{list1=null;}
			if(list2!=null){list2 = list2.next;}else{list2=null;}
			
		}
		if (carry > 0) {
			nextList.next = new ListNode(carry);
		}
		return returnList.next;//对于成员变量的理解欠缺
	}
	
	/**
	 * 官方答案
	 */
	public static ListNode addTwoNumbers4Consult(ListNode l1, ListNode l2) {
		 ListNode dummyHead = new ListNode(0);
		 ListNode p = l1, q = l2, curr = dummyHead;
		 int carry = 0;
		 while (p != null || q != null) {
		 int x = (p != null) ? p.val : 0;
		 int y = (q != null) ? q.val : 0;
		 int sum = carry + x + y;
		 carry = sum / 10;
		 curr.next = new ListNode(sum % 10);
		 System.out.println(curr);
		 curr = curr.next;
		 System.out.println(curr);
		 if (p != null) p = p.next;
		 if (q != null) q = q.next;
		 }
		 if (carry > 0) {
		 curr.next = new ListNode(carry);
		 }
		 return dummyHead.next;
	}
}
