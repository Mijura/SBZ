package com.sbz.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sbz.dto.LoginDTO;
import com.sbz.dto.RegisterDTO;
import com.sbz.dto.ResponseDTO;
import com.sbz.models.User;
import com.sbz.services.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) throws IllegalArgumentException, UnsupportedEncodingException {
		
		User user = service.findOneByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
		
		if(user==null){
			return new ResponseEntity<ResponseDTO>(
					new ResponseDTO("Wrong username and password combination"),HttpStatus.NOT_FOUND);
		}
		
		Algorithm algorithm = Algorithm.HMAC256("sbz");
	    String token = JWT.create()
	        .withClaim("username", user.getUsername())
	        .withClaim("fname", user.getFirstName())
	        .withClaim("lname", user.getLastName())
	        .withClaim("registered", user.getRegistered())
	        .sign(algorithm);
		return new ResponseEntity<ResponseDTO>(
				new ResponseDTO(token),HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseDTO> register(@RequestBody RegisterDTO registerDTO) throws IllegalArgumentException, UnsupportedEncodingException {
		
		User user = service.findOneByUsername(registerDTO.getUsername());
		
		if(user!=null){
			return new ResponseEntity<ResponseDTO>(
				new ResponseDTO("Username already exist!"),HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<ResponseDTO>(
				new ResponseDTO(),HttpStatus.OK);
		
	}
}
