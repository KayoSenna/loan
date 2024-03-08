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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.loan.controller.swagger.LoanControllerSwagger;
import com.br.loan.dto.request.LoanRequest;
import com.br.loan.dto.response.LoanResponse;
import com.br.loan.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController implements LoanControllerSwagger{
	
	@Autowired
	private LoanService service;
	
	@PostMapping
	@Override
	public ResponseEntity<LoanResponse> create(@RequestParam("pessoaId") Long personId, @RequestBody @Valid LoanRequest request) {
		
		return ResponseEntity.ok(service.create(personId,request));
	}
	
	@PutMapping(path = "/{id}")
	@Override
	public ResponseEntity<LoanResponse> update(@PathVariable("id") Long id,@RequestBody @Valid LoanRequest request) {
		
		return ResponseEntity.ok(service.update(id,request));
	}
	
	@GetMapping(path = "/{id}")
	@Override
	public ResponseEntity<LoanResponse> findById(@PathVariable("id") Long id) {
		
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
	public ResponseEntity<List<LoanResponse>> findAll() {
		
		return ResponseEntity.ok(service.findAll());
	}

}
