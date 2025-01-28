package com.equifax.demo.backend.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRest 
{
	@PostMapping
	public String login()
	{
		
		return "nologin";
	}
}
