package com.sea.backend.clientmanager.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

public class ClienteRequestDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9 ]+$")
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos (sem máscara)")
    private String cpf;

    @Valid
    @NotNull
    private EnderecoRequestDTO endereco;

    @NotEmpty
    private List<@Valid TelefoneDTO> telefones;

    @NotEmpty
    private List<@Email @NotBlank String> emails;

    public ClienteRequestDTO() {
    }

    public ClienteRequestDTO(String nome, String cpf, EnderecoRequestDTO endereco, List<TelefoneDTO> telefones, List<String> emails) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefones = telefones;
        this.emails = emails;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EnderecoRequestDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoRequestDTO endereco) {
        this.endereco = endereco;
    }

    public List<TelefoneDTO> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneDTO> telefones) {
        this.telefones = telefones;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "ClienteRequestDTO{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + endereco +
                ", telefones=" + telefones +
                ", emails=" + emails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteRequestDTO)) return false;
        ClienteRequestDTO that = (ClienteRequestDTO) o;
        return Objects.equals(nome, that.nome) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(endereco, that.endereco) &&
                Objects.equals(telefones, that.telefones) &&
                Objects.equals(emails, that.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, endereco, telefones, emails);
    }
}
