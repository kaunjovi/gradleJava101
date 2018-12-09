package ku.ber.portfolio;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CashOnlyPortfolio implements Portfolio {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private BigDecimal cash = new BigDecimal("0");

	@Override
	public BigDecimal transact(String transaction) {

		final TransactionRequest transactionRequest = TransactionRequest.createNewTransactionRequest(transaction);

		if (!transactionRequest.isEmptyTransaction()) {
			if (transactionRequest.isCashTransaction()) {
				executeCashTransaction(transactionRequest);
			} else {
				logger.debug("Not a valid transaction. Ignoring [{}].", transactionRequest);
			}

		} else {
			logger.debug("This is an empty line. Ignoring [{}].", transactionRequest);

		}

		return fetchLiquidCashValue();
	}

	protected void executeCashTransaction(TransactionRequest transactionRequest) {

		// logger.debug("{} / {}", transactionRequest.getRawTransaction(),
		// transactionRequest.getVerb());

		if (transactionRequest.isDepositRequest()) {

			cash = cash.add(transactionRequest.getAmount());
			logger.debug("Added {} to portfolio. Now the value of cash is {}.", transactionRequest.getAmount(), cash);

		} else if (transactionRequest.isWithdrawlRequest()) {

			BigDecimal amount = transactionRequest.getAmount();
			if (cash.compareTo(amount) > 0) {

				cash = cash.subtract(amount);
				logger.debug("Subtracted {} from portfolio. Now the value of cash is {}.", amount, cash);
			} else {
				logger.debug("Cash in hand is {}. Attempted withdrawl of {}. Transaction cancelled.", cash, amount);
			}
		} else {
			logger.debug("An unsupported transaction was attempted [{}]", transactionRequest);
		}
	}

	@Override
	public BigDecimal fetchLiquidCashValue() {

		return cash;
	}

}
