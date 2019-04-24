package com.bienen.intprep.challenges;

public class SmallestInteger {

	public static void main(String[] args) {

		System.out.print(solution(156));
		System.out.print(solution(15));
		System.out.print(solution(1));

		System.out.print(solution(0));

		System.out.print(solution(1563435));
		System.out.print(solution(156555555));
		System.out.print(solution(2333333));

	}

	public static int solution(int N) {

		double power = Math.floor(Math.log10(N));
		System.out.println("\n********************");
		System.out.println(N);
		System.out.println("No of 0s: " +  power);
		Double doubleres = Math.pow(10, power);
		System.out.print("Smallest Integer:");
		return doubleres.intValue();
	}

}
