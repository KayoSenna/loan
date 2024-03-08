package com.br.loan.controller.swagger;

import java.util.List;

import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.ResponseEntity;

import com.br.loan.dto.request.LoanRequest;
import com.br.loan.dto.response.LoanResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Controller de Empréstimo")
public interface LoanControllerSwagger  {

	@Operation(summary = "Cria uma emprestimo", method = "POST")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = LoanResponse.class)) }, description = "Requisição com sucesso"),
			@ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = Problem.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<LoanResponse> create(Long personId, LoanRequest request);
	
	@Operation(summary = "Atualiza os dados de um emprestimo por Id", method = "PUT")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = LoanResponse.class)) }, description = "Requisição com sucesso"),
	@ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = Problem.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<LoanResponse> update(Long id, LoanRequest request);
	
	@Operation(summary = "Busca os dados de um emprestimo por Id", method = "GET")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = LoanResponse.class)) }, description = "Requisição com sucesso"),
	@ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = Problem.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<LoanResponse> findById(Long id);
	
	@Operation(summary = "Deleta um emprestimo por Id", method = "DELETE")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Requisição com sucesso"),
			@ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = Problem.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<Void> deleteById(Long id);

	@Operation(summary = "Lista todos os emprestimos cadastrados", method = "GET")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = LoanResponse.class)) }, description = "Requisição com sucesso"),
	@ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json", 
		schema = @Schema(implementation = Problem.class)) }, description = "O recurso não foi encontrado") })
	ResponseEntity<List<LoanResponse>> findAll();

}

