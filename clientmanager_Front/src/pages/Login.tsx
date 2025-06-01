import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { setAuthHeader } from '../utils/auth';
import 'bootstrap/dist/css/bootstrap.min.css';

export default function Login() {
  const [username, setUsername] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const token = btoa(`${username}:${password}`);
    setAuthHeader(token);

    try {
      const res = await fetch('http://localhost:8080/clientes', {
        headers: { Authorization: `Basic ${token}` },
      });

      if (res.ok) {
        navigate('/clientes');
      } else {
        alert('Credenciais inválidas');
      }
    } catch (err) {
      alert('Erro ao conectar com o servidor');
    }
  };

  return (
    <div className="container mt-5">
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <div className="mb-3">
          <label htmlFor="username">Usuário</label>
          <input
            id="username"
            className="form-control"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Digite seu usuário"
            title="Usuário"
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="password">Senha</label>
          <input
            id="password"
            type="password"
            className="form-control"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Digite sua senha"
            title="Senha"
            required
          />
        </div>
        <button className="btn btn-primary" type="submit">Entrar</button>
      </form>
    </div>
  );
}