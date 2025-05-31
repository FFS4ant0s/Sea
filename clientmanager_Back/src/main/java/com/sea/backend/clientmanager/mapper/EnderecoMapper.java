package com.sea.backend.clientmanager.mapper;

import com.sea.backend.clientmanager.dto.EnderecoRequestDTO;
import com.sea.backend.clientmanager.dto.EnderecoResponseDTO;
import com.sea.backend.clientmanager.model.Endereco;
import com.sea.backend.clientmanager.util.FormatadorUtil;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoRequestDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setCep(FormatadorUtil.removerMascaraCep(dto.getCep()));
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setUf(dto.getEstado());
        endereco.setComplemento(dto.getComplemento());
        return endereco;
    }

    public static EnderecoResponseDTO toDTO(Endereco endereco) {
        EnderecoResponseDTO dto = new EnderecoResponseDTO();
        dto.setCep(FormatadorUtil.adicionarMascaraCep(endereco.getCep()));
        dto.setLogradouro(endereco.getLogradouro());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getUf());
        dto.setComplemento(endereco.getComplemento());
        return dto;
    }
}

