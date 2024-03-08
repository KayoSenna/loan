package com.br.loan.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.loan.dto.request.LoanRequest;
import com.br.loan.dto.response.LoanResponse;
import com.br.loan.model.Loan;
import com.br.loan.model.Person;

@Service
public class LoanMapper {
	
	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	private ModelMapper modelMapper;

	public Loan create(Person person,LoanRequest request) {
		
		Loan model = modelMapper.map(request, Loan.class);
		model.setPerson(person);
		
		return model;
		
	}
	
	public Loan update(Person person,LoanRequest request) {
		
		Loan model = modelMapper.map(request, Loan.class);
		model.setPerson(person);
		
		return model;
		
	}
	
	public LoanResponse response (Loan model) {
		
		LoanResponse response = modelMapper.map(model, LoanResponse.class);
		response.setPerson(personMapper.response(model.getPerson()));
		
		return response;
	}
	
	public List<LoanResponse> response(List<Loan> model){
		
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
	
}
