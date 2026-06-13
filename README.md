# 📦 LogiStock - Sistema de Gerenciamento de Estoque

O **LogiStock** é uma aplicação backend desenvolvida em Java Puro (Core) projetada para simular o fluxo real de movimentação de insumos e mercadorias dentro de um galpão logístico. O sistema gerencia o ciclo completo de produtos, desde o cadastro até movimentações de entrada e saída.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (Versão SE)
* **Paradigma:** Orientação a Objetos (POO)
* **Ferramentas de Entrada:** Java Scanner API
* **Versionamento:** Git & GitHub

---

## 🚀 Funcionalidades Principais

1. **Cadastro de Produtos Robustecido:** Permite registrar produtos com ID único, Nome, Preço e Saldo inicial.
2. **Listagem e Relatórios:** Exibe o balanço atualizado de todas as mercadorias armazenadas na memória dinâmica (`List`).
3. **Movimentação de Saída (Dar Baixa):** Processa a retirada de itens validando a disponibilidade real para evitar quebras de estoque.
4. **Movimentação de Entrada (Reposição):** Permite somar novas quantidades a produtos já existentes na base.

---

## 🛡️ Diferenciais Técnicos & Engenharia de Software

O grande foco do projeto foi a aplicação de boas práticas de desenvolvimento e arquitetura defensiva:

* **Validação de Interface Avançada (Console Blindado):** A camada de visualização foi totalmente isolada utilizando métodos utilitários que tratam exceções de digitação (`InputMismatchException`), impedindo que o software trave caso o operador digite letras em campos numéricos ou valores negativos.
* **Encapsulamento e Regras de Negócio Seguras:** O modelo (`Produto`) protege seu próprio estado contra dados corrompidos. O sistema foi calibrado para permitir estoques zerados (operação comum em logística), mas bloqueia estritamente preços abusivos ou saldos negativos.
* **Tratamento de Exceções Centralizado:** Erros de lógica de negócio (como tentar movimentar um produto inexistente ou dar baixa em quantidade maior do que a disponível) disparam exceções customizadas (`IllegalArgumentException`), capturadas de forma amigável para manter o sistema sempre operacional.

---

## ✒️ Autor

* **Felipe Oliveira de Moraes** - [GitHub](https://github.com/FelipeMoraes16)