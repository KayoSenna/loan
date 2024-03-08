package com.br.loan.service;

import java.util.List;

import com.br.loan.dto.request.LoanRequest;
import com.br.loan.dto.response.LoanResponse;

public interface LoanService {

	void deleteById(Long id);

	LoanResponse findById(Long id);

	LoanResponse update(Long id, LoanRequest request);

	LoanResponse create(Long personId, LoanRequest request);

	List<LoanResponse> findAll();

	List<LoanResponse> findAllByPersonId(Long personId);

}