package Bank;  

public class User{

private static final long serialVersionUID = -2284079786212849611L;
private String firstName;
private String lastName;
private String accountID;
private String accountType;
private String password;

public User(){

}

public void setFirstName(String firstN) {
	firstName = firstN;
}
public void setLastName(String lastN) {
	lastName = lastN;
}
public void setacccountID(String id) {
	accountID = id;
}
public void setacctType(String type) {
	accountType = type;
}
public void setpassWord(String Password) {
	this.password = Password;
}

public String getFirstName() {
	return firstName;
}
public String getLastName() {
	return lastName;
}
public String getacctID() {
	return accountID;
}
public String getacctType() {
	return accountType;
}
public String getPassword() {
	return password;
}


}
