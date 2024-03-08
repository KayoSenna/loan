package com.br.loan.service.impl;

import static java.util.Objects.nonNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.br.loan.dto.request.PersonRequest;
import com.br.loan.dto.response.PersonResponse;
import com.br.loan.execption.BaseException;
import com.br.loan.mapper.PersonMapper;
import com.br.loan.model.Person;
import com.br.loan.repository.PersonRepository;
import com.br.loan.service.PersonService;
import com.br.loan.util.ValidateIdentifier;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository repository;

	@Autowired
	private PersonMapper mapper;

	@Override
	@Transactional
	public PersonResponse create(PersonRequest request) {
		
		validationRequest(request);

		Person validateExistingIdentifier = repository.findByIdentifier(request.getIdentifier());

		if (nonNull(validateExistingIdentifier)) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "O identificador fornecido já está registrado!");
		}
	
		Person model = mapper.create(request);
		model = repository.save(model);

		return mapper.response(model);
	}
	
	@Override
	@Transactional
	public PersonResponse update(Long id, PersonRequest request) {
		
		validationRequest(request);

		Person model = repository.findById(id)
				.orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!") {
				});

		Person validateExistingIdentifier = repository.findByIdentifier(request.getIdentifier());

		if (nonNull(validateExistingIdentifier) && !request.getIdentifier().equals(model.getIdentifier())) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "O identificador fornecido já está registrado!");
		}

		Person person = mapper.update(request);

		BeanUtils.copyProperties(person, model, "id");
		
		model = repository.save(model);

		return mapper.response(model);
	}
	
	public void validationRequest(PersonRequest request) {
		
		if(request.getIdentifier().length() == 9 || request.getIdentifier().length() == 12 || request.getIdentifier().length() == 13 || request.getIdentifier().length() <8 || request.getIdentifier().length() >14) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "O indentificador informado é invalido!");
		}
		
		if(request.getIdentifier().length() == 11) {
			
			if(!ValidateIdentifier.validateCPF(request.getIdentifier())) {
				throw new BaseException(HttpStatus.BAD_REQUEST, "O indentificador CPF informado é invalido!");
			}
			
		}
		
		if(request.getIdentifier().length() == 14) {
			
			if(!ValidateIdentifier.validateCNPJ(request.getIdentifier())) {
				throw new BaseException(HttpStatus.BAD_REQUEST, "O indentificador CNPJ informado é invalido!");
			}
			
		}
	
		if(request.getIdentifier().length() == 8) {
			
			if(!ValidateIdentifier.validateEU(request.getIdentifier())) {
				throw new BaseException(HttpStatus.BAD_REQUEST, "O indentificador EU informado é invalido!");
			}
			
		}
		
		if(request.getIdentifier().length() == 10) {
			
			if(!ValidateIdentifier.validateAP(request.getIdentifier())) {
				throw new BaseException(HttpStatus.BAD_REQUEST, "O indentificador AP informado é invalido!");
			}
			
		}
		
		// caso haja limite de idade a melhorar
		//		
		//		Period period = Period.between(request.getBirthDate(), LocalDate.now());
		//		
		//		if(period.getYears()<18) {
		//			throw new BaseException(HttpStatus.BAD_REQUEST, "É preciso haver maioridade!");
		//		}
		//		
		//
		
	}

	@Override
	public PersonResponse findById(Long id) {

		Person model = repository.findById(id)
				.orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!") {
				});

		return mapper.response(model);
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {

		Person model = repository.findById(id)
				.orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!") {
				});

		repository.delete(model);

	}

	@Override
	public List<PersonResponse> findAll() {
		return mapper.response(repository.findAll());
	}
	
}