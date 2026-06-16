package com.logistock.service;

import com.logistock.model.Produto;
import com.logistock.ProdutoDAO;

import java.util.List;

public class EstoqueService {
    private ProdutoDAO produtoDao = new ProdutoDAO();

    public void adicionarProduto(Produto produto) {

        produtoDao.inserirProduto(produto);

        System.out.println("Produto " + produto.getNome() + " foi adicionado!");
    }

    public void listarProdutos(){
        List<Produto> listaDoBanco = produtoDao.listarTodos();

        if(listaDoBanco.isEmpty()) {
            System.out.println("O estoque está vazio no banco de dados.");
            return;
        }

        for(Produto produto : listaDoBanco) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: " + produto.getPreco());
            System.out.println("Quantidade: " + produto.getQuantidade() + "\n");
        }
    }

    public void darBaixaNoEstoque(int id, int quantidade){

        Produto produtoEstoque = produtoDao.buscarPorId(id);

        if (produtoEstoque == null) {
            throw new IllegalArgumentException("Erro: Produto não encontrado!");
        }

        if (produtoEstoque.getQuantidade() < quantidade) {
            throw new IllegalArgumentException("Erro: Quantidade do estoque insuficiente!" +
            produtoEstoque.getQuantidade());
        }

        int novoSaldo = produtoEstoque.getQuantidade() - quantidade;
        produtoDao.atualizarQuantidade(id, novoSaldo);
        System.out.println("Quantidade do estoque foi alterado com sucesso!");
    }

    public void adicionarSaldoEstoque(int id, int quantidade) {
       Produto produtoEstoque = produtoDao.buscarPorId(id);

        if (produtoEstoque == null) {
            throw new IllegalArgumentException("Erro: Produto não econtrado no banco de dados !");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Erro: Quantidade a ser adicionado deve ser maior do que zero!");
        }

        int novoSaldo = produtoEstoque.getQuantidade() + quantidade;
        produtoDao.atualizarQuantidade(id, novoSaldo);
        System.out.println("Saldo adicionado com sucesso para o produto: " +
        produtoEstoque.getNome());
    }
}
