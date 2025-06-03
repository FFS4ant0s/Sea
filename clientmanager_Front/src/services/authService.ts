import axios from "axios";

const API_URL = "http://localhost:8080";

// Faz login no sistema tentando acessar um endpoint protegido com Basic Auth
export const login = async (username: string, password: string) => {
  const basicAuth = btoa(`${username}:${password}`);

  // Tenta acessar o /clientes só pra validar login
  await axios.get(`${API_URL}/clientes`, {
    headers: {
      "Authorization": `Basic ${basicAuth}`,
    },
  });

  // Se chegou aqui, login deu certo — salva no localStorage
  localStorage.setItem("basicAuth", basicAuth);
  localStorage.setItem("username", username);
};

// Logout — remove token e user do localStorage
export const logout = () => {
  localStorage.removeItem("basicAuth");
  localStorage.removeItem("username");
};

// Recupera o Authorization Header
export const getAuthHeader = () => {
  const token = localStorage.getItem("basicAuth");
  return token ? { "Authorization": `Basic ${token}` } : {};
};

// Verifica se usuário está autenticado
export const isAuthenticated = (): boolean => {
  return !!localStorage.getItem("basicAuth");
};

// Recupera usuário logado
export const getCurrentUser = (): string | null => {
  return localStorage.getItem("username");
};