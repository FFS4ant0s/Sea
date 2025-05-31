# ClientManager API

Sistema backend desenvolvido em Java com Spring Boot para o gerenciamento de clientes, incluindo cadastro de endereço, telefone e e-mail.

## 🛠️ Tecnologias Utilizadas

- Java 8
- Spring Boot 2.7.18
- Spring Data JPA
- Spring Security (básico com autenticação HTTP Basic)
- Bean Validation (JSR 380)
- H2 Database (para ambiente de desenvolvimento/teste)
- Swagger 3.0 (Springfox)
- JUnit 5 + Spring Test (MockMvc)

## 📦 Como Executar o Projeto

1. **Pré-requisitos**
   - JDK 8
   - Maven 3.6+
   - Git (opcional)

2. **Clone o repositório**
   ```bash
   git clone https://github.com/FFS4ant0s/Sea_Projeto.git
   cd clientmanager_Back
Execute a aplicação

mvn spring-boot:run
Acesse no navegador

API: http://localhost:8080/clientes

Swagger: http://localhost:8080/swagger-ui/

🔐 Autenticação
A aplicação está protegida por autenticação via HTTP Basic.

Usuário: admin / user

Senha: 123qwe!@# / 123qwe123

📋 Endpoints principais
Método	Endpoint	Descrição
GET	/clientes	Lista todos os clientes
GET	/clientes/{id}	Busca cliente por ID
POST	/clientes	Cria novo cliente
PUT	/clientes/{id}	Atualiza cliente existente
DELETE	/clientes/{id}	Deleta cliente por ID

✅ Testes
Os testes de integração estão localizados em:
src/test/java/com/sea/backend/clientmanager/controller/ClienteControllerIntegrationTest.java

Execute os testes com:
mvn test

⚙️ Configurações adicionais
O banco de dados H2 está configurado no modo memória (in-memory).

Todas as requisições devem ser autenticadas.

O tratamento global de erros está implementado via @ControllerAdvice.
