package com.sea.backend.clientmanager.mapper;

import com.sea.backend.clientmanager.dto.*;
import com.sea.backend.clientmanager.model.Cliente;
import com.sea.backend.clientmanager.util.FormatadorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static Cliente toEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(FormatadorUtil.removerMascaraCpf(dto.getCpf()));
        cliente.setEndereco(EnderecoMapper.toEntity(dto.getEndereco()));
        cliente.setTelefones(dto.getTelefones().stream()
                .map(TelefoneMapper::toEntity)
                .collect(Collectors.toList()));
        cliente.setEmails(new ArrayList<>(dto.getEmails()));
        return cliente;
    }

    public static ClienteResponseDTO toDTO(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setCpf(FormatadorUtil.adicionarMascaraCpf(cliente.getCpf()));
        dto.setEndereco(EnderecoMapper.toDTO(cliente.getEndereco()));
        dto.setTelefones(cliente.getTelefones().stream()
                .map(TelefoneMapper::toDTO)
                .collect(Collectors.toList()));
        dto.setEmails(new ArrayList<>(cliente.getEmails()));
        return dto;
    }

    public static List<ClienteResponseDTO> toDTOList(List<Cliente> clientes) {
        return clientes.stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }
}