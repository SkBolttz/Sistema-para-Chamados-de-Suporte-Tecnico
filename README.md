# 📌 Sistema de Gestão de Chamados para Suporte Técnico

## 📖 Descrição
O **Sistema de Gestão de Chamados** é um sistema completo de help desk para registrar e gerenciar chamados de suporte técnico. Usuários podem abrir tickets, acompanhar o status até a resolução do problema.

## 🚀 Funcionalidades Principais

### ✅ Autenticação e Autorização
- Usuários comuns podem abrir chamados.
- Técnicos têm acesso aos chamados para atendê-los.
- Administradores gerenciam usuários e chamados.

### ✅ Abertura de Chamados
- Usuários podem descrever o problema.
- Cada chamado recebe um **ID único** e uma **prioridade** (Baixa, Média, Alta, Crítica). ( A prioridade eleva a cada 20 minutos do chamado aberto )
- O status inicial do chamado é **"Aberto"**.

### ✅ Gerenciamento de Chamados
- Técnicos podem alterar o status para **"Em Andamento"** ou **"Encerrado"**.
- Logs de interações são armazenados para rastreamento.
- Atualizações em tempo real via **WebSockets**.

### ✅ Persistência no Banco de Dados
- **PostgreSQL** para armazenar usuários, chamados e logs.

### ✅ API REST para Integração Externa
- Endpoints para **criação, listagem e atualização** de chamados.
- Segurança com **JWT** para autenticação.

## 📌 Rotas da API

### 🔹 Autenticação
- `POST /api/auth/login/usuario` → Realiza login e retorna um token JWT.
- `POST /api/auth/registrar/usuario` → Registra um novo usuário.
- `POST /api/auth/registrar/tecnico` → Registra um novo técnico.
- `POST /api/auth/registrar/administrador` → Registra um novo administrador.

### 🔹 Usuários
- `PUT /usuario/deletar` → Remove o usuário.
- `PUT /usuario/reativar` → Reativa o usuário.
- `PUT /usuario/atualizar}` → Atualiza informações do usuário.
- `GET /usuario/listar/todos/usuarios` → Lista todos os usuários.
- `GET /usuario/listar/todos/tecnicos` → Lista todos os técnicos.
- `GET /usuario/listar/todos/administradores` → Lista todos os administradores.

### 🔹 Chamados
- `POST /chamados/registrar` → Cria um novo chamado.
- `GET /chamados/listar/chamados/abertos` → Lista todos os chamados.
- `PUT /chamados/atender` → Atende o chamado.
- `PUT /chamados/finalizar` → Finaliza o chamado.
- 
## 🛠️ Tecnologias Utilizadas

- **Spring Boot** – Backend principal.
- **Spring Security + JWT** – Autenticação e autorização.
- **Spring Data JPA** – Manipulação do banco de dados.
- **PostgreSQL** – Persistência dos dados.
- **WebSockets** – Atualizações em tempo real.
- **Swagger/OpenAPI** – Documentação da API.

## 🎯 Objetivos do Projeto
Esse projeto foi desenvolvido para proporcionar desafios em diversas áreas, incluindo:

- **Segurança**: Implementação de JWT para proteger os endpoints.
- **Arquitetura RESTful**: Organização do sistema seguindo boas práticas.
- **Integrações em tempo real**: Uso de WebSockets e notificações.
- **Escalabilidade**: Design para suportar múltiplos usuários simultaneamente.

## 📦 Como Rodar o Projeto

### 🔹 Pré-requisitos
Antes de começar, você precisará ter instalado:
- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Maven](https://maven.apache.org/download.cgi)
---
💡 **Desenvolvido por [Pedro Borba](https://github.com/SkBolttz)**

