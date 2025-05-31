package com.sea.backend.clientmanager.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Representa um telefone associado a um cliente.
 * Armazenado como parte embutida (ElementCollection).
 */
@Embeddable
public class Telefone {

    @NotBlank(message = "O tipo de telefone é obrigatório (residencial, comercial ou celular)")
    private String tipo;

    @NotBlank(message = "O número do telefone é obrigatório")
    @Pattern(
        regexp = "\\d{10,11}",
        message = "O telefone deve conter apenas números, com DDD. Ex: 11999998888"
    )
    private String numero;

    public Telefone() {
    }

    public Telefone(String tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "tipo='" + tipo + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Telefone)) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(tipo, telefone.tipo) &&
                Objects.equals(numero, telefone.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, numero);
    }
}