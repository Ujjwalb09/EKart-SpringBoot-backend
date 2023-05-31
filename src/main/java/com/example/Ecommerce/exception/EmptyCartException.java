package com.example.Ecommerce.exception;

public class EmptyCartException extends Exception{

    public EmptyCartException(String message){
        super(message);
    }
}
