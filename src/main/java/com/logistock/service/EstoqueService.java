package com.logistock.service;

import com.logistock.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class EstoqueService {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {

        for (Produto produtoExistente : produtos) {
            if (produto.getId() == produtoExistente.getId()) {
                throw new IllegalArgumentException("Erro: ID já existente!");
            }
        }

        produtos.add(produto);
        System.out.println("Produto " + produto.getNome() + " foi adicionado!");
    }

    public void listarProdutos(){
        for (Produto produto : produtos) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.printf("Preço: %.2f\n", produto.getPreco());
            System.out.println("Quantidade: " + produto.getQuantidade() + "\n");
        }
    }

    public void darBaixaNoEstoque(int id, int quantidade){

        Produto produtoEstoque = encontrarProduto(id);

        if (produtoEstoque == null) {
            throw new IllegalArgumentException("Erro: Produto não encontrado!");
        }

        if (produtoEstoque.getQuantidade() < quantidade) {
            throw new IllegalArgumentException("Erro: Quantidade do estoque insuficiente!");
        }

        int novoSaldo = produtoEstoque.getQuantidade() - quantidade;
        produtoEstoque.setQuantidade(novoSaldo);
        System.out.println("Quantidade do estoque foi alterado com sucesso!");
    }

    public void adicionarSaldoEstoque(int id, int quantidade) {
       Produto produtoEstoque = encontrarProduto(id);

        if (produtoEstoque == null) {
            throw new IllegalArgumentException("Erro: Produto não econtrado!");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Erro: Quantidade a ser adicionado deve ser maior do que zero!");
        }

        int novoSaldo = produtoEstoque.getQuantidade() + quantidade;
        produtoEstoque.setQuantidade(novoSaldo);
        System.out.println("Saldo adicionado com sucesso!");
    }

    public Produto encontrarProduto(int id) {

        Produto produtoEncontrado = null;

        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produtoEncontrado = produto;
                break;
            }
        }

        return produtoEncontrado;
    }
}
