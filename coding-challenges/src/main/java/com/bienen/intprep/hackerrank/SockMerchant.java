package com.bienen.intprep.hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class SockMerchant {
	private static final Scanner scanner = new Scanner(System.in);
	static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = scanner.nextInt();

		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		int[] ar = new int[n];
		String[] arItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		for (int i = 0; i < n; i++) {
			int arItem = Integer.parseInt(arItems[i]);
			ar[i] = arItem;
		}
		int result = sockMerchant(n, ar);
		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.close();

		scanner.close();
	}

	// Complete the sockMerchant function below.
	static int sockMerchant(int n, int[] ar) {
		if (n <= 1) {
			return 0;
		}
		List<Integer> intList = Arrays.stream(ar).boxed().collect(Collectors.toList());
		Set<Integer> set = new HashSet<Integer>(intList);
		int count = 0;

		for (Integer i : set) {
			int paircount = (int) intList.stream().filter(x -> (x == i)).count();
			count += paircount / 2;
		}
		return count;

	}

}
