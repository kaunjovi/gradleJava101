package fun.n.games;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteSomeTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	// @Ignore
	@Test
	// @PerfTest(invocations = 1000, threads = 20)
	// @Required(max = 1200, average = 250)
	public void test() {

		// TODO
		// You can start testing as individual unit test. alt+cmd+x T
		// But sometime you will have to run the whole set
		// And ensure that the previous tests are not broken.

		// TODO
		// Is there a Jenkins online that I can use. Build everytime I check in.

		// Cheatlist
		// alt+cmd+3 - check in stuff.
		// log - to start the logger
		// deb - to put in a debug statement
		// perftes - to put some contiperf magic

		String aVeryLongStringANovelPerhaps = "it was the best of times and it was the worst of times";
		String theExpectedCounts = "{and=1, best=1, it=2, of=2, the=2, times=2, was=2, worst=1}";

		// Lets do some counting
		String[] arrayOfWords = aVeryLongStringANovelPerhaps.split(" ");
		Map<String, Integer> wordCounter = new HashMap<String, Integer>();
		for (String word : arrayOfWords) {

			Integer count = wordCounter.get(word);
			if (count == null) {
				wordCounter.put(word, 1);
			} else {
				wordCounter.put(word, count + 1);
			}
		}

		// Lets see how much did we get as counts.
		TreeMap<String, Integer> sortedWordCounter = new TreeMap<String, Integer>(wordCounter);
		String theCounts = sortedWordCounter.toString();

		logger.debug("These are the counts {}", theCounts);

		assertEquals(theExpectedCounts, theCounts);
		// fail("Not yet implemented");
	}

}
