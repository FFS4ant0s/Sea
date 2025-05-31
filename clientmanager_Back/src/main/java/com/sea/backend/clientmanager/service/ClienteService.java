package com.sea.backend.clientmanager.service;

import com.sea.backend.clientmanager.dto.ClienteRequestDTO;
import com.sea.backend.clientmanager.dto.ClienteResponseDTO;
import com.sea.backend.clientmanager.mapper.ClienteMapper;
import com.sea.backend.clientmanager.model.Cliente;
import com.sea.backend.clientmanager.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteResponseDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente salvo = clienteRepository.save(cliente);
        return ClienteMapper.toDTO(salvo);
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
        return ClienteMapper.toDTO(cliente);
    }

    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dto) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));

        Cliente atualizado = ClienteMapper.toEntity(dto);
        atualizado.setId(existente.getId()); // mantém o mesmo ID

        Cliente salvo = clienteRepository.save(atualizado);
        return ClienteMapper.toDTO(salvo);
    }

    public void deletarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
        clienteRepository.delete(cliente);
    }
}