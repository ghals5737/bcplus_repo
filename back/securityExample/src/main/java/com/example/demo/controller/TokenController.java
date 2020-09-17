package com.example.demo.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GoogleToken;
import com.example.demo.dto.Member;
import com.example.demo.dto.UserRole;
import com.example.demo.dto.UserVO;
import com.example.demo.utils.GoogleUtils;
import com.example.demo.utils.TokenUtils;

import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Log4j2
public class TokenController {
	
	@Autowired
	private GoogleUtils googleUtils;
	
	@Autowired
	private TokenUtils tokenUtils;
	@GetMapping("/aaa")
	private String testNginx() {
		return "hello nginx";
	}
	@PostMapping("/token")	
	private Member getGoogleAcountByToken(@RequestBody GoogleToken googleToken,HttpServletResponse response) throws IOException, GeneralSecurityException {
		Member member=googleUtils.checkMember(googleToken);
		String token = tokenUtils.generateJwtToken(member);
	    response.addHeader("Authorization", "Bearer " + token);
		return member;
	}

}
