package com.compass.desafio.util;

import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Sexo> {

    @Override
    public Sexo convert(String source) {
        return Sexo.valueOf(source.toUpperCase());
    }

}
