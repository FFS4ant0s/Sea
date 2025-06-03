export function setAuthHeader(token: string) {
  localStorage.setItem('basicAuth', token);

  // decodifica para obter o username
  const decoded = atob(token);
  const username = decoded.split(':')[0];
  localStorage.setItem('authUser', username);
}

export function getAuthHeader(): string {
  return localStorage.getItem('basicAuth') || '';
}

export function getLoggedUser(): string {
  return localStorage.getItem('authUser') || '';
}

export function isAdmin(): boolean {
  return getLoggedUser() === 'admin';
}

export function getToken(): string | null {
  return localStorage.getItem('basicAuth'); 
}