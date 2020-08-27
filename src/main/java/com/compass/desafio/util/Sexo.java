package com.compass.desafio.util;

public enum Sexo {
    MASCULINO(0),
    FEMININO(1),
    OUTRO(2);

    private final int value;

    Sexo(final int i) {
        value = i;
    }

    public int getValue() { return value; }
}
