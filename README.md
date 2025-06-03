# ClientManager

Sistema completo para gerenciamento de clientes, com backend em Java Spring Boot e frontend em React + Vite.

---

## 🛠️ Tecnologias Utilizadas

### Backend
- Java 8
- Spring Boot 2.7.18
- Spring Data JPA
- Spring Security (autenticação HTTP Basic)
- Bean Validation (JSR 380)
- H2 Database (ambiente dev/teste)
- Swagger 3.0 (Springfox)
- JUnit 5 + Spring Test (MockMvc)

### Frontend
- React 18+
- Vite (ferramenta de build)
- CSS moderno para estilização

---

## 📦 Como Executar o Projeto

### Backend

1. Pré-requisitos:
   - JDK 8
   - Maven 3.6+
   - Git (opcional)

2. Clone o repositório e acesse a pasta do backend:
   ```bash
   git clone https://github.com/FFS4ant0s/Sea_Projeto.git
   cd clientmanager_Back
Execute a aplicação:

mvn spring-boot:run

Acesse no navegador:

API: http://localhost:8080/clientes

Swagger: http://localhost:8080/swagger-ui/

Autenticação:

Usuário: admin / user

Senha: 123qwe!@# / 123qwe123

Frontend
Pré-requisitos:

Node.js 16+

npm ou yarn

Instale as dependências:

npm install
# ou
yarn install

Execute em modo desenvolvimento:

npm run dev
# ou
yarn dev

O frontend estará disponível em:

http://localhost:5173

Para gerar o build de produção:

📋 Endpoints Principais (Backend)(admin)
Método	Endpoint	Descrição
GET	/clientes	Lista todos os clientes (user/admin)
GET	/clientes/{id}	Busca cliente por ID  (user/admin)
POST	/clientes	Cria novo cliente (admin)
PUT	/clientes/{id}	Atualiza cliente existente (admin)
DELETE	/clientes/{id}	Deleta cliente por ID (admin)

✅ Testes
Os testes de integração estão localizados em:
src/test/java/com/sea/backend/clientmanager/controller/ClienteControllerIntegrationTest.java

Para executar os testes:

mvn test

⚙️ Configurações Adicionais
Banco de dados H2 está configurado em modo memória (in-memory).

Todas as requisições são protegidas por autenticação HTTP Basic.

Tratamento global de erros via @ControllerAdvice.

CSS do frontend está em arquivo .css dentro da pasta src
