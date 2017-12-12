package com.epam.bdd.ebay.exceptions;

public class WrongPageException extends RuntimeException {
    public WrongPageException(String s) {
        super(s);
    }
}
