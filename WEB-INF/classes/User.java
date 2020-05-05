package bank;
import java.util.*;
import java.io.Serializable;

public class User implements Serializable{

private static final long serialVersionUID = 223L;
private String firstName;
private String lastName;
private String userName;
private double accountID;
private String accountType;
private String password;
private List<Account> allUserAccount = new ArrayList<Account>();
public User(){

}

public void setFirstName(String firstN) {
	firstName = firstN;
}
public void setLastName(String lastN) {
	lastName = lastN;
}
public void setacccountID(double id) {
	accountID = id;
}
public void setUserName(String UserN){
	userName = UserN;
}
public void setacctType(String type) {
	accountType = type;
}
public void setPassword(String Password) {
	this.password = Password;
}

public void addAnotherAccount(Account toAdd){
	this.allUserAccount.add(toAdd);
}

public List<Account> getAllAccounts(){
	return allUserAccount;
}

public String getFirstName() {
	return firstName;
}
public String getLastName() {
	return lastName;
}
public String getUserName(){
	return userName;
}
public double getacctID() {
	return accountID;
}
public String getacctType() {
	return accountType;
}
public String getPassword() {
	return password;
}


}
