package conexao.comandos;

import classes.Cliente;
import conexao.ConectaMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComandosCliente extends ConectaMySQL {
    private String procurarCliente = "SELECT * FROM cliente WHERE cpf = ?";
    private final String updateUser = "UPDATE cliente SET Genero = ?, Nome = ?, Cpf = ?, Tel = ?, Endereco = ?, Senha = ?, Email = ? WHERE codigo = ?;";
    private final String deleteUser = "DELETE FROM cliente WHERE codigo = ?;";
    private final String procurarTodesClientes = "SELECT * FROM cliente";
    //Criando variável para conectar com o banco de dados
    Cliente cliente;

    //Criando método para inseir as informações para o banco de dados
    public boolean inserirBD(Cliente cliente){

        //Declarando qual tabela será incerida as informações
        String comando = "INSERT INTO cliente (Genero, Nome, Cpf, Tel, Endereco, Senha, Email) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            Optional <Cliente> opcao = Optional.ofNullable(buscarCpf(cliente.getCpf()));
            if (opcao.isPresent()){
                throw new RuntimeException();
            }
            PreparedStatement ps = super.iniciarConexao().prepareStatement(comando);
            ps.setString(1, cliente.getGenero());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getContato());
            ps.setString(5, cliente.getEndereco());
            ps.setString(6, cliente.getSenha());
            ps.setString(7, cliente.getEmail());

            //se alterou pelo menos 1 linha, então o INSERT deu certo
            if (ps.executeUpdate() != 0){
                super.close(); //Fecha a conexão e libera o recurso
                return true;
            }
            //Caso algo tenha dado errado
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return false;
        }

    public Cliente buscarCpf(String cpf){
        try {
            PreparedStatement ps = super.iniciarConexao().prepareStatement(procurarCliente);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            Cliente entity = null;
            if (rs.next()){
                entity = new Cliente(rs.getLong(6),rs.getString(2), rs.getString(3), rs.getString(8),
                        rs.getString(5), rs.getString(4), rs.getString(1), rs.getString(7));
            }
            super.close();
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Método para deletar cliente
    public boolean deletarCliente(Long codigo){
        //Criando e iniciando o objeto do ConectaMySQL
        //Tratamento da conexão
        try {
            PreparedStatement ps = super.iniciarConexao().prepareStatement(deleteUser);
            ps.setLong(1, codigo);

            if (ps.executeUpdate() != 0 ){
                super.close();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    //Método para alterar cliente
    public Boolean updateCliente(Cliente cliente) throws SQLException {
        Optional <Cliente> opcao = Optional.ofNullable(buscarCpf(cliente.getCpf()));
        if (!opcao.isPresent()){
            throw new RuntimeException();
        }
        PreparedStatement ps = super.iniciarConexao().prepareStatement(updateUser);
        ps.setString(1, cliente.getGenero());
        ps.setString(2, cliente.getNome());
        ps.setString(3, cliente.getCpf());
        ps.setString(4, cliente.getContato());
        ps.setString(5, cliente.getEndereco());
        ps.setString(6, cliente.getSenha());
        ps.setString(7, cliente.getEmail());
        ps.setLong(8, cliente.getId());
        ps.executeUpdate();
        return true;
    }

    public boolean alterarCliente(String comandoSql, String comando){
        //Criando e iniciando objeto do ConectaMySQL
        //Tratamento de conexão
        try {
            PreparedStatement ps = super.iniciarConexao().prepareStatement(comandoSql);
            if (ps.executeUpdate() != 0){
                super.close(); //Fecha a conexão e libera o recurso
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    public List<Cliente> mostrarCliente() {
        try {
            List<Cliente> clientes = new ArrayList<>();
            PreparedStatement ps = super.iniciarConexao().prepareStatement(procurarTodesClientes);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getLong(6),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(8),
                        rs.getString(5),
                        rs.getString(4),
                        rs.getString(1),
                        rs.getString(7)
                ));
            }

            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
