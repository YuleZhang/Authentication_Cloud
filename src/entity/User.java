package entity;

public class User {
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", email=" + email + ", phoneNum=" + phoneNum
				+ ", status=" + status + ", systemSource=" + systemSource + ", registerDate=" + registerDate
				+ ", finalip=" + finalip + ", finalTime=" + finalTime + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getSystemSource() {
		return systemSource;
	}
	public void setSystemSource(String systemSource) {
		this.systemSource = systemSource;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getFinalip() {
		return finalip;
	}
	public void setFinalip(String finalip) {
		this.finalip = finalip;
	}
	public String getFinalTime() {
		return finalTime;
	}
	public void setFinalTime(String finalTime) {
		this.finalTime = finalTime;
	}

	public User(String userName, String password, String email, String phoneNum, String status, String systemSource,
			String registerDate, String finalip, String finalTime) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNum = phoneNum;
		this.status = status;
		this.systemSource = systemSource;
		this.registerDate = registerDate;
		this.finalip = finalip;
		this.finalTime = finalTime;
	}

	String userName;
	String password;
	String email;
	String phoneNum;
	String status;
	String systemSource;
	String registerDate;
	String finalip;
	String finalTime;
	
	
}
