package com.shopAll.ShopAll.Excepcion;

public class InvalidUsuarioDataException extends RuntimeException  {
    public InvalidUsuarioDataException(String mensaje) {
        super(mensaje);
    }
}
