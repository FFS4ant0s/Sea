package com.sea.backend.clientmanager.util;

public class FormatadorUtil {

    public static String adicionarMascaraCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) return cpf;
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String removerMascaraCpf(String cpf) {
        if (cpf == null) return null;
        return cpf.replaceAll("\\D", ""); // Remove tudo que não for dígito
    }

    public static String adicionarMascaraCep(String cep) {
        if (cep == null || cep.length() != 8) return cep;
        return cep.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }

    public static String removerMascaraCep(String cep) {
        if (cep == null) return null;
        return cep.replaceAll("\\D", "");
    }

    public static String adicionarMascaraTelefone(String numero) {
        if (numero == null || !(numero.length() == 10 || numero.length() == 11)) return numero;
        if (numero.length() == 10) {
            // Ex: 1133445566 → (11)3344-5566
            return numero.replaceFirst("(\\d{2})(\\d{4})(\\d{4})", "($1)$2-$3");
        } else {
            // Ex: 11987654321 → (11)98765-4321
            return numero.replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1)$2-$3");
        }
    }

    public static String removerMascaraTelefone(String numero) {
        if (numero == null) return null;
        return numero.replaceAll("\\D", "");
    }
}
