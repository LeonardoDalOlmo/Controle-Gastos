package com.example.Controle_Gastos.services.Exceptions;

public class ResourceNotFoundException extends RuntimeException  {
    public ResourceNotFoundException(String mensage) {
        super(mensage);
    }
}
