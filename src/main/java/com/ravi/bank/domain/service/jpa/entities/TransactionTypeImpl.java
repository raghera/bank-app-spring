/**
 * 
 */
package com.ravi.bank.domain.service.jpa.entities;


/**
 * 
 * Implementation of TransactionTypes Data Domain Object
 * 
 * @author Ravi Aghera
 *
 */
public final class TransactionTypeImpl implements TransactionType {

	private Long transactionTypeId;
	private String transactionTypeName;
	//May want to add a description
	
	@Override
	public Long getTransactionTypeId() {
		return transactionTypeId;
	}

	@Override
	public void setTransactionTypeId(final Long transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	@Override
	public String getTransactionTypeName() {
		return transactionTypeName;
	}

	@Override
	public void setTransactionTypeName(final String transTypeName) {
		this.transactionTypeName = transTypeName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransationTypeImpl [transactionTypeId=");
		builder.append(transactionTypeId);
		builder.append(", transactionTypeName=");
		builder.append(transactionTypeName);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
