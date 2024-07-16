package com.shopAll.ShopAll.Excepcion;

public class InvalidProductoDataException extends RuntimeException {
    public InvalidProductoDataException(String mensaje) {
        super(mensaje);
    }
}
