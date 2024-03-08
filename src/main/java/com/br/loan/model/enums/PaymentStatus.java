package com.br.loan.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {

    PAGO("Pego"),
    PENDENTE("Pendente"),
    CANCELADO("Cancelado");
	
	String name;

}
