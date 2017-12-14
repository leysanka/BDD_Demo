package com.epam.bdd.ebay.exceptions;

public class ElementNotPresentException extends RuntimeException {
    public ElementNotPresentException(String s) {
        super(s);
    }
}
