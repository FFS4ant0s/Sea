export function setAuthHeader(token: string) {
  localStorage.setItem('basicAuth', token);
}

export function getAuthHeader(): string {
  return localStorage.getItem('basicAuth') || '';
}