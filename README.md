# 📦 LogiStock - Sistema de Gerenciamento de Estoque

O **LogiStock** é uma aplicação backend desenvolvida em Java projetada para simular o fluxo real de movimentação de insumos e mercadorias dentro de um galpão logístico. O sistema gerencia o ciclo completo de produtos, desde o cadastro até movimentações de entrada e saída, agora com armazenamento persistente em banco de dados relacional.

---

## 🛠️ Tecnologias e Conceitos Utilizados

* **Linguagem:** Java (Versão SE)
* **Banco de Dados:** MySQL
* **Persistência de Dados:** JDBC (Java Database Connectivity)
* **Padrões de Projeto (Design Patterns):** DAO (Data Access Object) e Factory Method
* **Paradigma:** Orientação a Objetos (POO)
* **Ferramentas de Entrada:** Java Scanner API
* **Versionamento:** Git & GitHub

---

## 🚀 Funcionalidades Principais

1. **Cadastro de Produtos com Persistência:** Permite registrar produtos com geração automática de ID único pelo banco de dados, além de Nome, Preço e Saldo inicial.
2. **Listagem e Relatórios em Tempo Real:** Consulta diretamente o banco de dados e exibe o balanço atualizado de todas as mercadorias armazenadas.
3. **Movimentação de Saída (Dar Baixa):** Processa a retirada de itens no banco, validando a disponibilidade real para evitar quebras de estoque.
4. **Movimentação de Entrada (Reposição):** Atualiza os saldos diretamente nas tabelas do banco de dados ao somar novas quantidades a produtos existentes.

---

## 🛡️ Diferenciais Técnicos & Engenharia de Software

O grande foco do projeto foi a aplicação de boas práticas de desenvolvimento, separação de responsabilidades e arquitetura defensiva:

* **Padrão DAO (Data Access Object) & Factory:** Isolamento completo da lógica de persistência de dados. A classe `ConnectionFactory` gerencia a abertura de conexões com o MySQL utilizando drivers nativos (`mysql_native_password`), enquanto a `ProdutoDAO` centraliza todas as operações de CRUD (Create, Read, Update, Delete) através de `PreparedStatement`, protegendo a aplicação contra vulnerabilidades como SQL Injection.
* **Validação de Interface Avançada (Console Blindado):** A camada de visualização foi totalmente isolada utilizando métodos utilitários que tratam exceções de digitação (`InputMismatchException`), impedindo que o software trave caso o operador digite letras em campos numéricos ou valores negativos.
* **Encapsulamento e Regras de Negócio Seguras:** O modelo (`Produto`) protege seu próprio estado contra dados corrompidos. O sistema foi calibrado para permitir estoques zerados (operação comum em logística), mas bloqueia estritamente preços abusivos ou saldos negativos.
* **Tratamento de Exceções Centralizado:** Erros de banco de dados (como falhas via `SQLException`) e erros de lógica de negócio (como tentar movimentar um produto inexistente ou dar baixa em quantidade maior do que a disponível) disparam exceções tratadas de forma amigável para manter o sistema sempre operacional.

---

## ✒️ Autor

* **Felipe Oliveira de Moraes** - [GitHub](https://github.com/FelipeMoraes16)
