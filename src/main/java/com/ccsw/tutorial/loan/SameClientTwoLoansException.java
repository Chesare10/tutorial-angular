package com.ccsw.tutorial.loan;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "El cliente  ya tiene prestado el máximo de 2 juegos en el rango de préstamo elegido, indique otro cliente o espere que devuelva un juego")
public class SameClientTwoLoansException extends Exception {

}
