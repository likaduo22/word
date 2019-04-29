package cn.likaduo.po;

public class UserPo {
	
	private String usernanme;
	private String password;
	
	
	public UserPo() {
		super();
	}
	
	
	public String getUsernanme() {
		return usernanme;
	}
	public void setUsernanme(String usernanme) {
		this.usernanme = usernanme;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "UserPo [usernanme=" + usernanme + ", password=" + password
				+ ", getUsernanme()=" + getUsernanme() + ", getPassword()="
				+ getPassword() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	

}
