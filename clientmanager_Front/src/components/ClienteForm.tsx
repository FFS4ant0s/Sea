import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import {
  Cliente,
  criarCliente,
  atualizarCliente,
  buscarClientePorId,
} from "../services/clienteService";
import { getToken } from "../utils/auth";
import { buscarEnderecoPorCep } from "../services/cepService";

// Máscara CPF
const aplicarMascaraCpf = (cpf: string): string =>
  cpf
    .replace(/\D/g, "")
    .replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4")
    .slice(0, 14);

// Máscara CEP
const aplicarMascaraCep = (cep: string): string =>
  cep.replace(/\D/g, "").replace(/(\d{5})(\d{3})/, "$1-$2").slice(0, 9);

// Máscara Telefone por tipo
const aplicarMascaraTelefone = (numero: string, tipo: string): string => {
  const nums = numero.replace(/\D/g, "");
  if (tipo === "CELULAR") {
    return nums.replace(/(\d{2})(\d{5})(\d{0,4})/, "($1)$2-$3").slice(0, 14);
  }
  return nums.replace(/(\d{2})(\d{4})(\d{0,4})/, "($1)$2-$3").slice(0, 13);
};

const ClienteForm: React.FC = () => {
  const [cliente, setCliente] = useState<Cliente>({
    nome: "",
    cpf: "",
    endereco: {
      cep: "",
      logradouro: "",
      numero: "",
      complemento: "",
      bairro: "",
      cidade: "",
      estado: "",
    },
    telefones: [{ tipo: "CELULAR", numero: "" }],
    emails: [""],
  });

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (id) carregarCliente(Number(id));
  }, [id]);

  const carregarCliente = async (clienteId: number) => {
    try {
      const token = getToken();
      if (!token) return;
      const dados = await buscarClientePorId(clienteId, token);
      setCliente(dados);
    } catch (error) {
      console.error("Erro ao carregar cliente:", error);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    // Validação nome
    const nomeRegex = /^[A-Za-z0-9À-ÿ\s]+$/;
    if (
      !nomeRegex.test(cliente.nome) ||
      cliente.nome.length < 3 ||
      cliente.nome.length > 100
    ) {
      alert(
        "Nome inválido. Deve conter apenas letras, números e espaços, entre 3 e 100 caracteres."
      );
      return;
    }

    // Validação e-mails
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    for (const email of cliente.emails) {
      if (!emailRegex.test(email)) {
        alert(`E-mail inválido: ${email}`);
        return;
      }
    }

    try {
      const token = getToken();
      if (!token) {
        alert("Usuário não autenticado.");
        return;
      }

      const clienteFormatado = {
        ...cliente,
        cpf: cliente.cpf.replace(/\D/g, ""),
        endereco: {
          ...cliente.endereco,
          cep: cliente.endereco.cep.replace(/\D/g, ""),
        },
        telefones: cliente.telefones.map((t) => ({
          ...t,
          numero: t.numero.replace(/\D/g, ""),
        })),
      };

      if (id) {
        await atualizarCliente(Number(id), clienteFormatado, token);
        alert("Cliente atualizado com sucesso!");
      } else {
        await criarCliente(clienteFormatado, token);
        alert("Cliente cadastrado com sucesso!");
      }

      navigate("/clientes");
    } catch (error) {
      console.error("Erro ao salvar cliente:", error);
      alert("Erro ao salvar cliente.");
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    if (name === "nome") {
      const regex = /^[A-Za-z0-9À-ÿ\s]{0,100}$/;
      if (!regex.test(value)) return;
    }

    setCliente({
      ...cliente,
      [name]: name === "cpf" ? aplicarMascaraCpf(value) : value,
    });
  };

  const handleEnderecoChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setCliente({
      ...cliente,
      endereco: {
        ...cliente.endereco,
        [name]: name === "cep" ? aplicarMascaraCep(value) : value,
      },
    });
  };

  const handleCepBlur = async () => {
    try {
      const enderecoAPI = await buscarEnderecoPorCep(cliente.endereco.cep);
      setCliente({
        ...cliente,
        endereco: {
          ...cliente.endereco,
          logradouro: enderecoAPI.logradouro,
          bairro: enderecoAPI.bairro,
          cidade: enderecoAPI.localidade,
          estado: enderecoAPI.uf,
        },
      });
    } catch (error) {
      console.error("Erro ao buscar endereço:", error);
    }
  };

  const handleTelefoneChange = (
    index: number,
    campo: string,
    valor: string
  ) => {
    const telefones = [...cliente.telefones];
    if (campo === "numero") {
      telefones[index].numero = aplicarMascaraTelefone(valor, telefones[index].tipo);
    } else {
      telefones[index].tipo = valor as "CELULAR" | "RESIDENCIAL" | "COMERCIAL";
      telefones[index].numero = aplicarMascaraTelefone(telefones[index].numero, valor);
    }
    setCliente({ ...cliente, telefones });
  };

  const handleAddTelefone = () => {
    setCliente({
      ...cliente,
      telefones: [...cliente.telefones, { tipo: "CELULAR", numero: "" }],
    });
  };

  const handleRemoveTelefone = (index: number) => {
    const telefones = cliente.telefones.filter((_, i) => i !== index);
    setCliente({ ...cliente, telefones });
  };

  const handleEmailChange = (index: number, value: string) => {
    const emails = [...cliente.emails];
    emails[index] = value;
    setCliente({ ...cliente, emails });
  };

  const handleAddEmail = () => {
    setCliente({ ...cliente, emails: [...cliente.emails, ""] });
  };

  const handleRemoveEmail = (index: number) => {
    const emails = cliente.emails.filter((_, i) => i !== index);
    setCliente({ ...cliente, emails });
  };

  return (
    <div className="form-container">
      <h2>{id ? "Editar Cliente" : "Cadastrar Cliente"}</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="nome"
          placeholder="Nome"
          value={cliente.nome}
          onChange={handleChange}
          required
          maxLength={100}
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
          value={cliente.endereco.cep}
          onChange={handleEnderecoChange}
          onBlur={handleCepBlur}
          required
        />
        <input
          type="text"
          name="logradouro"
          placeholder="Logradouro"
          value={cliente.endereco.logradouro}
          onChange={handleEnderecoChange}
        />
        <input
          type="text"
          name="numero"
          placeholder="Número"
          value={cliente.endereco.numero}
          onChange={handleEnderecoChange}
          required
        />
        <input
          type="text"
          name="complemento"
          placeholder="Complemento"
          value={cliente.endereco.complemento}
          onChange={handleEnderecoChange}
        />
        <input
          type="text"
          name="bairro"
          placeholder="Bairro"
          value={cliente.endereco.bairro}
          onChange={handleEnderecoChange}
        />
        <input
          type="text"
          name="cidade"
          placeholder="Cidade"
          value={cliente.endereco.cidade}
          onChange={handleEnderecoChange}
        />
        <input
          type="text"
          name="estado"
          placeholder="Estado"
          value={cliente.endereco.estado}
          onChange={handleEnderecoChange}
        />

        <h4>Telefones</h4>
        {cliente.telefones.map((tel, index) => (
          <div key={index}>
            <select
              value={tel.tipo}
              onChange={(e) => handleTelefoneChange(index, "tipo", e.target.value)}
            >
              <option value="CELULAR">Celular</option>
              <option value="RESIDENCIAL">Residencial</option>
              <option value="COMERCIAL">Comercial</option>
            </select>
            <input
              type="text"
              placeholder="Telefone"
              value={tel.numero}
              onChange={(e) => handleTelefoneChange(index, "numero", e.target.value)}
              required
            />
            {cliente.telefones.length > 1 && (
              <button type="button" onClick={() => handleRemoveTelefone(index)}>
                Remover
              </button>
            )}
          </div>
        ))}
        <button type="button" onClick={handleAddTelefone}>
          Adicionar Telefone
        </button>

        <h4>E-mails</h4>
        {cliente.emails.map((email, index) => (
          <div key={index}>
            <input
              type="email"
              placeholder="E-mail"
              value={email}
              onChange={(e) => handleEmailChange(index, e.target.value)}
              required
            />
            {cliente.emails.length > 1 && (
              <button type="button" onClick={() => handleRemoveEmail(index)}>
                Remover
              </button>
            )}
          </div>
        ))}
        <button type="button" onClick={handleAddEmail}>
          Adicionar E-mail
        </button>

        <button type="submit">{id ? "Atualizar" : "Cadastrar"}</button>
      </form>
    </div>
  );
};

export default ClienteForm;