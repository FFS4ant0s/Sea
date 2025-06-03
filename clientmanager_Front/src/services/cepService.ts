import axios from "axios";

export interface AddressResponse {
  cep: string;
  logradouro: string;
  complemento: string;
  bairro: string;
  localidade: string;
  uf: string;
  ibge: string;
  gia: string;
  ddd: string;
  siafi: string;
  erro?: boolean;
}

// Busca endereço pelo CEP
export const buscarEnderecoPorCep = async (cep: string): Promise<AddressResponse> => {
  try {
    const response = await axios.get<AddressResponse>(`https://viacep.com.br/ws/${cep}/json/`);

    if (response.data.erro) {
      throw new Error("CEP não encontrado.");
    }

    return response.data;
  } catch (error) {
    console.error("Erro ao buscar CEP:", error);
    throw error;
  }
};