package com.sea.backend.clientmanager.dto;

import java.util.List;
import java.util.Objects;

public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String cpf; // com máscara
    private EnderecoResponseDTO endereco;
    private List<TelefoneDTO> telefones;
    private List<String> emails;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(Long id, String nome, String cpf, EnderecoResponseDTO endereco, List<TelefoneDTO> telefones, List<String> emails) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefones = telefones;
        this.emails = emails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EnderecoResponseDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoResponseDTO endereco) {
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
        return "ClienteResponseDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + endereco +
                ", telefones=" + telefones +
                ", emails=" + emails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteResponseDTO)) return false;
        ClienteResponseDTO that = (ClienteResponseDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(endereco, that.endereco) &&
                Objects.equals(telefones, that.telefones) &&
                Objects.equals(emails, that.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, endereco, telefones, emails);
    }
}