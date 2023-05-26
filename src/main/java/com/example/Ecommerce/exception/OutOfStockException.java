package com.example.Ecommerce.exception;

public class OutOfStockException extends  Exception{

    public OutOfStockException(String message){
        super(message);
    }
}
