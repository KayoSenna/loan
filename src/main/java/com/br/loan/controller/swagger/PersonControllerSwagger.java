package com.br.loan.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.ResponseEntity;

import com.br.loan.dto.request.PersonRequest;
import com.br.loan.dto.response.LoanResponse;
import com.br.loan.dto.response.PersonResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Controller de Pessoa")
public interface PersonControllerSwagger  {

	@Operation(summary = "Cria uma pessoa", method = "POST")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
			@ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = Problem.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<PersonResponse> create(PersonRequest request);

	@Operation(summary = "Atualiza uma pessoa por Id", method = "PUT")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
			@ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = Problem.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<PersonResponse> update(Long id, @Valid PersonRequest request);

	@Operation(summary = "Busca uma pessoa por Id", method = "GET")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
			@ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = Problem.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<PersonResponse> findById(Long id);

	@Operation(summary = "Delata uma pessoa por Id", method = "DELETE")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
			@ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = Problem.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<Void> deleteById(Long id);

	@Operation(summary = "Busca todas as pessoa cadastradas", method = "GET")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
			@ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = Problem.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<List<PersonResponse>> findAll();
	
	@Operation(summary = "Lista todos os emprestimos de uma pessoa por seu Id", method = "GET")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = LoanResponse.class)) }, description = "Requisição com sucesso"),
	@ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = Problem.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<List<LoanResponse>> findAllLoansByPersonId(Long id);
	
}

