package com.example.demo.exceptions;

import java.util.NoSuchElementException;

public class ObjetoNaoEncontradoException extends NoSuchElementException {
    public ObjetoNaoEncontradoException(String msg){
        super(msg);
    }
}
