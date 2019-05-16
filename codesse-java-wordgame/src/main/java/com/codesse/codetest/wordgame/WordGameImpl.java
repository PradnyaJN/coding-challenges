package com.codesse.codetest.wordgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * This is the shell implementation of the WordGame interface. It is the class
 * that you should focus on when developing your solution to the Challenge.
 */
public class WordGameImpl implements WordGame {

	Map<String, WordEntry> wordsSubmitted = new ConcurrentHashMap<String, WordGameImpl.WordEntry>(10);
	Map<String, Player> playerMap = new ConcurrentHashMap<String, WordGameImpl.Player>();
	List<String> ledgerBoard = new LinkedList<String>();

	private String startingString;
	private ValidWords validWords;
	Map<Character, Integer> charmapStartString = new HashMap<Character, Integer>();

	public WordGameImpl(String startingSetOfWords, ValidWords validWords) {
		startingString = startingSetOfWords;
		charmapStartString = createCharMap(startingString);
		this.validWords = validWords;
	}

	@Override
	public String getPlayerNameAtPosition(int position) {
		if (ledgerBoard == null || ledgerBoard.size() == 0 || position > ledgerBoard.size() - 1)
			return null;
		String word = ledgerBoard.get(position);
		WordEntry entry = wordsSubmitted.get(word);
		return entry.players.get(0).playerName;
	}

	@Override
	public String getWordEntryAtPosition(int position) {
		if (ledgerBoard == null || ledgerBoard.size() == 0 || position > ledgerBoard.size() - 1)
			return null;
		return ledgerBoard.get(position);
	}

	@Override
	public Integer getScoreAtPosition(int position) {
		if (ledgerBoard == null || ledgerBoard.size() == 0 || position > ledgerBoard.size() - 1)
			return null;
		return ledgerBoard.get(position).length();
	}

	@Override
	public synchronized int submitWord(String playerName, String word) {
		if (!ledgerBoard.contains(word) && !isValidEntry(word)) {
			return 0;
		}
		System.out.println(playerName + " submitted  word " + word);
		updateLedgerBoard(word);

		Player player = playerMap.computeIfAbsent(playerName, (t) -> new Player(t));
		PlayerEntry entry = player.create(word);

		WordEntry wordEntry = wordsSubmitted.computeIfAbsent(word, (t) -> new WordEntry(t));
		wordEntry.submit(entry);
		System.out.println("LedgerBoard:" + ledgerBoard.toString());
		return wordEntry.getScore();
	}

	private void updateLedgerBoard(String word) {
		if (!ledgerBoard.contains(word)) {
			int index = IntStream.range(0, ledgerBoard.size()).filter(i -> ledgerBoard.get(i).length() < word.length())
					.findFirst().orElseGet(() -> (ledgerBoard.size() == 0) ? 0 : ledgerBoard.size() - 1);
			System.out.println("Index" + index);
			ledgerBoard.add(index, word);

		}
	}

	private boolean isValidEntry(String word) {
		return isWordMatchesWithStartingString(word) && validWords.contains(word);
	}

	private boolean isWordMatchesWithStartingString(String word) {
		Map<Character, Integer> charmap = createCharMap(word);
		return charmap.entrySet().stream().noneMatch(valueEntry -> (!charmapStartString.containsKey(valueEntry.getKey())
				|| charmapStartString.get(valueEntry.getKey()).compareTo(valueEntry.getValue()) < 0));
	}

	private Map<Character, Integer> createCharMap(String word) {
		Map<Character, Integer> charToCountMap = new HashMap<Character, Integer>();

		for (char charstart : word.toCharArray()) {
			charToCountMap.merge(charstart, 1, (k, v) -> (v == null) ? 1 : v + 1);

		}
		return charToCountMap;
	}

	class Player {
		String playerName;
		List<PlayerEntry> validEntries = new ArrayList<PlayerEntry>();
		int score;

		public Player(String playerName) {
			super();
			this.playerName = playerName;
		}

		public int getScore() {
			return (int) validEntries.stream().map(x -> x.word.chars()).count();
		}

		public void addValidEntry(PlayerEntry validWord) {
			this.validEntries.add(validWord);
		}

		public PlayerEntry create(String word) {
			return new PlayerEntry(this, word);
		}
	}

	class PlayerEntry {
		Player player;
		String word;

		public PlayerEntry(Player playerName, String word2) {
			this.player = playerName;
			this.word = word2;
		}

		public final Player getPlayer() {
			return player;
		}

		public final void setPlayer(Player player) {
			this.player = player;
		}

		public final String getWord() {
			return word;
		}

		public final void setWord(String word) {
			this.word = word;
		}

	}

	class WordEntry {
		final String word;
		List<Player> players = new ArrayList<WordGameImpl.Player>();
		final int score;

		public void submit(PlayerEntry playerEntry) {

			if (!players.contains(playerEntry.getPlayer())) {
				this.players.add(playerEntry.getPlayer());
				playerEntry.getPlayer().addValidEntry(playerEntry);
			}
		}

		public final List<Player> getPlayers() {
			return players;
		}

		public final int getScore() {
			return score;
		}

		public WordEntry(String word) {
			super();
			this.word = word;
			this.score = word.length();
		}

	}

}
