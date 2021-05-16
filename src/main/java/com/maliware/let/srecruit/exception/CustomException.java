package com.maliware.let.srecruit.exception;

import java.util.NoSuchElementException;

public class CustomException extends RuntimeException {
    public CustomException(String msg){
        super(msg);
    }
}
