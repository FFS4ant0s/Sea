import { useEffect, useState } from 'react';
import { getAuthHeader } from '../utils/auth';

interface Cliente {
  id: number;
  nome: string;
  cpf: string;
}

export default function Clientes() {
  const [clientes, setClientes] = useState<Cliente[]>([]);

  useEffect(() => {
    fetch('http://localhost:8080/clientes', {
      headers: {
        Authorization: `Basic ${getAuthHeader()}`,
      },
    })
      .then(res => res.json())
      .then(data => setClientes(data))
      .catch(() => alert('Erro ao carregar clientes'));
  }, []);

  return (
    <div className="container mt-5">
      <h2>Lista de Clientes</h2>
      <ul className="list-group">
        {clientes.map(cliente => (
          <li key={cliente.id} className="list-group-item">
            <strong>{cliente.nome}</strong> - CPF: {cliente.cpf}
          </li>
        ))}
      </ul>
    </div>
  );
}