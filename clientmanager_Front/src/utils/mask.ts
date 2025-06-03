export const aplicarMascaraCpf = (cpf: string): string =>
  cpf.replace(/\D/g, "").replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4").slice(0, 14);

export const aplicarMascaraCep = (cep: string): string =>
  cep.replace(/\D/g, "").replace(/(\d{5})(\d{3})/, "$1-$2").slice(0, 9);

export const aplicarMascaraTelefone = (numero: string): string => {
  const nums = numero.replace(/\D/g, "");
  if (nums.length <= 10) return nums.replace(/(\d{2})(\d{4})(\d{0,4})/, "($1)$2-$3").slice(0, 13);
  return nums.replace(/(\d{2})(\d{5})(\d{0,4})/, "($1)$2-$3").slice(0, 14);
};

export const removerMascara = (valor: string): string => valor.replace(/\D/g, "");
