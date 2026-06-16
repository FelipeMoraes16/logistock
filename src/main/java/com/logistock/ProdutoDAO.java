package com.logistock;

import com.logistock.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ProdutoDAO {

    public List<Produto> listarTodos() {
        String sql = "SELECT * FROM produtos";
        List<Produto> listaDeProdutos = new ArrayList<>();

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){

                int idTemp = rs.getInt("id");
                String nomeTemp = rs.getString("nome");
                BigDecimal precoTemp = rs.getBigDecimal("preco");
                int quantidadeTemp = rs.getInt("quantidade");

                Produto produto = new Produto(idTemp,nomeTemp,precoTemp,quantidadeTemp);

                listaDeProdutos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar os produtos do banco: ", e);
        }

        return listaDeProdutos;
    }

    public void inserirProduto(Produto produto) {

        String sqlInserir = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?,?,?)";

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sqlInserir)){

            stmt.setString(1, produto.getNome());
            stmt.setBigDecimal(2,produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto: ", e);
        }
    }

    public Produto buscarPorId(int id){
        String sql = "SELECT * FROM produtos WHERE id = ?";

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    int idBanco = rs.getInt("id");
                    String nome = rs.getString("nome");
                    BigDecimal preco = rs.getBigDecimal("preco");
                    int quantidade = rs.getInt("quantidade");

                    return new Produto(idBanco, nome, preco, quantidade);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por ID: ", e);
        }
        return null;
    }

    public void atualizarQuantidade(int id, int novaQuantidade) {
        String sql = "UPDATE produtos SET quantidade = ? WHERE id = ?";

        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, id);

            stmt.executeUpdate();
            System.out.println("Banco de dados atualizados com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar quantidade no banco: ", e);
        }
    }
}
