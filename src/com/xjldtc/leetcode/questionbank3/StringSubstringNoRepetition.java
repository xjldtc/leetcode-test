package com.xjldtc.leetcode.questionbank3;

import java.util.HashSet;
import java.util.Set;

public class StringSubstringNoRepetition {
	public static void main(String[] args) {
		String str1 = "adlsafhasduascasg";
		System.out.println(lengthOfLongestSubstring(str1));
		System.out.println(lengthOfLongestSubstringBruteForce(str1));
	}
    
	/**
	 * 利用hash判断重复，此方法 为暴力法的改进，将循环计数和判断是否有重复放在一次循环中
	 * 时间复杂度为  循环两次查找O(n^2)
	 * 空间复杂度为 表中存储了 n个元素  O(n)
	 */
	public static int lengthOfLongestSubstring(String s) {
		int maxLength = 0;
		for (int i = 0; i < s.length(); i++) {
			Set<String> set = new HashSet<>();
			int markLength = 0;
			set.add(s.substring(i, i + 1));
			for (int j = i + 1; j < s.length(); j++) {
				if ( set.contains(s.substring(j, j + 1))) {
					break;
				} else {
					set.add(s.substring(j, j + 1));
					markLength++;
				}	
			}
			if(markLength>maxLength){
				maxLength = markLength;
			}
		}
		if(s.length()<maxLength+1){
			return 0;
		}
		return maxLength + 1;
	}
	
	/**
	 * 以下为参考写法
	 */
	
	/**
	 * 暴力法 
	 * 时间复杂度：O(n^3)
	 * 
	 */
	/**
	 * 枚举给定字符串的所有子字符串，枚举他们的开始结束索引，假设开始和结束的索引分别为i和j。那么我们有0<=i<j<=n 
	 * 因此使用 ii 从0到 n - 1 以及 j 从 i+1 到 n 这两个嵌套的循环，我们可以枚举出 s 的所有子字符串
	 */
	public static int lengthOfLongestSubstringBruteForce(String s) {
		int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
	}
	/**
	 * 要检查一个字符串是否有重复字符，我们可以使用集合。我们遍历字符串中的所有字符，并将它们逐个放入 set 中。
	 * 在放置一个字符之前，我们检查该集合是否已经包含它。如果包含，我们会返回 false。循环结束后，我们返回 true。
	 * 
	 */
	public static boolean allUnique(String s, int start, int end) {
		Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
	}
}
