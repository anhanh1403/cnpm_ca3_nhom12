package BEAN;

import java.sql.Timestamp;

public class AccountActive {
	public String AccountName;
	public String AccountPass;
	public Timestamp  createTime;
	public Timestamp lastAccessTime;
	public String  createTimeStr;
	public String lastAccessTimeStr;
	public AccountActive(String accountName, String accountPass, Timestamp createTime, Timestamp lastAccessTime) {
		super();
		AccountName = accountName;
		AccountPass = accountPass;
		this.createTime = createTime;
		this.lastAccessTime = lastAccessTime;
	}
	
	public AccountActive(String accountName) {
		super();
		AccountName = accountName;
	}

	public AccountActive(String accountName, String accountPass, String createTimeStr, String lastAccessTimeStr) {
		super();
		AccountName = accountName;
		AccountPass = accountPass;
		this.createTimeStr = createTimeStr;
		this.lastAccessTimeStr = lastAccessTimeStr;
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
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Timestamp lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getLastAccessTimeStr() {
		return lastAccessTimeStr;
	}
	public void setLastAccessTimeStr(String lastAccessTimeStr) {
		this.lastAccessTimeStr = lastAccessTimeStr;
	}

}

