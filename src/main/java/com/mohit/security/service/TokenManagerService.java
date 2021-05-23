package com.mohit.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class TokenManagerService {

	Set<String> tokens = new HashSet<>();
	
	public void addToken(String token) {
		tokens.add(token);
	}
	
	public boolean checkTokenVaild(String token) {
		return tokens.contains(token);
	}
}
