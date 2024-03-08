package com.br.loan.dto.request;


import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.br.loan.model.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanRequest {

	@NotNull( message= "Data de criação não pode ser nulo!" )
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonProperty("dataCriacao")
	private LocalDate creationDate;
	
	@NotNull( message= "O status de pagamento não pode ser nulo!" )
	@JsonProperty("statusPagamento")
	private PaymentStatus paymentStatus;
	
	@NotNull( message= "O valor de emprestimo não pode ser nulo!" )
	@JsonProperty("valorDeEmprestimo")
	private BigDecimal value;
	
	@NotNull( message= "O valor do número de parcelas não pode ser nulo!" )
	@JsonProperty("numeroDeParcelas")
	private Integer numberOfInstallments;

}
