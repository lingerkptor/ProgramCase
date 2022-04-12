package com.example.mockito.Dao.exception;

public class ProductIsExist extends RuntimeException{
    private static final long serialVersionUID = 6098210367153239341L;

    public ProductIsExist(String message) {
        super(message);
    }
}
