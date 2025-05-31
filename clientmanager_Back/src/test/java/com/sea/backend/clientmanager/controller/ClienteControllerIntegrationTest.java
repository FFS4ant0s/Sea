
package com.sea.backend.clientmanager.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sea.backend.clientmanager.dto.ClienteRequestDTO;
import com.sea.backend.clientmanager.dto.EnderecoRequestDTO;
import com.sea.backend.clientmanager.dto.TelefoneDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long clienteIdCriado;

    @BeforeEach
    public void criarClienteAntesDosTestes() throws Exception {
        ClienteRequestDTO dto = new ClienteRequestDTO();
        dto.setNome("Maria Teste");
        dto.setCpf("32165498700");

        EnderecoRequestDTO endereco = new EnderecoRequestDTO();
        endereco.setCep("71010057");
        endereco.setLogradouro("Rua Y");
        endereco.setBairro("Centro");
        endereco.setCidade("Brasília");
        endereco.setEstado("DF");
        endereco.setNumero(456);
        dto.setEndereco(endereco);

        dto.setTelefones(Arrays.asList(new TelefoneDTO("CELULAR", "61988888888")));
        dto.setEmails(Arrays.asList("maria@email.com"));

        MvcResult result = mockMvc.perform(post("/clientes")
                        .with(httpBasic("admin", "123qwe!@#"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        clienteIdCriado = objectMapper.readTree(responseBody).get("id").asLong();
    }

    @Test
    public void deveBuscarClientePorIdComSucesso() throws Exception {
        mockMvc.perform(get("/clientes/" + clienteIdCriado)
                        .with(httpBasic("admin", "123qwe!@#")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clienteIdCriado))
                .andExpect(jsonPath("$.nome").value("Maria Teste"))
                .andExpect(jsonPath("$.cpf").exists());
    }

    @Test
public void deveRetornar404QuandoClienteNaoExistir() throws Exception {
    Long idInexistente = 999999L;

    mockMvc.perform(get("/clientes/" + idInexistente)
                    .with(httpBasic("admin", "123qwe!@#")))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error").value("Recurso não encontrado"))
            .andExpect(jsonPath("$.message").value("Cliente não encontrado com ID: " + idInexistente));
}

@Test
public void deveRetornar400QuandoCpfInvalido() throws Exception {
    ClienteRequestDTO dto = new ClienteRequestDTO();
    dto.setNome("Teste CPF inválido");
    dto.setCpf("123"); // CPF inválido

    EnderecoRequestDTO endereco = new EnderecoRequestDTO();
    endereco.setCep("71010057");
    endereco.setLogradouro("Rua Z");
    endereco.setBairro("Centro");
    endereco.setCidade("Brasília");
    endereco.setEstado("DF");
    endereco.setNumero(111);
    dto.setEndereco(endereco);

    dto.setTelefones(Arrays.asList(new TelefoneDTO("FIXO", "6133333333")));
    dto.setEmails(Arrays.asList("cpf@invalido.com"));

    mockMvc.perform(post("/clientes")
                    .with(httpBasic("admin", "123qwe!@#"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").value("Validação falhou"))
            .andExpect(jsonPath("$.messages").isArray());
}

@Test
public void deveAtualizarClienteComSucesso() throws Exception {
    ClienteRequestDTO dtoAtualizado = new ClienteRequestDTO();
    dtoAtualizado.setNome("Maria Atualizada");
    dtoAtualizado.setCpf("32165498700");

    EnderecoRequestDTO endereco = new EnderecoRequestDTO();
    endereco.setCep("71010100");
    endereco.setLogradouro("Rua Atualizada");
    endereco.setBairro("Setor Atualizado");
    endereco.setCidade("Brasília");
    endereco.setEstado("DF");
    endereco.setNumero(999);
    dtoAtualizado.setEndereco(endereco);

    dtoAtualizado.setTelefones(Arrays.asList(new TelefoneDTO("CELULAR", "61999999900")));
    dtoAtualizado.setEmails(Arrays.asList("maria.atualizada@email.com"));

    mockMvc.perform(put("/clientes/" + clienteIdCriado)
            .with(httpBasic("admin", "123qwe!@#"))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dtoAtualizado)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nome").value("Maria Atualizada"));
}

@Test
public void deveDeletarClienteComSucesso() throws Exception {
    mockMvc.perform(delete("/clientes/" + clienteIdCriado)
                    .with(httpBasic("admin", "123qwe!@#")))
            .andExpect(status().isNoContent());

    // Verifica que ele não existe mais
    mockMvc.perform(get("/clientes/" + clienteIdCriado)
                    .with(httpBasic("admin", "123qwe!@#")))
            .andExpect(status().isNotFound());
}


}

