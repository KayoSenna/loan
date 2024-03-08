package com.br.loan.service;

import java.util.List;

import com.br.loan.dto.request.PersonRequest;
import com.br.loan.dto.response.PersonResponse;

public interface PersonService {
	
	PersonResponse create(PersonRequest request);

	PersonResponse update(Long id, PersonRequest request);

	PersonResponse findById(Long id);

	void deleteById(Long id);

	List<PersonResponse> findAll();

}