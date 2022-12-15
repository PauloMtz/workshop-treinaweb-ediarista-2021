package com.home.ediaristas.service;

import com.home.ediaristas.dto.ViaCepResponse;
import com.home.ediaristas.exception.CepInvalidoException;
import com.home.ediaristas.exception.CepNaoEncontradoException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {
    
    public ViaCepResponse buscarPorCep(String cep) {
        var url = "https://viacep.com.br/ws/" + cep + "/json/";
        var clienteHttp = new RestTemplate();
        ResponseEntity<ViaCepResponse> response;

        try {
            response = clienteHttp.getForEntity(url, ViaCepResponse.class);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new CepInvalidoException("CEP inválido");
        }

        if (response.getBody().getCep() == null) {
            throw new CepNaoEncontradoException("CEP não encontrado");
        }

        return response.getBody();
    }
}
