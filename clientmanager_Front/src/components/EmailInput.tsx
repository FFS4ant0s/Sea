interface Props {
  emails: string[];
  onChange: (index: number, value: string) => void;
  onAdd: () => void;
  onRemove: (index: number) => void;
}

const EmailInput: React.FC<Props> = ({ emails, onChange, onAdd, onRemove }) => (
  <div>
    <h4>E-mails</h4>
    {emails.map((email, index) => (
      <div key={index}>
        <input
          type="email"
          placeholder="E-mail"
          value={email}
          onChange={(e) => onChange(index, e.target.value)}
          required
        />
        <button type="button" onClick={() => onRemove(index)}>Remover</button>
      </div>
    ))}
    <button type="button" onClick={onAdd}>Adicionar E-mail</button>
  </div>
);
