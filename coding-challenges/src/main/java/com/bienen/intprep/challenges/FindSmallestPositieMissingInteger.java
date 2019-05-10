package com.bienen.intprep.challenges;

import java.util.Arrays;

public class FindSmallestPositieMissingInteger {

	public static void main(String[] args) {

		int[] inputArray = { 1, 3, 6, 4, 1, 2 };

		int[] inputArray1 = { -3, -6, -4, 1, 2 };

		System.out.println(solution(inputArray));
		System.out.println(solution(inputArray1));

	}

	public static int solution(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		A = Arrays.stream(A).distinct().sorted().toArray();

		if (A[A.length - 1] < 0) {
			return 1;
		}
		for (int i = 0; i < A.length; i++) {
			if (i < A.length - 1) {
				if (A[i] < 0 && A[i + 1] > 0 && A[i + 1] - A[i] > 2) {
					return 1;
				}
				if (A[i] > 0 && A[i + 1] > 0 && A[i + 1] - A[i] > 1) {
					return A[i] + 1;
				}

				
			} else {
				return A[i] + 1;
			}
		}
		return 1;
	}

}
