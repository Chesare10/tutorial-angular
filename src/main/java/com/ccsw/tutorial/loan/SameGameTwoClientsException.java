package com.ccsw.tutorial.loan;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "El juego ya está prestado a un cliente en el rango de préstamo elegido. Indique otro juego o espere a que el cliente devuelva el juego.")
public class SameGameTwoClientsException extends Exception {

}
