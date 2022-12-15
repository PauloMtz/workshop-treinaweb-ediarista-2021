package com.home.ediaristas.dto;

public class ViaCepResponse {

    private String cep;
    private String logradouro;
    private String uf;
    private String localidade;
    private String complemento;
    private String bairro;
    private String ibge;

    // construtor vazio --> digite cto e selecione ctor
    public ViaCepResponse() {
    }

    /*
        ---- construtor com argumentos -----
        selecione todos os atributos e tecle com o botão direito
        e selecione a opção 'Source Action...'
        selecione a opção generate constructors
    */

    public ViaCepResponse(String cep, String logradouro, String uf, 
        String localidade, String complemento, String bairro, String ibge) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.uf = uf;
        this.localidade = localidade;
        this.complemento = complemento;
        this.bairro = bairro;
        this.ibge = ibge;
    }

    /*
        ---- Getters e Setters -----
        selecione todos os atributos e tecle CTRL + SHIFT + p
        ou, selecione e clique com o botão direito, e selecione
        a última opção na lista --> command pallete
        digite generate get and set methods
    */

    public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getIbge() {
		return this.ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

    /*
        ---- toString() -----
        selecione todos os atributos e tecle com o botão direito
        e selecione a opção 'Source Action...'
        selecione a opção generate toString()
    */
    
    @Override
    public String toString() {
        return "ViaCepResponse [bairro=" + bairro + ", cep=" 
            + cep + ", complemento=" + complemento + ", ibge=" 
            + ibge + ", localidade=" + localidade + ", logradouro=" 
            + logradouro + ", uf=" + uf + "]";
    }
}
