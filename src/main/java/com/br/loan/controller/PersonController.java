package com.br.loan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.loan.controller.swagger.PersonControllerSwagger;
import com.br.loan.dto.request.PersonRequest;
import com.br.loan.dto.response.LoanResponse;
import com.br.loan.dto.response.PersonResponse;
import com.br.loan.service.LoanService;
import com.br.loan.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController implements PersonControllerSwagger{
	
	@Autowired
	private PersonService service;
	
	@Autowired
	private LoanService loanService;
	

	@PostMapping
	@Override
	public ResponseEntity<PersonResponse> create(@RequestBody @Valid PersonRequest request) {
		
		return ResponseEntity.ok(service.create(request));
	}
	
	@PutMapping(path = "/{id}")
	@Override
	public ResponseEntity<PersonResponse> update(@PathVariable("id") Long id,@RequestBody @Valid PersonRequest request) {
		
		return ResponseEntity.ok(service.update(id,request));
	}
	
	@GetMapping(path = "/{id}")
	@Override
	public ResponseEntity<PersonResponse> findById(@PathVariable("id") Long id) {
		
		return ResponseEntity.ok(service.findById(id));
	}
	
	@DeleteMapping(path = "/{id}")
	@Override
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		
		service.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	@Override
	public ResponseEntity<List<PersonResponse>> findAll() {
		
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping(path = "/{id}/loan")
	@Override
	public ResponseEntity<List<LoanResponse>> findAllLoansByPersonId(@PathVariable("id") Long id) {
		
		return ResponseEntity.ok(loanService.findAllByPersonId(id));
	}
	
}
