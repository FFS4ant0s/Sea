package com.sea.backend.clientmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.sea.backend.clientmanager.dto.ClienteRequestDTO;
import com.sea.backend.clientmanager.dto.ClienteResponseDTO;
import com.sea.backend.clientmanager.mapper.ClienteMapper;
import com.sea.backend.clientmanager.model.Cliente;
import com.sea.backend.clientmanager.repository.ClienteRepository;
import com.sea.backend.clientmanager.util.FormatadorUtil;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteResponseDTO> listarClientes() {
    return clienteRepository.findAll()
        .stream()
        .map(cliente -> {
            ClienteResponseDTO dto = ClienteMapper.toDTO(cliente);
            dto.setCpf(FormatadorUtil.adicionarMascaraCpf(dto.getCpf()));
            dto.getEndereco().setCep(FormatadorUtil.adicionarMascaraCep(dto.getEndereco().getCep()));
            dto.getTelefones().forEach(t -> t.setNumero(FormatadorUtil.adicionarMascaraTelefone(t.getNumero())));
            return dto;
        })
        .collect(Collectors.toList());
}

    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto) {
    Cliente cliente = ClienteMapper.toEntity(dto);

    // → remove máscaras
    cliente.setCpf(FormatadorUtil.removerMascaraCpf(cliente.getCpf()));
    cliente.getEndereco().setCep(FormatadorUtil.removerMascaraCep(cliente.getEndereco().getCep()));
    cliente.getTelefones().forEach(t -> t.setNumero(FormatadorUtil.removerMascaraTelefone(t.getNumero())));

    Cliente salvo = clienteRepository.save(cliente);
    return ClienteMapper.toDTO(salvo);
}

    public ClienteResponseDTO buscarPorId(Long id) {
    Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));

    ClienteResponseDTO dto = ClienteMapper.toDTO(cliente);

    // adiciona máscaras
    dto.setCpf(FormatadorUtil.adicionarMascaraCpf(dto.getCpf()));
    dto.getEndereco().setCep(FormatadorUtil.adicionarMascaraCep(dto.getEndereco().getCep()));
    dto.getTelefones().forEach(t -> t.setNumero(FormatadorUtil.adicionarMascaraTelefone(t.getNumero())));

    return dto;
}

    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dto) {
    Cliente existente = clienteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));

    Cliente atualizado = ClienteMapper.toEntity(dto);
    atualizado.setId(existente.getId());

    // → remove máscaras
    atualizado.setCpf(FormatadorUtil.removerMascaraCpf(atualizado.getCpf()));
    atualizado.getEndereco().setCep(FormatadorUtil.removerMascaraCep(atualizado.getEndereco().getCep()));
    atualizado.getTelefones().forEach(t -> t.setNumero(FormatadorUtil.removerMascaraTelefone(t.getNumero())));

    Cliente salvo = clienteRepository.save(atualizado);
    return ClienteMapper.toDTO(salvo);
}

    public void deletarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
        clienteRepository.delete(cliente);
    }
}