package fun.n.games.portfolio;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDataForPortfolio {

	private final static Logger logger = LoggerFactory.getLogger(TestDataForPortfolio.class);

	public List<String> transactions = new ArrayList<String>();
	BigDecimal expectedCashInPortfolio = new BigDecimal("0.00");

	public static List<TestDataForPortfolio> createTestDataSetFromCSV(String testDataFileName) throws IOException {

		List<TestDataForPortfolio> testDataSetsForPortfolio = new ArrayList<TestDataForPortfolio>();

		List<String> transactions = new ArrayList<String>();
		// depositOnlyTransactions.add("DEPOSIT, 100.00");
		// depositOnlyTransactions.add("DEPOSIT, 100.00");

		try (BufferedReader br = Files.newBufferedReader(Paths.get(testDataFileName), StandardCharsets.US_ASCII)) {
			String transaction;
			while ((transaction = br.readLine()) != null) {
				logger.debug(transaction);
				if (!(transaction.startsWith("#"))) {
					transactions.add(transaction);
				} else {

					String[] args = transaction.split(",");
					if (args[0].trim().toUpperCase().equals("#TESTFORCASH")) {
						BigDecimal expectedCashInPortfolio = new BigDecimal(args[1].trim().toUpperCase());

						TestDataForPortfolio testDataForPortfolio = new TestDataForPortfolio();
						testDataForPortfolio.transactions = transactions;
						testDataForPortfolio.expectedCashInPortfolio = expectedCashInPortfolio;
						testDataSetsForPortfolio.add(testDataForPortfolio);
						transactions = new ArrayList<String>();

					}

				}
			}
		}

		return testDataSetsForPortfolio;
	}

}
