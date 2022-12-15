package com.home.ediaristas.validator;

import com.home.ediaristas.models.Diarista;
import com.home.ediaristas.service.ViaCepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CepValidator implements Validator {

    /*
        esse @Component é um bean spring genérico
        pois, o spring só consegue fazer injeção de dependência
        em classes bean, e essa não é service nem controller
        por isso, foi usado esse bean genérico
    */

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public boolean supports(Class<?> clazz) {
        // valida se o objeto é de uma classe
        return Diarista.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // converte o target para um objeto Diarista
        var diarista = (Diarista) target;

        // valida o cep
        try {
            var cep = diarista.getCep();
            viaCepService.buscarPorCep(cep);
        } catch (RuntimeException e) {
            errors.rejectValue("cep", null, e.getMessage());
        }

        // TEM QUE INFORMAR LÁ NO CONTROLLER PARA UTILIZAR ESSA CLASSE
    }
    
}
