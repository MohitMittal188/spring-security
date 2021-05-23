package com.mohit.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.security.dao.LoginDao;

@Service
public class LoginService {

	@Autowired
	LoginDao loginDao;
	
	public void saveLoginOtp(String email,Integer otp){
		loginDao.saveLoginOtp(email, otp);
	}
	
	public Integer getOtp(String email) {
		return loginDao.getOtp(email);
	}
}
