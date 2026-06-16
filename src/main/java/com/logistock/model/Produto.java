package com.logistock.model;

import java.math.BigDecimal;

public class Produto {
    private int id;
    private String nome;
    private BigDecimal preco;
    private int quantidade;

    public Produto(int id, String nome, BigDecimal preco, int quantidade) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Erro: Preço deve ser maior que zero!");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("Erro: Quantidade deve ser maior que zero!");
        }
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
           throw new IllegalArgumentException("Erro: Preço deve ser maior que zero!");
        }
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Erro: Quantidade deve ser maior que zero!");
        }
        this.quantidade = quantidade;
    }
}
