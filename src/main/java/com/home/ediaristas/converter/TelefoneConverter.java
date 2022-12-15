package com.home.ediaristas.converter;

import javax.persistence.AttributeConverter;

/*
    vai converter de String para String, ou seja, apenas trata essa String
    para gravar no banco de dados sem os caracteres especiais
    deve informar lá no atributo na classe model que tem que seguir esse converter
*/

public class TelefoneConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String telefone) {
        // substiui os parênteses, o espaço e o traço por vazio
        return telefone.replaceAll("[() -]", "");
    }

    @Override
    public String convertToEntityAttribute(String telefone) {
        // retorna o telefone que vem do banco de dados
        return telefone;
    }
    
}
