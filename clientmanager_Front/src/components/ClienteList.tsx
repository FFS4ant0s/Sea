import React, { useEffect, useState } from "react";
import { listarClientes, deletarCliente, Cliente } from "../services/clienteService";
import { getToken, isAdmin } from "../utils/auth";
import { Link } from "react-router-dom";
import "../index.css";

const ClienteList: React.FC = () => {
  const [clientes, setClientes] = useState<Cliente[]>([]);
  const admin = isAdmin();

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

      {admin && (
        <Link to="/clientes/novo">
          <button>Novo Cliente</button>
        </Link>
      )}

      <ul className="cliente-lista">
        {clientes.map((cliente) => (
          <li key={cliente.id} className="cliente-card">
            <div className="cliente-info">
              <p><strong>Nome:</strong> {cliente.nome}</p>
              <p><strong>CPF:</strong> {cliente.cpf}</p>
              <p><strong>Endereço:</strong> {`${cliente.endereco.logradouro}, ${cliente.endereco.numero}, ${cliente.endereco.bairro}, ${cliente.endereco.cidade} - ${cliente.endereco.estado}, CEP: ${cliente.endereco.cep}`}</p>
              <p><strong>Telefones:</strong> {cliente.telefones.map(t => t.numero).join(", ")}</p>
              <p><strong>E-mails:</strong> {cliente.emails.join(", ")}</p>
            </div>
            {admin && (
              <div className="cliente-acoes">
                <Link to={`/clientes/${cliente.id}`}>
                  <button>Editar</button>
                </Link>
                <button onClick={() => handleDelete(cliente.id!)}>Excluir</button>
              </div>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ClienteList;