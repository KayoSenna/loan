package com.br.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.loan.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

	@Query("select l from Loan l where l.person.id=:personId")
	List<Loan> findByPersonId(@Param("personId") Long personId);
	
}
