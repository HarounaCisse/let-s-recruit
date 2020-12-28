package com.maliware.let.srecruit.exception;

import java.util.NoSuchElementException;

public class CustomException extends NoSuchElementException {
    public CustomException(String msg){
        super(msg);
    }
}
