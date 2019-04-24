package com.bienen.intprep.challenges;

public class PerfectSquareRoot {

	public static void main(String[] args) {
		System.out.println(solution(10, 30));
		System.out.println(solution(6000, 7000));

	}

	public static int solution(int A, int B) {
		if (A < 2 || B < 2)
			return 0;

		int nop = 0;

		A = (int) Math.ceil(Math.sqrt(A));
		B = (int) Math.floor(Math.sqrt(B));

		while (B >= A) {

			A = (int) Math.ceil(Math.sqrt(A));
			B = (int) Math.floor(Math.sqrt(B));
			nop = nop + 1;
		}
		return nop;
	}

}
