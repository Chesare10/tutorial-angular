package com.ccsw.tutorial.loan;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "El período máximo de préstamo es de 14 días.")
public class LoanLongerThan14DaysException extends Exception {

}
