package com.sea.backend.clientmanager.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class TelefoneDTO {

    @NotBlank(message = "O tipo de telefone é obrigatório")
    private String tipo;

    @NotBlank
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter apenas números, com DDD (ex: 11999999999)")
    private String numero;

    public TelefoneDTO() {
    }

    public TelefoneDTO(String tipo, String numero) {
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
        return "TelefoneDTO{" +
                "tipo='" + tipo + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TelefoneDTO)) return false;
        TelefoneDTO that = (TelefoneDTO) o;
        return Objects.equals(tipo, that.tipo) &&
               Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, numero);
    }
}