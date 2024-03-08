package com.br.loan.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.br.loan.dto.request.LoanRequest;
import com.br.loan.dto.response.LoanResponse;
import com.br.loan.execption.BaseException;
import com.br.loan.mapper.LoanMapper;
import com.br.loan.model.Loan;
import com.br.loan.model.Person;
import com.br.loan.model.enums.PaymentStatus;
import com.br.loan.repository.LoanRepository;
import com.br.loan.repository.PersonRepository;
import com.br.loan.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private LoanRepository repository;

	@Autowired
	private LoanMapper mapper;

	@Override
	@Transactional
	public LoanResponse create(Long personId, LoanRequest request) {

		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!") {
				});
		
		if(!request.getPaymentStatus().equals(PaymentStatus.PENDENTE)) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "Não é possivel cadastrar um empréstimo já como pago ou cancelado!");
		}

		validationRequest(person, request);
		
		Loan model = mapper.create(person, request);

		model = repository.save(model);

		return mapper.response(model);

	}
	
	public void validationRequest(Person person, LoanRequest request) {
		
		if(request.getNumberOfInstallments()>24) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "O maximo de parcelas é 24!");
		}
		
		if(request.getNumberOfInstallments()<1) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "O minimo de parcelas é 1!");
		}

		if(request.getValue().doubleValue()>person.getMaximumLoanAmount().doubleValue()) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "O valor de empréstimo está acima do permitido a "+person.getName()+" !");
		}
		
		if(request.getValue().doubleValue()>person.getMaximumLoanAmount().doubleValue()) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "O valor de empréstimo está acima do permitido a "+person.getName()+" !");
		}

		Double valueInstallments = request.getValue().doubleValue() / request.getNumberOfInstallments();

		if(valueInstallments.doubleValue()<person.getMinimumMonthlyAmount().doubleValue()) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "O valor para cada parcela está abaixo do permitido para o tipo de indentificador da pessoa informada!");
		}
		
		// caso haja limite de idade a melhorar
//				
//				Period period = Period.between(person.getBirthDate(), LocalDate.now());
//				
//				if(period.getYears()<18) {
//					throw new BaseException(HttpStatus.BAD_REQUEST, "É preciso haver maioridade!");
//				}
//				
		//
		
	}

	@Override
	@Transactional
	public LoanResponse update(Long id, LoanRequest request) {

		Loan model = repository.findById(id)
				.orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado!") {
				});
		
		validationRequest(model.getPerson(), request);

		Loan loan = mapper.update(model.getPerson(), request);

		BeanUtils.copyProperties(loan, model, "id");

		model = repository.save(model);

		return mapper.response(model);

	}

	@Override
	public LoanResponse findById(Long id) {

		Loan model = repository.findById(id)
				.orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado!") {
				});

		return mapper.response(model);

	}

	@Override
	@Transactional
	public void deleteById(Long id) {

		Loan model = repository.findById(id)
				.orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado!") {
				});
		
		if(!model.getPaymentStatus().equals(PaymentStatus.PAGO) && !model.getPaymentStatus().equals(PaymentStatus.CANCELADO)) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "O empréstimo informado não pode ser deletado, pois se encontra pendente de pagamento");
		}

		repository.delete(model);
	}
	
	@Override
	public List<LoanResponse> findAll(){
		return mapper.response(repository.findAll());
	}
	
	@Override
	public List<LoanResponse> findAllByPersonId(Long personId){
		return mapper.response(repository.findByPersonId(personId));
	}
	

}