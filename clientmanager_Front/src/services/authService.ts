import axios from "axios";

const API_URL = "http://localhost:8080";

// Faz login no sistema tentando acessar um endpoint protegido com Basic Auth
export const login = async (username: string, password: string) => {
  const basicAuth = btoa(`${username}:${password}`);

  // Verifica se as credenciais funcionam acessando o endpoint protegido
  await axios.get(`${API_URL}/clientes`, {
    headers: {
      "Authorization": `Basic ${basicAuth}`,
    },
  });

  // Se deu certo, salva no localStorage
  setAuthHeader(basicAuth); // isso já salva o token e o usuário
};

// Salva token e username corretamente no localStorage
export function setAuthHeader(token: string) {
  localStorage.setItem('basicAuth', token);

  // Extrai o username do token base64
  const decoded = atob(token); // exemplo: "admin:senha"
  const username = decoded.split(':')[0].trim();
  localStorage.setItem('authUser', username);
}

// Logout — limpa os dados do localStorage
export const logout = () => {
  localStorage.removeItem("basicAuth");
  localStorage.removeItem("authUser");
};

// Recupera o header para autenticação nas requisições
export function getAuthHeader(): string {
  return localStorage.getItem('basicAuth') || '';
}

// Recupera o usuário logado
export function getLoggedUser(): string {
  return localStorage.getItem('authUser') || '';
}

// Verifica se o usuário é admin
export function isAdmin(): boolean {
  return getLoggedUser() === 'admin';
}

// Verifica se está autenticado
export function isAuthenticated(): boolean {
  return !!localStorage.getItem("basicAuth");
}

// Recupera apenas o token bruto
export function getToken(): string | null {
  return localStorage.getItem("basicAuth");
}