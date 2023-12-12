package conexao.comandos;

import classes.Produto;
import conexao.ConectaMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ComandosProduto  extends ConectaMySQL {
    private String procurarProduto = "SELECT * FROM produtos WHERE codProduto = ?";
    private String procurarProdutoNome = "SELECT * FROM produtos WHERE nome = ?";
    private final String updateProduto = "UPDATE produtos SET Nome = ?, Quantidade = ?, Valor = ?, Tamanho = ?, Marca = ?, Selecao = ? WHERE codProduto = ?;";
    private final String deletProduto = "DELETE FROM produtos WHERE codProduto = ?;";
    private final String procurarTodesProdutos = "SELECT * FROM produtos";
    public static  List<Produto> produtos = new ArrayList<>();
    //inserir tabela no banco de dados
    public boolean inserirBD(Produto produto) {
        //Deckarando qual tabela será incerida as informações
        String comando = "INSERT INTO produtos(Nome, Quantidade, Valor, Tamanho, Marca, Selecao) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = super.iniciarConexao().prepareStatement(comando);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getQuantidade());
            ps.setString(3, produto.getValor());
            ps.setString(4, produto.getTamanho());
            ps.setString(5, produto.getMarca());
            ps.setString(6, produto.getTime());
            ps.execute();
            //Caso algo tenha dado errado
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Produto buscaraProduto(Long codigo) {
        try {
            PreparedStatement ps = super.iniciarConexao().prepareStatement(procurarProduto);
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            Produto entity = null;
            if (rs.next()) {
                entity = new Produto(rs.getString(1), rs.getLong(3), rs.getString(2),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Método para deletar produto
    public boolean deletarProduto(Long codigo) {
        //Criando e iniciando o objeto do ConectaMySQL
        //Tratamento da conexão
        try {
            PreparedStatement ps = super.iniciarConexao().prepareStatement(deletProduto);
            ps.setLong(1, codigo);

            if (ps.executeUpdate() != 0) {
                super.close();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    //Método para update produto
    public Boolean updateProduto(Produto produto) throws SQLException {
        produto.mostrarProduto();
        Optional<Produto> opcao = Optional.ofNullable(buscaraProduto(produto.getCodigo()));
        if (!opcao.isPresent()) {
            throw new RuntimeException();
        }
        System.out.println(produto.getCodigo());
        PreparedStatement ps = super.iniciarConexao().prepareStatement(updateProduto);
        ps.setString(1, produto.getNome());
        ps.setString(2, produto.getQuantidade());
        ps.setString(3, produto.getValor());
        ps.setString(4, produto.getTamanho());
        ps.setString(5, produto.getMarca());
        ps.setString(6, produto.getTime());
        ps.setLong(7, produto.getCodigo());
        ps.executeUpdate();
        return true;
    }

    public Boolean procurarProdutoNome (String nome){
        try {
            PreparedStatement ps = super.iniciarConexao().prepareStatement(procurarProdutoNome);
            ps.setString(1, nome);
            ResultSet resultado = ps.executeQuery();
            if (resultado.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean alterarProduto(String comandoSql, String comando) {
        try {
            PreparedStatement ps = super.iniciarConexao().prepareStatement(comandoSql);
            if (ps.executeUpdate() != 0) {
                super.close(); //Fecha a conexão e libera o recurso
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public List<Produto> pesquisarProduto(String comando) {


        PreparedStatement ps = null;
        try {
            ps = super.iniciarConexao().prepareStatement(procurarTodesProdutos);

            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                produtos.add(new Produto(resultado.getString(1),
                        resultado.getLong(3),
                resultado.getString(2),
                resultado.getString(4),
                resultado.getString(5),
                resultado.getString(6),
                resultado.getString(7)
                ));
            }
            super.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return produtos;

    }

    public List<Produto> mostrarProduto() {
        try {
            List<Produto> produtos = new ArrayList<>();
            PreparedStatement ps = super.iniciarConexao().prepareStatement(procurarTodesProdutos);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                produtos.add(new Produto( resultado.getString(1),
                        resultado.getLong(3),
                        resultado.getString(2),
                        resultado.getString(4),
                        resultado.getString(5),
                        resultado.getString(6),
                        resultado.getString(7)
                ));
            }

            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
