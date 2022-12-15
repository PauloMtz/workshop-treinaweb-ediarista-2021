package com.home.ediaristas.controller;

import com.home.ediaristas.dto.DiaristasPagedResponse;
import com.home.ediaristas.repository.DiaristaRepository;
import com.home.ediaristas.service.ViaCepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diaristas-cidade")
public class DiaristaRestControllerApi {
    
    @Autowired
    private DiaristaRepository repository;

    @Autowired
    private ViaCepService viaCepService;

    @GetMapping
    public DiaristasPagedResponse buscarDiaristasPorCep(@RequestParam String cep) {

        var endereco = viaCepService.buscarPorCep(cep);
        var codigoIbge = endereco.getIbge();
        
        // paginação
        var page = 0;
        var size = 6;
        var pageable = PageRequest.of(page, size);
        var diaristas = repository.findByCodigoIbge(codigoIbge, pageable);
        var quantidadeDiaristas = diaristas.getTotalElements() > size 
            ? diaristas.getTotalElements() - size : page;

        return new DiaristasPagedResponse(diaristas.getContent(), quantidadeDiaristas);
    }
}
