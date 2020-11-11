package com.example.project309.app;

/**
 * Shortcut class to distinguish different account types
 *
 * @author Ryan Hickok
 */
public enum AccountType {

    /**
     * String values of each account type
     */
    CUSTOMER_DELIVERER_ACCOUNT("CustomerDeliverer"), MANAGER_ACCOUNT("Manager"), ADMIN_ACCOUNT("Admin"), DINING_ACCOUNT("Dining");

    public final String accountType;


    private AccountType(String s){

        this.accountType = s;

    }

    public String getAccountType(){

        return accountType;

    }
}
