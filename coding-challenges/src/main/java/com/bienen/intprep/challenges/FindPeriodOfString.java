package com.bienen.intprep.challenges;

public class FindPeriodOfString {

	/*
	 * A non-empty zero-indexed string S consisting of Q characters is given. The
	 * period of this string is the smallest
	 * 
	 * positive integer P such that:
	 * 
	 * P ≤ Q / 2 and S[K] = S[K+P] for 0 ≤ K < Q − P.
	 * 
	 * For example, 7 is the period of “pepsicopepsicopep”. A positive integer M is
	 * the binary period of a positive integer N if M is the period of the binary
	 * representation of N.
	 * 
	 * For example, 1651 has the binary representation of "110011100111". Hence, its
	 * binary period is 5. On the other hand, 102 does not have a binary period,
	 * because its binary representation is “1100110” and it does not have a period.
	 * 
	 * Consider above scenarios & write a function in Java which will accept an
	 * integer N as the parameter. Given a positive integer N, the function returns
	 * the binary period of N. The function should return −1 if N does not have a
	 * binary period.
	 */

	public static void main(String[] args) {
		System.out.println(solution("CodilityCodilityCod"));// 8
		System.out.println(solution("abcdabcdefgh"));// -1
		System.out.println(solution("xyz12xyz12"));// 5
		System.out.println(solution("xxx11xxx11xxx11xxx11"));// 5
		System.out.println(solution("11xx1111xx1111xx1111xx11"));// 6
		System.out.println(solution("XXXXXXXX"));// 1


		System.out.println(solution(convertIntegerToBinary(955)));// 4
		System.out.println(solution(convertIntegerToBinary(1651)));// 5
		System.out.println(solution(convertIntegerToBinary(102)));// -1

//		convertIntegerToBinary(0);
//		convertIntegerToBinary(1);
//		convertIntegerToBinary(2);
//		convertIntegerToBinary(3);
//		convertIntegerToBinary(4);
//		convertIntegerToBinary(955);

	}

	private static int solution(String input) {
		System.out.print("Period of String " + input + " : ");
		int period = -1;
		char[] inputArray = input.toCharArray();
		for (int i = 1; i <= inputArray.length / 2; i++) {
			boolean match = true;
			// System.out.println("**********Round: " + i);
			for (int j = 0; j < inputArray.length && i + j < inputArray.length; j++) {
				if (inputArray[j] != inputArray[i + j]) {
					match = false;
					// System.out.println("Not Match: " + inputArray[j] + " " + inputArray[j + i]);
					break;
				}
//				else {
//					System.out.println("Match: " + inputArray[j] + " " + inputArray[j + i]);
//				}

			}
			if (match) {
				period = i;
				// System.out.println("Match at : " + i);
				break;
			}
		}

		return period;

	}

	static String convertIntegerToBinary(int n) {
		String binary = "";
//		System.out.print(n + ":");
		while (n > 0) {
			binary = n % 2 + binary;
			n /= 2;
		}
//		System.out.println(binary);

		return binary;
	}

}
