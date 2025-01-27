package com.equifax.demo.backend.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTest 
{
	@RequestMapping("/test")
	public String test()
	{
		return "OK";
	}
	
	@RequestMapping("/test2")
	public String test(
			@RequestParam(name = "param1", defaultValue = "nil")  String p1,
			@RequestParam(name = "param2", defaultValue = "nil2") String p2
	)
	{
		return "OK2: [" + p1 + "/" + p2 + "]";
	}

}
