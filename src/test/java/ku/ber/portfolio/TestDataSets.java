package ku.ber.portfolio;

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

public class TestDataSets {

	private final static Logger logger = LoggerFactory.getLogger(TestDataSets.class);

	public static List<TestDataSet> generateTestDataSets(String testDataFileName) throws IOException {

		List<TestDataSet> testDataSets = new ArrayList<TestDataSet>();

		List<String> transactions = new ArrayList<String>();

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

						TestDataSet testDataForPortfolio = new TestDataSet();
						testDataForPortfolio.transactions = transactions;
						testDataForPortfolio.expectedCashInPortfolio = expectedCashInPortfolio;
						testDataSets.add(testDataForPortfolio);
						transactions = new ArrayList<String>();

					}

				}
			}
		}

		return testDataSets;
	}

	public static boolean runTestTransactions(Portfolio myPortfolio, TestDataSet testDataForPortfolio) {
		BigDecimal expectedCashInPortfolio = testDataForPortfolio.expectedCashInPortfolio;

		BigDecimal cashInPortfolio = new BigDecimal("0.0");
		for (String transaction : testDataForPortfolio.transactions) {
			cashInPortfolio = myPortfolio.transact(transaction);
		}

		logger.debug("The portfolio has {} in liquid cash. It is expected to have {}.", cashInPortfolio,
				expectedCashInPortfolio);

		return cashInPortfolio.compareTo(expectedCashInPortfolio) == 0;
	}

}
