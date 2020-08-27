package com.task.login.model;

public class TokenDto {
	
	private String token;
	private String email;
	private int userid;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "TokenDto [token=" + token + ", email=" + email + ", userid=" + userid + "]";
	}

	
}
