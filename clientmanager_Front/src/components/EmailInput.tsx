import React from "react";
import { Email } from "../services/clienteService";

interface Props {
  emails: Email[];
  onChange: (index: number, value: string) => void;
}

const EmailInput: React.FC<Props> = ({ emails, onChange }) => {
  return (
    <>
      <h4>E-mails</h4>
      {emails.map((email, index) => (
        <div key={index}>
          <input
            type="email"
            placeholder="E-mail"
            value={email.email}
            onChange={(e) => onChange(index, e.target.value)}
          />
        </div>
      ))}
    </>
  );
};

export default EmailInput;