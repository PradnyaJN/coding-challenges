package com.bienen.intprep.challenges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FindLargestPossibleNumber {

	public static void main(String[] args) {
		System.out.println(FindLargestPossibleNumber.solution(109));
		System.out.println(FindLargestPossibleNumber.solution(9198798));
	}
	

	public static int solution(int N) {

		List<Integer> intList = new ArrayList<Integer>();
		while (N / 10 >= 1) {
			intList.add(N % 10);
			N = N / 10;
		}
		intList.add(N % 10);
		intList.sort(Comparator.reverseOrder());
		int number = 0;
		int noOfDigits = intList.size();
		
		for (Integer integer : intList) {
			number = number + (int) Math.pow(10, noOfDigits - 1) * integer;
			noOfDigits = noOfDigits - 1;
		}

		return number;
	}

}
