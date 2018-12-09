package ku.ber.portfolio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDataSet {

	private final static Logger logger = LoggerFactory.getLogger(TestDataSet.class);

	public List<String> transactions = new ArrayList<String>();
	BigDecimal expectedCashInPortfolio = new BigDecimal("0.00");

}
