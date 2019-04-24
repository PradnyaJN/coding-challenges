package com.bienen.intprep.challenges;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FindOddNumber {

	/**
	 * @param left 
	 * @param right
	 * @return list of all odd numbers between two given number l & r including.
	 */
	static List<Integer> oddNumbers(int left, int right) {
		return IntStream.range(left, right+1).filter(i ->  (i%2!=0)).collect(ArrayList::new,  ArrayList::add, ArrayList::addAll);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
bufferedWriter.write("Enter start and end numbers of range:");
bufferedWriter.flush();
		int l = Integer.parseInt(bufferedReader.readLine().trim());

		int r = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> res = oddNumbers(l, r);

		bufferedWriter.write(res.stream().map(Object::toString).collect(joining("\n")) + "\n");

		bufferedReader.close();
		bufferedWriter.close();
	}

}
