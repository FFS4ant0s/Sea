import React from "react";
import { Telefone } from "../services/clienteService";

interface Props {
  telefones: Telefone[];
  onChange: (index: number, value: string) => void;
}

const TelefoneInput: React.FC<Props> = ({ telefones, onChange }) => {
  return (
    <>
      <h4>Telefones</h4>
      {telefones.map((tel, index) => (
        <div key={index}>
          <input
            type="text"
            placeholder="Telefone"
            value={tel.numero}
            onChange={(e) => onChange(index, e.target.value)}
          />
        </div>
      ))}
    </>
  );
};

export default TelefoneInput;