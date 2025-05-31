package com.sea.backend.clientmanager.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import javax.validation.Valid;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9 ]+$", message = "O nome deve conter apenas letras, números e espaços")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos (sem máscara)")
    private String cpf;

    @Embedded
    @Valid
    private Endereco endereco;

    @ElementCollection
    @CollectionTable(name = "cliente_telefones", joinColumns = @JoinColumn(name = "cliente_id"))
    private List<@Valid Telefone> telefones;

    @ElementCollection
    @CollectionTable(name = "cliente_emails", joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "email")
    private List<@Email(message = "E-mail inválido") @NotBlank(message = "O e-mail não pode estar vazio") String> emails;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String cpf, Endereco endereco, List<Telefone> telefones, List<String> emails) {
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
} 
