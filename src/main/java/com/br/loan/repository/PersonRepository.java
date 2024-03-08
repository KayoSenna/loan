package com.br.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.loan.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	@Query("select p from Person p where p.identifier=:identifier")
	Person findByIdentifier(@Param("identifier") String identifier);

}
