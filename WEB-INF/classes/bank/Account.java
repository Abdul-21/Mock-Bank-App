package bank;

import java.io.Serializable;

public class Account implements Serializable{
    private static final long serialVersionUID = 123L;
    private String CustomerName;
    private double initialDeposit;
    private double balance;
    private double CustomerID;
    private String accountType;

    public Account(){
        balance=0.00;
    }
    public double getBalance() {
        return balance;
    }

    public void setInitialDeposit(Double amount) {
        initialDeposit = amount;
    }

    public Double getInitialDeposit() {
        return initialDeposit;
    }

    public void setCustomerID(double customerID) {
        CustomerID = customerID;
    }

    public double getCustomerID() {
        return CustomerID;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public double withdraw(double amount){
        return balance-=amount;
    }

    public void setacctType(String type) {
        accountType = type;
    }

    public String getacctType() {
        return accountType;
    }

    public double deposit(double amount){
        return balance+=amount;
    }
}
