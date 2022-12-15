package com.home.ediaristas.converter;

import javax.persistence.AttributeConverter;

/*
    vai converter de String para String, ou seja, apenas trata essa String
    para gravar no banco de dados sem os caracteres especiais
    deve informar lá no atributo na classe model que tem que seguir esse converter
*/

public class CepConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String cep) {
        // substiui o traço por vazio
        return cep.replace("-", "");
    }

    @Override
    public String convertToEntityAttribute(String cep) {
        // retorna o cep que vem do banco de dados
        return cep;
    }
    
}
