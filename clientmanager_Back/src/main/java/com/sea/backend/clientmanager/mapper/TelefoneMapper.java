package com.sea.backend.clientmanager.mapper;

import com.sea.backend.clientmanager.dto.TelefoneDTO;
import com.sea.backend.clientmanager.model.Telefone;
import com.sea.backend.clientmanager.util.FormatadorUtil;

public class TelefoneMapper {

    public static Telefone toEntity(TelefoneDTO dto) {
        Telefone telefone = new Telefone();
        telefone.setTipo(dto.getTipo());
        telefone.setNumero(FormatadorUtil.removerMascaraTelefone(dto.getNumero()));
        return telefone;
    }

    public static TelefoneDTO toDTO(Telefone telefone) {
        TelefoneDTO dto = new TelefoneDTO();
        dto.setTipo(telefone.getTipo());
        dto.setNumero(FormatadorUtil.adicionarMascaraTelefone(telefone.getNumero()));
        return dto;
    }
}
