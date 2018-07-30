package com.xjldtc.leetcode.questionbank1;

import java.util.HashMap;
import java.util.Map;

public class TwoNumSum {

	public static void main(String[] args) {
		int[] bigNums = new int[100000];
		for (int i = 0; i < bigNums.length; i++) {
			bigNums[i] = i;
		}
		System.out.println(System.currentTimeMillis());
		int[] retrunNums = TwoNumSum.sumNum(bigNums, 195152);
		System.out.println(retrunNums[0] + "," + retrunNums[1]);
		System.out.println(System.currentTimeMillis());
		int[] retrunNums2 = TwoNumSum.hashSumNum(bigNums, 195152);
		System.out.println(retrunNums2[0] + "," + retrunNums2[1]);
		System.out.println(System.currentTimeMillis());
		int[] retrunNums3 = TwoNumSum.oneHashSumNum(bigNums, 195152);
		System.out.println(retrunNums3[0] + "," + retrunNums3[1]);
		System.out.println(System.currentTimeMillis());	
	}

	/**
	 * 暴力法 时间复杂度：O(n^2)， 对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素， 这将耗费 O(n)
	 * 的时间。因此时间复杂度为 O(n^2)。 空间复杂度：O(1).
	 */
	public static int[] sumNum(int[] numbers, int target) {
		int[] retrunNums = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			int a = numbers[i];
			for (int j = numbers.length - 1; j > i - 1; j--) {// 注意考虑数组是0开始，总数是实际数量
				int b = numbers[j];
				if (target == (a + b)) {
					retrunNums[0] = i;
					retrunNums[1] = j;
					return retrunNums;
				}
			}
		}
		throw new IllegalArgumentException("No two sum solution");

		// 使用减法 循环就用加法
		// for (int i = 0; i < nums.length; i++) {
		// for (int j = i + 1; j < nums.length; j++) {
		// if (nums[j] == target - nums[i]) {
		// return new int[] { i, j };
		// }
		// }
		// throw new IllegalArgumentException("No two sum solution");
		// }
	}

	/**
	 * 时间复杂度：O(n)， 我们把包含有 n 个元素的列表遍历两次.由于哈希表将查找时间缩短到 O(1),所以时间复杂度为 O(n)
	 * 空间复杂度：O(n)， 所需的额外空间取决于哈希表中存储的元素数量，该表中存储了 n个元素。
	 */
	public static int[] hashSumNum(int[] numbers, int target) {
		Map<Integer, Integer> makeMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			makeMap.put(numbers[i], i);
		}
		for (int i = 0; i < numbers.length; i++) {
			int mapKey = target - numbers[i];
			if (makeMap.containsKey(mapKey) && makeMap.get(mapKey) != i) {
				return new int[] { i, makeMap.get(mapKey) };
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	/**
	 * 时间复杂度：O(n)， 我们只遍历了包含有 n 个元素的列表一次。在表中进行的每次查找只花费 O(1) 的时间。
	 * 空间复杂度：O(n)， 所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储 n个元素。
	 */
	public static int[] oneHashSumNum(int[] numbers, int target) {
		Map<Integer, Integer> makeMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			makeMap.put(numbers[i], i);
			int mapKey = target - numbers[i];
			if (makeMap.containsKey(mapKey) && makeMap.get(mapKey) != i) {
				return new int[] { i, makeMap.get(mapKey) };
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}

}
