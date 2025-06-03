import axios from "axios";

// Defina a URL base da sua API
const API_URL = "http://localhost:8080/clientes";

// Tipo Cliente
export interface Cliente {
  id?: number;
  nome: string;
  cpf: string;
  cep: string;
  logradouro: string;
  complemento?: string;
  bairro: string;
  cidade: string;
  uf: string;
  telefones: Telefone[];
  emails: Email[];
}

export interface Telefone {
  id?: number;
  tipo: string;
  numero: string;
}

export interface Email {
  id?: number;
  email: string;
}

export const listarClientes = async (token: string): Promise<Cliente[]> => {
  const response = await axios.get<Cliente[]>(API_URL, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
};

// Função para buscar um cliente pelo ID
export const buscarClientePorId = async (id: number, token: string): Promise<Cliente> => {
  const response = await axios.get<Cliente>(`${API_URL}/${id}`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

// Função para criar um novo cliente
export const criarCliente = async (cliente: Cliente, token: string) => {
  const response = await axios.post(API_URL, cliente, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
};

// Função para atualizar um cliente
export const atualizarCliente = async (id: number, cliente: Cliente, token: string) => {
  const response = await axios.put(`${API_URL}/${id}`, cliente, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
};

// Função para deletar um cliente
export const deletarCliente = async (id: number, token: string) => {
  const response = await axios.delete(`${API_URL}/${id}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
};