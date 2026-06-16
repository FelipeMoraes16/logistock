package com.logistock;

import com.logistock.service.EstoqueService;
import com.logistock.model.Produto;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        EstoqueService estoque = new EstoqueService();
        int opcao = 0;

        while (opcao != 5) {
            try {
                System.out.println("\n--- LOGISTOCK - SISTEMA DE ESTOQUE ---");
                System.out.println("1. Cadastrar Novo Produto");
                System.out.println("2. Listar Estoque");
                System.out.println("3. Dar Baixa (Saída)");
                System.out.println("4. Repor Estoque (Entrada)");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");

                opcao = entrada.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas numeros!");
                entrada.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\nCADASTRO DO PRODUTO");

                    // LIMPEZA DA ENTRADA
                    entrada.nextLine();

                    // CADASTRO NOME
                    String nome;
                    System.out.println("Digite o nome ou cancele digitando 0:");
                    nome = entrada.nextLine();

                    if (nome.equals("0")) {
                        System.out.println("Operação cancelada!");
                        break;
                    }

                    // CADASTRO PREÇO
                    String mensagem = "Digite o preço ou cancele digitando 0:";
                    BigDecimal preco = lerPrecos(entrada, mensagem);

                    if (preco.compareTo(BigDecimal.ZERO) == 0) {
                        System.out.println("Operação cancelada!");
                        break;
                    }

                    //CADASTRO QUANTIDADE
                    mensagem = "Digite a quantidade ou cancele digitando 0:";
                    int quantidade = lerInteiros(entrada, mensagem);

                    if (quantidade == 0) {
                        System.out.println("Operação cancelada!");
                        break;
                    }

                    Produto produtoAtual = new Produto(0, nome, preco, quantidade);

                    try {
                        estoque.adicionarProduto(produtoAtual);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    System.out.println("\n--- RELATÓRIO ATUAL ---");
                    estoque.listarProdutos();
                    break;

                case 3:
                    System.out.println("\n--- SAÍDA DE PRODUTO --- ");
                    mensagem = "Digite o id ou cancele digitando 0:";
                    int idBaixa = lerInteiros(entrada, mensagem);

                    if (idBaixa == 0) {
                        System.out.println("Operação cancelada!");
                        break;
                    }

                    mensagem = "Digite a quantidade ou canele digitando 0:";
                    int quantidadeBaixa = lerInteiros(entrada, mensagem);

                    if (quantidadeBaixa == 0) {
                        System.out.println("Operação cancelada!");
                        break;
                    }

                    try {
                        estoque.darBaixaNoEstoque(idBaixa, quantidadeBaixa);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:
                    System.out.println("\n--- ADICIONAR PRODUTOS ---");
                    mensagem = "Digite o id ou cancele digitando 0:";
                    int idAdd = lerInteiros(entrada, mensagem);

                    if (idAdd == 0) {
                        System.out.println("Operação cancelada!");
                        break;
                    }

                    mensagem = "Digite a quantidade ou cancele digitando 0:";
                    int quantidadeAdd = lerInteiros(entrada, mensagem);

                    if (quantidadeAdd == 0) {
                        System.out.println("Operação cancelada!");
                        break;
                    }

                    try {
                        estoque.adicionarSaldoEstoque(idAdd, quantidadeAdd);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 5:
                    System.out.println("Fechando o sistema... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        entrada.close();
    }

    private static int lerInteiros(Scanner entrada, String mensagem) {
        while (true) {
            try {
                System.out.println(mensagem);
                int valor = entrada.nextInt();
                if (valor < 0) {
                    System.out.println(("Erro: Digite números maiores que 0"));
                    continue;
                }
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números");
                entrada.nextLine();
            }
        }
    }

    private static BigDecimal lerPrecos(Scanner entrada, String mensagem) {
        while (true) {
            try {
                System.out.println(mensagem);
                BigDecimal valor = entrada.nextBigDecimal();
                if (valor.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Erro: Digite números maiores que 0");
                    continue;
                }
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números");
                entrada.nextLine();
            }
        }
    }
}
