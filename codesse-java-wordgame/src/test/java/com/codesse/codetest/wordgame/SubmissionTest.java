package com.codesse.codetest.wordgame;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Copyright (C) 2015 Codesse. All rights reserved.
 * ••••••••••••••••••••••••••••••••••••••••••••••••
 */
public class SubmissionTest {

	static ValidWords validWords;
	WordGame service;

	@BeforeClass
	public static void oneTimeSetUp() {
		validWords = new ValidWordsImpl();
	}

	@Before
	public void setUp() throws Exception {
		service = new WordGameImpl("areallylongword", validWords);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testSimpleSubmission() {
		assertEquals(3, service.submitWord("player1", "all"));
		assertEquals(4, service.submitWord("player2", "word"));
		assertEquals(0, service.submitWord("player3", "tale"));
		assertEquals(0, service.submitWord("player4", "glly"));
		assertEquals(6, service.submitWord("player5", "woolly"));
		assertEquals(0, service.submitWord("player6", "adder"));
		assertEquals(4, service.submitWord("player1", "word"));
		assertEquals("player5", service.getPlayerNameAtPosition(0));
		assertEquals(new Integer(6), service.getScoreAtPosition(0));
		assertEquals("woolly", service.getWordEntryAtPosition(0));
	}

	@Test
	public void testThreadSubmission() throws Exception {

		Thread t1 = new Thread(() -> {

			service.submitWord("player1", "all");
			service.submitWord("player1", "adder");
			service.submitWord("player1", "word");
			service.submitWord("player1", "really");
			service.submitWord("player1", "woolly");

		});

		Thread t2 = new Thread(() -> {

			service.submitWord("player2", "really");
			service.submitWord("player2", "all");
			service.submitWord("player2", "woolly");
			service.submitWord("player2", "adder");
		});

		Thread t3 = new Thread(() -> {

			service.submitWord("player3", "all");
			service.submitWord("player3", "really");
			service.submitWord("player3", "woolly");
		});

		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();

		assertEquals("really", service.getWordEntryAtPosition(0));
		assertEquals("woolly", service.getWordEntryAtPosition(1));
		assertEquals("word", service.getWordEntryAtPosition(2));
		assertEquals("all", service.getWordEntryAtPosition(3));

		assertEquals(new Integer(6), service.getScoreAtPosition(0));
		assertEquals(new Integer(6), service.getScoreAtPosition(1));
		assertEquals(new Integer(4), service.getScoreAtPosition(2));
		assertEquals(new Integer(3), service.getScoreAtPosition(3));

		assertEquals("player3", service.getPlayerNameAtPosition(0));

	}

}
