package com.ccsw.tutorial.loan;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Fechas inválidas: La fecha de préstamo debe ser menor a la de devolución.")
public class InvalidLoanDatesException extends Exception {
}
