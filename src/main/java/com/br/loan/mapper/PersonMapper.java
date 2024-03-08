package com.br.loan.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.loan.dto.request.PersonRequest;
import com.br.loan.dto.response.PersonResponse;
import com.br.loan.model.Person;
import com.br.loan.model.enums.TypeIdentifier;

@Service
public class PersonMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public Person create(PersonRequest request) {

		Person model = new Person();
		model.setName(request.getName());
		model.setIdentifier(request.getIdentifier());
		model.setBirthDate(request.getBirthDate());

		model.setTypeIdentifier(request.getIdentifier().length() == 8 ? TypeIdentifier.EU
				: request.getIdentifier().length() == 11 ? TypeIdentifier.PF
						: request.getIdentifier().length() == 10 ? TypeIdentifier.AP : TypeIdentifier.PJ);

		model.setMinimumMonthlyAmount(request.getIdentifier().length() == 8 ? new BigDecimal("100.0000")
				: request.getIdentifier().length() == 11 ? new BigDecimal("300.0000")
						: request.getIdentifier().length() == 10 ? new BigDecimal("400.0000")
								: new BigDecimal("1000.0000"));

		model.setMaximumLoanAmount(request.getIdentifier().length() == 8 ? new BigDecimal("10000.0000")
				: request.getIdentifier().length() == 11 ? new BigDecimal("10000.0000")
						: request.getIdentifier().length() == 10 ? new BigDecimal("25000.0000")
								: new BigDecimal("100000.0000"));
		
		model.setLoans(new ArrayList<>());

		return model;

	}
	
	
	public Person update(PersonRequest request) {

		Person model = new Person();
		model.setName(request.getName());
		model.setIdentifier(request.getIdentifier());
		model.setBirthDate(request.getBirthDate());

		model.setTypeIdentifier(request.getIdentifier().length() == 8 ? TypeIdentifier.EU
				: request.getIdentifier().length() == 11 ? TypeIdentifier.PF
						: request.getIdentifier().length() == 10 ? TypeIdentifier.AP : TypeIdentifier.PJ);

		model.setMinimumMonthlyAmount(request.getIdentifier().length() == 8 ? new BigDecimal("100.0000")
				: request.getIdentifier().length() == 11 ? new BigDecimal("300.0000")
						: request.getIdentifier().length() == 10 ? new BigDecimal("400.0000")
								: new BigDecimal("1000.0000"));

		model.setMaximumLoanAmount(request.getIdentifier().length() == 8 ? new BigDecimal("10000.0000")
				: request.getIdentifier().length() == 11 ? new BigDecimal("10000.0000")
						: request.getIdentifier().length() == 10 ? new BigDecimal("25000.0000")
								: new BigDecimal("100000.0000"));

		return model;

	}
	
	public PersonResponse response (Person model) {
	
		PersonResponse response = modelMapper.map(model, PersonResponse.class);
		
		return response;
	}
	
	public List<PersonResponse> response(List<Person> model){
		
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

}
