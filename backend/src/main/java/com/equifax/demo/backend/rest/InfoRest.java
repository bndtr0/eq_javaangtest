package com.equifax.demo.backend.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import jakarta.ws.rs.core.Response;
import model.Dato;
import model.Usuario;

@RestController
public class InfoRest 
{
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/user")
	public ResponseEntity<Usuario> readUserInfo()
	{		
		Dato d = new Dato();
		Usuario u = new Usuario();
		u.setDatos(d);
		
		ResponseEntity<Usuario> re = null;
		re = ResponseEntity.status(200).header("Access-Control-Allow-Origin", "http://localhost:4200").body(u);
		
		return re;// "{'error': 'nodata'}";
	}
	
	@PostMapping("/user")
	public Usuario addUserInfo(
		@RequestBody(required = true) String reqBody
	)
	{
		System.out.println(reqBody);
		
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
