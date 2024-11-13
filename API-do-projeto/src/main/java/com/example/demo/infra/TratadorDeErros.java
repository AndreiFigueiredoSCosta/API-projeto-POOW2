package com.example.demo.infra;

import com.example.demo.exceptions.ObjetoNaoEncontradoException;
import com.example.demo.exceptions.QuantidadeInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<RestErrorMessage> tratarErro404(ObjetoNaoEncontradoException ex){
        RestErrorMessage r = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(404).body(r);
    }

    @ExceptionHandler(QuantidadeInvalidaException.class)
    public ResponseEntity<RestErrorMessage> erroDeQtdInvalida(QuantidadeInvalidaException ex){
        RestErrorMessage r = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(500).body(r);
    }
}
