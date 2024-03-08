package com.br.loan.dto.request;


import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonRequest {

	@NotBlank( message= "Nome não pode ser nulo!" )
	@JsonProperty("nome")
	private String name;
	
	@NotBlank( message= "Identificador não pode ser nulo!" )
	@JsonProperty("identificador")
	private String identifier;
	
	@NotNull( message= "Data de Nascimento não pode ser nulo!" )
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonProperty("dataNascimento")
	private LocalDate birthDate;

}