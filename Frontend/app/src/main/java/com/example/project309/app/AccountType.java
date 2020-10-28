package com.example.project309.app;

public enum AccountType {

    CUSTOMER_DELIVERER_ACCOUNT("CustomerDeliverer"), MANAGER_ACCOUNT("Manager"), ADMIN_ACCOUNT("Admin");

    public final String accountType;


    private AccountType(String s){

        this.accountType = s;

    }

    public String getAccountType(){

        return accountType;

    }
}
