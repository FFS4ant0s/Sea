import React, { useEffect, useState } from "react";
import { listarClientes, deletarCliente, Cliente } from "../services/clienteService";
import { getToken } from "../utils/auth";
import { Link } from "react-router-dom";

const ClienteList: React.FC = () => {
  const [clientes, setClientes] = useState<Cliente[]>([]);

  const carregarClientes = async () => {
  try {
    const token = getToken();
    if (!token) {
      console.error("Usuário não autenticado");
      return;
    }
    const dados = await listarClientes(token);
    setClientes(dados);
  } catch (error) {
    console.error("Erro ao listar clientes:", error);
  }
};

  useEffect(() => {
    carregarClientes();
  }, []);

  const handleDelete = async (id: number) => {
  try {
    const token = getToken();
    if (!token) {
      console.error("Usuário não autenticado");
      return;
    }
    await deletarCliente(id, token);
    await carregarClientes();
  } catch (error) {
    console.error("Erro ao excluir cliente:", error);
  }
};

  return (
    <div>
      <h2>Lista de Clientes</h2>
      <Link to="/clientes/novo">
        <button>Novo Cliente</button>
      </Link>
      <ul>
        {clientes.map((cliente) => (
          <li key={cliente.id}>
            {cliente.nome} - {cliente.cpf}
            <Link to={`/clientes/${cliente.id}`}>
              <button>Editar</button>
            </Link>
            <button onClick={() => handleDelete(cliente.id!)}>Excluir</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ClienteList;