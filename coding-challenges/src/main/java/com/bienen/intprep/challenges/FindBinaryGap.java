package com.bienen.intprep.challenges;

public class FindBinaryGap {

	public static void main(String[] args) {
		System.out.println(solution(1041));
		System.out.println(solution(32));
		System.out.println(solution(1));
		System.out.println(solution(0));
		System.out.println(solution(3));
		System.out.println(solution(15));

	}

	private static int solution(int N) {
		int highestGap = 0, currentGap = 0;

		char[] charStringArray = Integer.toBinaryString(N).toCharArray();
		boolean firstOneFound = false;
		int currentIndex = charStringArray.length - 1;
		while (currentIndex > 0) {

			if (charStringArray[currentIndex] == '1') {
				firstOneFound = true;
			}
			if ((charStringArray[currentIndex] == '1' && charStringArray[currentIndex - 1] == '0')
					|| (charStringArray[currentIndex] == '0' && charStringArray[currentIndex - 1] == '0'
							&& firstOneFound)) {
				currentGap++;
			} else if (charStringArray[currentIndex - 1] == '1') {
				if (currentGap > highestGap) {
					highestGap = currentGap;
				}
				currentGap = 0;
			}

			currentIndex--;
		}

		return highestGap;
	}

}
