package com.ccsw.tutorial.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "El nombre ya existe. Elija otro")
public class NameAlreadyExistsException extends Exception {
}
