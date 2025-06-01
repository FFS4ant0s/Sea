const API_URL = 'http://localhost:8080/clientes';

const authHeaders = (username, password) => ({
  'Content-Type': 'application/json',
  'Authorization': 'Basic ' + btoa(`${username}:${password}`)
});

export const getClientes = async (username, password) => {
  const res = await fetch(API_URL, {
    method: 'GET',
    headers: authHeaders(username, password)
  });
  if (!res.ok) throw new Error('Erro ao buscar clientes');
  return res.json();
};
