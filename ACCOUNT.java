package BEAN;

import java.sql.Timestamp;

public class ACCOUNT {
	public String UserName;
	public String AccountName;
	public String AccountPass;
	public String AccountEmail;

	public ACCOUNT(String accountName, String accountPass) {
		AccountName = accountName;
		AccountPass = accountPass;
	}
	
	public ACCOUNT(String accountName) {
		super();
		AccountName = accountName;
	}

	public ACCOUNT(String userName, String accountName, String accountPass, String accountEmail) {
		UserName = userName;
		AccountName = accountName;
		AccountPass = accountPass;
		AccountEmail = accountEmail;
	}

	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getAccountName() {
		return AccountName;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	public String getAccountPass() {
		return AccountPass;
	}
	public void setAccountPass(String accountPass) {
		AccountPass = accountPass;
	}
	public String getAccountEmail() {
		return AccountEmail;
	}
	public void setAccountEmail(String accountEmail) {
		AccountEmail = accountEmail;
	}
	
	
	
}
