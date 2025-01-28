package com.equifax.demo.backend.rest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;
import org.yaml.snakeyaml.util.Tuple;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.core.Response;
import model.Dato;
import model.Usuario;
import model.UsuarioExcel;

@RestController
public class InfoRest 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/user")
	public ResponseEntity<String> readUserInfo()
	{			
		String sql = "select * from Usuario";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		
		LinkedList<Usuario> lus = new LinkedList<Usuario>();
		while(rs.next())
		{
			Usuario us = new Usuario();
			us.setNombre_input(rs.getString("nombre_input"));
			us.setRut_input(rs.getString("rut_input"));
			
			Dato da = new Dato();
			da.setCampo1(rs.getString("campo1"));
			da.setCampo2(rs.getString("campo2"));
			da.setCampo3(rs.getInt("campo3"));
			us.setDatos(da);
			
			lus.add(us);
		}
     
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonOutput = "";
		try {
			jsonOutput = objectMapper.writeValueAsString(lus);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println(jsonOutput);		
		
		ResponseEntity<String> re = null;
		re = ResponseEntity.status(200).header("Access-Control-Allow-Origin", "http://localhost:4200").contentType(MediaType.APPLICATION_JSON).body(jsonOutput);
		
		return re;
	}
	
	@PostMapping("/user")
	@CrossOrigin(origins = "http://localhost:4200/")
	public ResponseEntity<Usuario> addUserInfo(
		@RequestBody(required = true) String reqBody
	)
	{
		int rows = 0;
		System.out.println(reqBody);
		
		List<UsuarioExcel> listUe = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			 listUe = objectMapper.readValue(reqBody, new TypeReference<List<UsuarioExcel>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(UsuarioExcel ue: listUe)
		{	
			String sql = "insert into Usuario (nombre_input, rut_input, campo1, campo2, campo3) values (?, ?, ?, ?, ?)";
			Object[] params = new Object[5];
			params[0] = ue.getNombre();
			params[1] = ue.getRut();
			params[2] = ue.getInfo1();
			params[3] = ue.getInfo2();
			params[4] = ue.getInfo3();
         
			rows = jdbcTemplate.update(sql, params);
		}
		
        if (rows > 0) 
        {
            System.out.println("A new row has been inserted.");
            return ResponseEntity.status(200).body(null);
        }
        else
        {
        	return ResponseEntity.status(400).body(null);
        }
	}
	
	
}
