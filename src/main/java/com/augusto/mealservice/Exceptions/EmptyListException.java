package com.augusto.mealservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmptyListException extends Exception {
    public EmptyListException(String message){
        super(message);
    }
}
