package ku.ber.portfolio;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CashAndSecuritiesPortfolio extends CashOnlyPortfolio {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public BigDecimal transact(String transaction) {

		final TransactionRequest transactionRequest = TransactionRequest.createNewTransactionRequest(transaction);

		if (!transactionRequest.isEmptyTransaction()) {
			if (transactionRequest.isSecuritiesTransaction()) {
				executeSecuritiesTransaction(transactionRequest);
			} else {
				return super.transact(transaction);
			}

		} else {
			logger.debug("This is an empty line. Ignoring [{}].", transactionRequest);

		}

		return fetchLiquidCashValue();
	}

	private void executeSecuritiesTransaction(TransactionRequest transactionRequest) {
		// TODO Auto-generated method stub

	}

}
