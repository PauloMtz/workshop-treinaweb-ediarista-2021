package com.home.ediaristas.converter;

import javax.persistence.AttributeConverter;

/*
    vai converter de String para String, ou seja, apenas trata essa String
    para gravar no banco de dados sem os caracteres especiais
    deve informar lá no atributo na classe model que tem que seguir esse converter
*/

public class CpfConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String cpf) {
        // substiui o ponto e o traço por vazio
        return cpf.replaceAll("[.-]", "");
    }

    @Override
    public String convertToEntityAttribute(String cpf) {
        // retorna o cpf que vem do banco de dados
        return cpf;
    }
    
}
