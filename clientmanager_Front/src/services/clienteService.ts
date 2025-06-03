import axios from "axios";

// URL base da API
const API_URL = "http://localhost:8080/clientes";

// Tipos
export interface Cliente {
  id?: number;
  nome: string;
  cpf: string;
  endereco: Endereco;
  telefones: Telefone[];
  emails: string[];
}
export interface Endereco {
  cep: string;
  logradouro: string;
  numero: string;
  complemento?: string;
  bairro: string;
  cidade: string;
  estado: string;
}

export interface Telefone {
  id?: number;
  tipo: string;
  numero: string;
}

export interface Email {
  email: string;
}

// Listar todos os clientes
export const listarClientes = async (token: string): Promise<Cliente[]> => {
  const response = await axios.get<Cliente[]>(API_URL, {
    headers: {
      Authorization: `Basic ${token}`,
    },
  });
  return response.data;
};

// Buscar cliente por ID
export const buscarClientePorId = async (id: number, token: string): Promise<Cliente> => {
  const response = await axios.get<Cliente>(`${API_URL}/${id}`, {
    headers: {
      Authorization: `Basic ${token}`,
    },
  });
  return response.data;
};

// Criar novo cliente
export const criarCliente = async (cliente: Cliente, token: string) => {
  const response = await axios.post(API_URL, cliente, {
    headers: {
      Authorization: `Basic ${token}`,
    },
  });
  return response.data;
};

// Atualizar cliente existente
export const atualizarCliente = async (id: number, cliente: Cliente, token: string) => {
  const response = await axios.put(`${API_URL}/${id}`, cliente, {
    headers: {
      Authorization: `Basic ${token}`,
    },
  });
  return response.data;
};

// Deletar cliente
export const deletarCliente = async (id: number, token: string) => {
  const response = await axios.delete(`${API_URL}/${id}`, {
    headers: {
      Authorization: `Basic ${token}`,
    },
  });
  return response.data;
};
