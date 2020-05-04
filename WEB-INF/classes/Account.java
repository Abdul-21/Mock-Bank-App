package bank;

import java.io.Serializable;

public class Account implements Serializable{
    private static final long serialVersionUID = 123L;
    private String CustomerName;
    private double balance;
    private long CustomerID;

    public Account(){
        balance=0.00;
    }
    public double getBalance() {
        return balance;
    }

    public void setCustomerID(long customerID) {
        CustomerID = customerID;
    }

    public long getCustomerID() {
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

    public double deposit(double amount){
        return balance+=amount;
    }
}
