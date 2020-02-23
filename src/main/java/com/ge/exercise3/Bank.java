package com.ge.exercise3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private static final Logger logger = LogManager.getLogger(Bank.class);
    private Map<String, Account> accountMap;

    public Bank() {
        accountMap = new HashMap<String,Account>();
    }

    public Account getAccount(String accountNumber) {
        return accountMap.get(accountNumber);
    }

    public void addAccount(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    public void depositToAccount(String accountNumber, float amount) {
        getAccount(accountNumber).deposit(amount);
    }

    public void withdrawFromAccount(String accountNumber, float amount) {
        String accType=getAccount(accountNumber).getAccountType();
        if(accType.equals("Checking") && (amount > 100.0f))
        	logger.info("Checking account cannot be overdrawn by more than $100");
        
        if(accType.equals("Savings") && (amount > getAccount(accountNumber).getBalance()))
        		logger.info("Amount to be withdrawn is more than the exsisting balance");
        else
        	getAccount(accountNumber).withdraw(amount);
    }

    public int numAccounts() {
        return accountMap.size();
    }
    public float getSumOfCurrentHoldings() {
    	float sum = 0.0f;
    	for(Account acc: accountMap.values()) {
    		sum+=acc.getBalance();
    	}
    	return sum;
    }
    public String predict() {
    	float sumOfInterestPaid = 0.0f;
    	float sumOfMonthlyFees = 0.0f;
    	for(Account acc: accountMap.values()) {
    		sumOfInterestPaid += acc.getInterestPaid();
    		sumOfMonthlyFees += acc.getMonthlyFee();
    	}
    	if(sumOfInterestPaid > sumOfMonthlyFees)
    		return "Loss";
    	else if(sumOfInterestPaid < sumOfMonthlyFees)
    		return "Profit";
    	else
    		return "No profit no loss";
    }
}
