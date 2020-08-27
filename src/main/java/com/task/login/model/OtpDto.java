package com.task.login.model;

public class OtpDto {
	
	private String email;
	private int otp;

	public int getOtp() {
		return otp;
	}

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setOtp(int otp) {
		this.otp = otp;
	}


	@Override
	public String toString() {
		return "OtpDto [email=" + email + ", otp=" + otp + "]";
	}

	
}
