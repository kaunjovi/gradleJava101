package fun.n.games;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteSomeTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// TODO
	// You can start testing as individual unit test. alt+cmd+x T
	// But sometime you will have to run the whole set
	// And ensure that the previous tests are not broken.

	// TODO
	// Is there a Jenkins online that I can use. Build everytime I check in.

	// TODO
	// Run this with some pretty large set of words.
	// File is good. Should we do file? Should we do some stream?

	// How to get the colors to say how much of the code is tested?
	// It is the eclEmma eclipse plugin.
	// Just hit the colored button by the debug button.

	// Cheatlist
	// alt+cmd+3 - check in stuff.
	// log - to start the logger
	// deb - to put in a debug statement
	// perftest - to put some contiperf magic

	// @Rule
	// public ContiPerfRule i = new ContiPerfRule();

	// @Ignore
	@Test
	// @PerfTest(invocations = 1000, threads = 20)
	// @Required(max = 1200, average = 250)
	public void test() throws MalformedURLException, IOException {

		String aVeryLongString = fetchTaleOfTwoCities();

		String[] bunchOfWords = fetchAllWordsFrom(aVeryLongString);

		Map<String, Integer> wordCounter = countOccurancesOfEachWord(bunchOfWords);

		displayWordsWithCounts(wordCounter);

		displayWordsWithCountsSortedByCount(wordCounter);

		String theExpectedCounts = "{and=1, best=1, it=2, of=2, the=2, times=2, was=2, worst=1}";
		fail("Not yet implemented");
	}

	private void displayWordsWithCountsSortedByCount(Map<String, Integer> wordCounter) {
		// Sort the words by their frequency of occurrences

		// Trying to sort the collection by the count now.
		// TreeMap cant be sorted on values.
		// Lets put them in a sorted set and put a comparator to sort based on values
		// rather than keys.

		// TODO
		// Fix this. We are showing only one entry of any count. If there are multiple
		// words with same frequency
		SortedSet<Map.Entry<String, Integer>> wordCountSortedByCount = new TreeSet<Map.Entry<String, Integer>>(
				Comparator.comparing(Map.Entry::getValue));
		wordCountSortedByCount.addAll(wordCounter.entrySet());

		logger.debug("There are {} unique words.", wordCountSortedByCount.size());
		logger.debug("WORD, COUNT");
		logger.debug("===========");

		for (Iterator i = wordCountSortedByCount.iterator(); i.hasNext();) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) i.next();
			logger.debug("{}, {}", entry.getKey(), entry.getValue());
		}
	}

	private Map<String, Integer> countOccurancesOfEachWord(String[] bunchOfWords) {
		Map<String, Integer> wordCounter = new HashMap<String, Integer>();
		for (String word : bunchOfWords) {

			Integer count = wordCounter.get(word);
			if (count == null) {
				wordCounter.put(word, 1);
			} else {
				wordCounter.put(word, count + 1);
			}
		}
		return wordCounter;
	}

	private String[] fetchAllWordsFrom(String aVeryLongString) {
		// We need to fetch words from a string.
		// In a regular string you could probably just use " "
		// However, if your string is a novel, you will probably need to use regex and
		// find any white space.
		// String[] arrayOfWords = aVeryLongStringANovelPerhaps.split(" ");
		// aNovelAsAString.replaceAll(",$", "");

		String[] arrayOfWords = aVeryLongString.replaceAll("[!,?.();:\\”\\“]", "").toLowerCase().split("\\s+");
		return arrayOfWords;
	}

	private void displayWordsWithCounts(Map<String, Integer> wordCounter) {
		displayWordsWithTheirCountsSortedByWord(wordCounter, 10);
	}

	private void displayWordsWithTheirCountsSortedByWord(Map<String, Integer> wordCounter, int countOfRowsToDisplay) {
		TreeMap<String, Integer> wordCounterSortedByWord = new TreeMap<String, Integer>(wordCounter);

		logger.debug("There are {} unique words.", wordCounterSortedByWord.size());
		logger.debug("WORD, COUNT");
		logger.debug("===========");

		// If countOfRowsToDisplay is less than 1, then show all.
		// Else, show as many as requested.
		// You might have too many distinct words and not want to show them all.

		if (countOfRowsToDisplay < 1) {

			Set<String> keys = wordCounterSortedByWord.keySet();
			for (Iterator i = keys.iterator(); i.hasNext();) {
				String word = (String) i.next();
				Integer count = wordCounterSortedByWord.get(word);
				logger.debug("{}, {}", word, count);
			}
		} else {
			Set<String> keys = wordCounterSortedByWord.keySet();

			int counter = 0;

			for (Iterator i = keys.iterator(); i.hasNext() && counter < countOfRowsToDisplay;) {
				String word = (String) i.next();
				Integer count = wordCounterSortedByWord.get(word);
				logger.debug("{}, {}", word, count);
				counter++;
			}

		}

	}

	private String fetchTaleOfTwoCities() {

		// final String URL = "http://www.google.com";
		// That was great. But better to find a URL which spits out text file.
		// Preferably a novel.

		// final String URL = "http://www.gutenberg.org/files/57091/57091-0.txt";
		// This was fine. But the novel was small. We need a bigger one.
		final String URL = "http://www.gutenberg.org/files/98/98-0.txt";
		try {
			return fetchANovel(URL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			logger.debug("There was some error fetching data from {}. Falling back on hard coded text.", URL);

			StringBuffer aTaleOfTwoCities = new StringBuffer();
			aTaleOfTwoCities.append("It was the best of times,");
			aTaleOfTwoCities.append("it was the worst of times,");
			aTaleOfTwoCities.append("it was the age of wisdom,");
			aTaleOfTwoCities.append("it was the age of foolishness,");
			aTaleOfTwoCities.append("it was the epoch of belief,");
			aTaleOfTwoCities.append("it was the epoch of incredulity,");
			aTaleOfTwoCities.append("it was the season of Light,");
			aTaleOfTwoCities.append("it was the season of Darkness,");
			aTaleOfTwoCities.append("it was the spring of hope,");
			aTaleOfTwoCities.append("it was the winter of despair,");
			aTaleOfTwoCities.append("we had everything before us,");
			aTaleOfTwoCities.append("we had nothing before us,");
			return aTaleOfTwoCities.toString();
		}

	}

	private String fetchANovel(String URL) throws IOException, MalformedURLException {

		Scanner scanFromWebsite = new Scanner(new URL(URL).openStream(), "UTF-8");
		scanFromWebsite.useDelimiter("\\A");
		final String NOVEL = scanFromWebsite.next();

		scanFromWebsite.close();

		return NOVEL;
	}

	@Ignore
	@Test
	public void sortMapBasedOnValueTest() {
		// Create a map of word and their counts.
		// Put them in TreeMap so that they are naturally sorted by the words
		Map<String, Integer> wordCount = new TreeMap<String, Integer>();
		wordCount.put("but", 100);
		wordCount.put("all", 10);

		// Iterate over the map to confirm that the data is stored sorted by words.
		// This part is also working nicely and I can see that the ouput is sorted by
		// words.
		Set<String> words = wordCount.keySet();
		logger.debug("word, count");
		for (Iterator<String> itForWords = words.iterator(); itForWords.hasNext();) {
			String word = (String) itForWords.next();
			Integer count = wordCount.get(word);
			logger.debug("{}, {}", word, count);
		}

		// Trying to sort the collection by the count now.
		// TreeMap cant be sorted on values.
		// Lets put them in a sorted set and put a comparator to sort based on values
		// rather than keys.
		SortedSet<Map.Entry<String, Integer>> wordCountSortedByCount = new TreeSet<Map.Entry<String, Integer>>(
				Comparator.comparing(Map.Entry::getValue));
		wordCountSortedByCount.addAll(wordCount.entrySet());

		// This is NOT WORKING
		// The size is only 1. It should have been two.
		logger.debug("Size of sorted collection is {}", wordCountSortedByCount.size());

		fail("Not yet tested.");
	}

	@Ignore
	@Test
	public void testRegexToStripAllCommasAtTheEndOfWords() {
		String testString = "there is something fi,shy, going on here. Is it not?";

		String stringWithoutAnyCommasAtAll = testString.replaceAll(",", "");
		logger.debug(stringWithoutAnyCommasAtAll);

		String stringWithoutAnyCommasAtTheEndOfLine = testString.replaceAll(",$", "");
		logger.debug(stringWithoutAnyCommasAtTheEndOfLine);

		String stringWithoutAnyCommasAtTheEndOfAnyWordOfALine = testString.replaceAll(",([^,]*)$", "$1");
		logger.debug(stringWithoutAnyCommasAtTheEndOfAnyWordOfALine);

		String stringStrippedOfCertainCharacters = testString.replaceAll("[,?.]", "");
		logger.debug(stringStrippedOfCertainCharacters);

		fail("Not done yet");

	}

}
