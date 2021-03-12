package com.devsuperior.dslearn.resources;

import com.devsuperior.dslearn.dtos.UserDTO;
import com.devsuperior.dslearn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable Long id){
		UserDTO dto = service.findById(id); 
		return ResponseEntity.ok().body(dto);
	}

}
