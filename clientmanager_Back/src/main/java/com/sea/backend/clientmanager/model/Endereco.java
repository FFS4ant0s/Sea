package com.sea.backend.clientmanager.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Representa o endereço do cliente, armazenado como componente embutido (embedded).
 */
@Embeddable
public class Endereco {

    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 dígitos numéricos (sem máscara)")
    private String cep;

    @NotBlank(message = "O logradouro é obrigatório")
    private String logradouro;

    @NotBlank(message = "O bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "A UF é obrigatória")
    @Size(min = 2, max = 2, message = "A UF deve conter exatamente 2 letras")
    private String uf;

    private String complemento;

    public Endereco() {
    }

    public Endereco(String cep, String logradouro, String bairro, String cidade, String uf, String complemento) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.complemento = complemento;
    }

    // Getters e Setters

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(cep, endereco.cep) &&
                Objects.equals(logradouro, endereco.logradouro) &&
                Objects.equals(bairro, endereco.bairro) &&
                Objects.equals(cidade, endereco.cidade) &&
                Objects.equals(uf, endereco.uf) &&
                Objects.equals(complemento, endereco.complemento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, logradouro, bairro, cidade, uf, complemento);
    }
}
