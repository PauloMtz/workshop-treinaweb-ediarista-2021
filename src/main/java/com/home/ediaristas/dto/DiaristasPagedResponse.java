package com.home.ediaristas.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.home.ediaristas.models.Diarista;

public class DiaristasPagedResponse {
    
    private List<Diarista> diaristas;

    @JsonProperty("quantidade_diaristas")
    private Long quantidadeDiaristas;

    public DiaristasPagedResponse() {
    }

    public DiaristasPagedResponse(List<Diarista> diaristas, Long quantidadeDiaristas) {
        this.diaristas = diaristas;
        this.quantidadeDiaristas = quantidadeDiaristas;
    }

	public List<Diarista> getDiaristas() {
		return this.diaristas;
	}

	public void setDiaristas(List<Diarista> diaristas) {
		this.diaristas = diaristas;
	}

	public Long getQuantidadeDiaristas() {
		return this.quantidadeDiaristas;
	}

	public void setQuantidadeDiaristas(Long quantidadeDiaristas) {
		this.quantidadeDiaristas = quantidadeDiaristas;
	}

}
