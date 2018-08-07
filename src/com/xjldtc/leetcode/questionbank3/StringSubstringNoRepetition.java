package com.xjldtc.leetcode.questionbank3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringSubstringNoRepetition {
	public static void main(String[] args) {
		String str1 = "adlsafhasduascasg";
		System.out.println(slidingWindow4ASCII(str1));
		System.out.println(slidingWindowV2(str1));
		System.out.println(slidingWindow(str1));
	}

	/**
	 * 利用hash判断重复，此方法 为暴力法的改进，将循环计数和判断是否有重复放在一次循环中 时间复杂度为 循环两次查找O(n^2) 空间复杂度为
	 * 表中存储了 n个元素 O(n)
	 */
	public static int lengthOfLongestSubstring(String s) {
		int maxLength = 0;
		for (int i = 0; i < s.length(); i++) {
			Set<String> set = new HashSet<>();
			int markLength = 0;
			set.add(s.substring(i, i + 1));
			for (int j = i + 1; j < s.length(); j++) {
				if (set.contains(s.substring(j, j + 1))) {
					break;
				} else {
					set.add(s.substring(j, j + 1));
					markLength++;
				}
			}
			if (markLength > maxLength) {
				maxLength = markLength;
			}
		}
		if (s.length() < maxLength + 1) {
			return 0;
		}
		return maxLength + 1;
	}

	/**
	 * 以下为参考写法
	 */

	/**
	 * 暴力法 时间复杂度：O(n^3) 要验证在范围 [i,j)内的字符是否都是唯一的，我们需要迭代出所有字符，因此需要花费 O(j-i)的时间. n
	 * 对于给定的 i，对于所有 j∈[i+1,n] 所耗费的时间总和为： ​∑ O(j-i) ​ * i+1 n-1 n n-1
	 * 因此执行所有步骤耗去的时间总和为 O( Σ ( Σ (j-i))) = O( Σ (1+n-i)(n-i)/2 = O(n^3) i=0
	 * j=i+1 i=0 空间复杂度：O(min(n, m))，我们需要 O(k) 的空间来检查子字符串中是否有重复字符，其中 k 表示 Set
	 * 的大小。而 Set 的大小取决于字符串 n 的大小以及字符集/字母 m 的大小。
	 */
	/**
	 * 枚举给定字符串的所有子字符串，枚举他们的开始结束索引，假设开始和结束的索引分别为i和j。那么我们有0<=i<j<=n 因此使用 ii 从0到 n
	 * - 1 以及 j 从 i+1 到 n 这两个嵌套的循环，我们可以枚举出 s 的所有子字符串
	 */
	public static int lengthOfLongestSubstringBruteForce(String s) {
		int n = s.length();
		int ans = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j <= n; j++)
				if (allUnique(s, i, j))
					ans = Math.max(ans, j - i);
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
			if (set.contains(ch))
				return false;
			set.add(ch);
		}
		return true;
	}

	/**
	 * 滑动窗口
	 * 利用HashSet使单个字符查找的福再度从O(n)到O(1)，
	 * 然后将暴力法中的两次循环去查找变成，单次区间不再重复查找已经查找过的子串，
	 * 时间复杂度：O(2n) = O(n)，在最糟糕的情况下，每个字符将被 i 和 j 访问两次。
	 * 空间复杂度：O(min(m, n))，与之前的方法相同。滑动窗口法需要 O(k) 的空间，
	 *        其中 k 表示 Set 的大小。而Set的大小取决于字符串 n 的大小以及字符集/字母 m 的大小。 
	 * 
	 */
	public static int slidingWindow(String s) {
		int maxLength = s.length();
		int retrunNum = 0, i = 0, j = 0;
		Set<Character> set = new HashSet<>();
		while (i < maxLength && j < maxLength) {
            if (!set.contains(s.charAt(j))){//[i,j)是否存在i+1，不存在的话j区间加1，查看[i,j+1)
                set.add(s.charAt(j++));
                retrunNum = Math.max(retrunNum, j - i);//记录下最大的值
            }
            else {
                set.remove(s.charAt(i++));//存在就移动区间[i,j)，变成[i+1,j+1)
            }
		}
		return retrunNum;
	}
	/**
	 * 滑动窗口优化
	 * 如果 s[j] 在 [i, j) 范围内有与 j'重复的字符，我们不需要逐渐增加 i。 我们可以直接跳过 [i，j′] 范围内的所有元素，并将 i 变为 j' + 1
	 * 时间复杂度：O(n)。i,j不会重复查找
	 * 空间复杂度：O(min(m, n))与之前的方法相同。
	 */
	public static int slidingWindowV2(String s) {
		int maxLength = s.length();
		int retrunNum = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int j = 0, i = 0; j < maxLength; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);//如果有重复的值，直接跳过整个[i, j)区间，从j+1开始新的查找，因为 在这个区间内不可能出现比[i, j)再大的子串。
            }
            retrunNum = Math.max(retrunNum, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
		return retrunNum;
	}
	
	/**
	 * 本质是滑动窗口优化方式跳过
	 * 当我们知道该字符集比较小的时侯，我们可以用一个整数数组作为直接访问表来替换 Map。
	 * 时间复杂度：O(n)。i,j不会重复查找
	 * 空间复杂度：O(m),m是字符集大小
	 */
	public static int slidingWindow4ASCII(String s) {
		int maxLength = s.length();
		int retrunNum = 0;
		//int [26] 用于字母 ‘a’ - ‘z’或 ‘A’ - ‘Z’
		//int [128] 用于ASCII码
		//int [256] 用于扩展ASCII码
		int[] index = new int[128];//代替map key大小包含所有字符集的ASCII码
		for (int j = 0, i = 0; j < maxLength; j++) {
			 //index[s.charAt(j)] 代替 map.containsKey(s.charAt(j)) 直接通过int数组查找 
			 //本质仍然是直接跳过整个[i, j)区间，从j+1开始新的查找，因为 在这个区间内不可能出现比[i, j)再大的子串。
			 i = Math.max(index[s.charAt(j)], i);//跳过[i, j)取如果重复取上一个j的j + 1，作为当前起点
			 retrunNum = Math.max(retrunNum, j - i + 1);//当前位置到当前起点的大小是否比上一次大
	         index[s.charAt(j)] = j + 1;//以当前字母结尾的区间大小为j+1
        }
		return retrunNum;
	}
	
}
