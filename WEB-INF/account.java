public class account {
    private String CustomerName;
    private double balanace;
    private long CustomerID;

    public account(){
        balanace=0.00;
    }
    public double getBalanace() {
        return balanace;
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
        return balanace-=amount;
    }

    public double deposit(double amount){
        return balanace+=amount;
    }
}
