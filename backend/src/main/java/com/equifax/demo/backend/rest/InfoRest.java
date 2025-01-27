package com.equifax.demo.backend.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.ws.rs.core.Response;
import model.Dato;
import model.Usuario;

@RestController
public class InfoRest 
{
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/user")
	public Usuario readUserInfo()
	{
		Dato d = new Dato();
		Usuario u = new Usuario();
		u.setDatos(d);
		
		return u;// "{'error': 'nodata'}";
	}
	
	@PostMapping("/user1")
	public Usuario addUserInfo()
	{
		Dato d = new Dato();
		Usuario u = new Usuario();
		u.setDatos(d);
		
		String sql = "INSERT INTO students (name, email) VALUES ("
                + "'Nam Ha Minh', 'nam@codejava.net')";
         
        int rows = jdbcTemplate.update(sql);
        
        if (rows > 0) 
        {
            System.out.println("A new row has been inserted.");
            return u;
        }
        else
        {
        	return null;
        }
	}
	
	
}
