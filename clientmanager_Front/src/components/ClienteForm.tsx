import React, { useState, useEffect } from "react";
import { Cliente, criarCliente, atualizarCliente, buscarClientePorId } from "../services/clienteService";
import { buscarEnderecoPorCep } from "../services/cepService";
import { getToken } from "../utils/auth";
import { useParams, useNavigate } from "react-router-dom";

const ClienteForm: React.FC = () => {
  const [cliente, setCliente] = useState<Cliente>({
    nome: "",
    cpf: "",
    cep: "",
    logradouro: "",
    complemento: "",
    bairro: "",
    cidade: "",
    uf: "",
    telefones: [{ tipo: "CELULAR", numero: "" }],
    emails: [{ email: "" }],
  });

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (id) {
      carregarCliente(Number(id));
    }
  }, [id]);

  const carregarCliente = async (clienteId: number) => {
  try {
    const token = getToken();
    if (!token) {
      console.error("Token não encontrado. Usuário não autenticado.");
      return;
    }
    const dados = await buscarClientePorId(clienteId, token);
    setCliente(dados);
  } catch (error) {
    console.error("Erro ao carregar cliente:", error);
  }
};

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setCliente({ ...cliente, [name]: value });
  };

  const handleCepBlur = async () => {
    try {
      const endereco = await buscarEnderecoPorCep(cliente.cep);
      setCliente({
        ...cliente,
        logradouro: endereco.logradouro,
        bairro: endereco.bairro,
        cidade: endereco.localidade,
        uf: endereco.uf,
      });
    } catch (error) {
      console.error("Erro ao buscar endereço:", error);
    }
  };

  const handleTelefoneChange = (index: number, value: string) => {
    const telefones = [...cliente.telefones];
    telefones[index].numero = value;
    setCliente({ ...cliente, telefones });
  };

  const handleEmailChange = (index: number, value: string) => {
    const emails = [...cliente.emails];
    emails[index].email = value;
    setCliente({ ...cliente, emails });
  };

  const handleSubmit = async (e: React.FormEvent) => {
  e.preventDefault();
  try {
    const token = getToken();
    if (!token) {
      alert("Usuário não autenticado. Faça login para continuar.");
      return;
    }

    if (id) {
      await atualizarCliente(Number(id), cliente, token);
      alert("Cliente atualizado com sucesso!");
    } else {
      await criarCliente(cliente, token);
      alert("Cliente cadastrado com sucesso!");
    }
    navigate("/clientes");
  } catch (error) {
    console.error("Erro ao salvar cliente:", error);
  }
};

  return (
    <div>
      <h2>{id ? "Editar Cliente" : "Cadastrar Cliente"}</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="nome"
          placeholder="Nome"
          value={cliente.nome}
          onChange={handleChange}
          required
        />

        <input
          type="text"
          name="cpf"
          placeholder="CPF"
          value={cliente.cpf}
          onChange={handleChange}
          required
        />

        <input
          type="text"
          name="cep"
          placeholder="CEP"
          value={cliente.cep}
          onChange={handleChange}
          onBlur={handleCepBlur}
          required
        />

        <input
          type="text"
          name="logradouro"
          placeholder="Logradouro"
          value={cliente.logradouro}
          onChange={handleChange}
        />

        <input
          type="text"
          name="complemento"
          placeholder="Complemento"
          value={cliente.complemento}
          onChange={handleChange}
        />

        <input
          type="text"
          name="bairro"
          placeholder="Bairro"
          value={cliente.bairro}
          onChange={handleChange}
        />

        <input
          type="text"
          name="cidade"
          placeholder="Cidade"
          value={cliente.cidade}
          onChange={handleChange}
        />

        <input
          type="text"
          name="uf"
          placeholder="UF"
          value={cliente.uf}
          onChange={handleChange}
        />

        <h4>Telefones</h4>
        {cliente.telefones.map((tel, index) => (
          <div key={index}>
            <input
              type="text"
              placeholder="Telefone"
              value={tel.numero}
              onChange={(e) => handleTelefoneChange(index, e.target.value)}
            />
          </div>
        ))}

        <h4>E-mails</h4>
        {cliente.emails.map((email, index) => (
          <div key={index}>
            <input
              type="email"
              placeholder="E-mail"
              value={email.email}
              onChange={(e) => handleEmailChange(index, e.target.value)}
            />
          </div>
        ))}

        <button type="submit">{id ? "Atualizar" : "Cadastrar"}</button>
      </form>
    </div>
  );
};

export default ClienteForm;