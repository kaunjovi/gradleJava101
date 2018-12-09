package ku.ber.portfolio;

import java.math.BigDecimal;

public class TransactionRequest {

	private String rawTransaction;

	private String verb;
	private BigDecimal amount;
	private String ticker;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	private BigDecimal quantity;
	private BigDecimal rate;

	private boolean emptyTransaction = false;
	private boolean cashTransaction = false;
	private boolean securitiesTransaction = false;

	public static TransactionRequest createNewTransactionRequest(String transaction) {

		TransactionRequest transactionRequest = new TransactionRequest();
		transactionRequest.rawTransaction = transaction;

		if (transaction.trim().length() != 0) {

			String[] args = transaction.split(",");

			transactionRequest.setVerb(args[0].trim().toUpperCase());

			if (transactionRequest.isWithdrawlRequest() || transactionRequest.isDepositRequest()) {

				transactionRequest.cashTransaction = true;
				transactionRequest.amount = new BigDecimal(args[1].trim().toUpperCase());

			} else if (transactionRequest.isBuyRequest() || transactionRequest.isSellRequest()) {

				transactionRequest.securitiesTransaction = true;
				transactionRequest.ticker = args[1].trim().toUpperCase();
				transactionRequest.quantity = new BigDecimal(args[2].trim().toUpperCase());
				transactionRequest.rate = new BigDecimal(args[3].trim().toUpperCase());

			}
		} else {

			transactionRequest.setEmptyTransaction(true);

		}

		return transactionRequest;
	}

	public void setRawTransaction(String rawTransaction) {
		this.rawTransaction = rawTransaction;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setCashTransaction(boolean cashTransaction) {
		this.cashTransaction = cashTransaction;
	}

	public String getVerb() {
		return verb;
	}

	public boolean isCashTransaction() {
		return cashTransaction;
	}

	public String getRawTransaction() {
		return rawTransaction;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String toString() {
		return getRawTransaction();
	}

	public boolean isEmptyTransaction() {
		return emptyTransaction;
	}

	public void setEmptyTransaction(boolean emptyTransaction) {
		this.emptyTransaction = emptyTransaction;
	}

	public boolean isDepositRequest() {
		return this.getVerb().equals("DEPOSIT");
	}

	public boolean isBuyRequest() {
		return this.getVerb().equals("BUY");
	}

	public boolean isSellRequest() {
		return this.getVerb().equals("SELL");
	}

	public boolean isWithdrawlRequest() {
		return this.getVerb().equals("WITHDRAW");

	}

	public boolean isSecuritiesTransaction() {
		return securitiesTransaction;
	}

	public void setSecuritiesTransaction(boolean securitiesTransaction) {
		this.securitiesTransaction = securitiesTransaction;
	}

}
