# TP3 Microservices

## Arquitetura da Solução

A aplicação foi estruturada seguindo o padrão em camadas e os princípios de microsserviços e APIs RESTful:

- **Controller (`/controller`):** Disponibiliza os endpoints públicos e protegidos.
- **Service (`/service`):** Regras de negócio, geração/validação de tokens JWT e gestão de passwords.
- **Security (`/config`, `/security`):** Configuração do Spring Security e filtro personalizado para validação de tokens em cada requisição.
- **Repository (`/repository`):** Interface de acesso aos dados persistidos via Spring Data JPA.
- **Model (`/model`):** Entidades de domínio JPA (`Usuario` e `Cliente`).
- **DTO (`/dto`):** Objetos de transferência de dados para requisições e respostas.

---

## Tecnologia Escolhida

Foi utilizada a tecnologia **JWT (JSON Web Token)** com a biblioteca **JJWT (`io.jsonwebtoken`)** integrada ao **Spring Security**.

### Justificativa
- **Stateless:** A API não armazena sessões no servidor, permitindo escalabilidade horizontal.
- **Assinatura HMAC-SHA256:** Garante a integridade do token emitido.
- **Refresh Token:** Permite renovar a credencial de acesso expirada sem a necessidade de solicitar as credenciais do utilizador novamente.

---

## Endpoints da Aplicação

### Endpoints Públicos
- `POST /usuarios` - Cadastro de novos utilizadores.
- `POST /usuarios/login` - Autenticação e emissão de tokens.
- `POST /usuarios/refresh` - Renovação do token de acesso expirado.

### Endpoints Protegidos
- `GET /clientes` - Listagem de clientes (Exige token Bearer válido).

---

## Como Executar o Serviço

### Pré-requisitos
- **Java 17** ou superior instalado.
- **Maven 3.8+** instalado.

### Passo a Passo

1. Compile o projeto:
```bash
mvn clean install
