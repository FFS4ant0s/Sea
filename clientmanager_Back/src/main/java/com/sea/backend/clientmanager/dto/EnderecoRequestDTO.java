package com.sea.backend.clientmanager.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class EnderecoRequestDTO {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 dígitos numéricos (sem máscara)")
    private String cep;

    private String complemento;

    @NotNull
    private Integer numero;

    public EnderecoRequestDTO() {
    }

    public EnderecoRequestDTO(String logradouro, String bairro, String cidade, String estado, String cep, String complemento, Integer numero) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
        this.numero = numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "EnderecoRequestDTO{" +
                "logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                ", complemento='" + complemento + '\'' +
                ", numero=" + numero +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnderecoRequestDTO)) return false;
        EnderecoRequestDTO that = (EnderecoRequestDTO) o;
        return Objects.equals(logradouro, that.logradouro) &&
                Objects.equals(bairro, that.bairro) &&
                Objects.equals(cidade, that.cidade) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(cep, that.cep) &&
                Objects.equals(complemento, that.complemento) &&
                Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logradouro, bairro, cidade, estado, cep, complemento, numero);
    }
}