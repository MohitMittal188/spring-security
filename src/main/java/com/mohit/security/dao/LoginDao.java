package com.mohit.security.dao;

public interface LoginDao {
	void saveLoginOtp(String email,Integer otp);
	Integer getOtp(String email);
}
