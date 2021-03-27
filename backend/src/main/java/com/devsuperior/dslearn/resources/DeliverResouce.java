package com.devsuperior.dslearn.resources;

import com.devsuperior.dslearn.dtos.DeliverRevisionDTO;
import com.devsuperior.dslearn.services.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deliveries")
public class DeliverResouce {

	@Autowired
	private DeliverService service;


    
    
	@PutMapping("/{id}")
	public ResponseEntity<DeliverRevisionDTO> saveRevision(@RequestBody DeliverRevisionDTO dto, @PathVariable Long id){
		service.saveRevision(id, dto); 
		return ResponseEntity.noContent().build();
	}

    

}
