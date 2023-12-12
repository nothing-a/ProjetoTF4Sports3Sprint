package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMySQL {
    //Declarando as váriaveis
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DBNAME = "tf4sports";
    private final String URL = "jdbc:mysql://ESN509VMYSQL/" + DBNAME;
    private final String LOGIN = "aluno";
    private final String SENHA = "Senai1234";

    //Objeto da classe Connection que será usado para configurar o IP do banco, usuário e senha
    //import java.sql.Connection;
    private Connection conexao; //Atalhi import: alt enter

    //Criar método que irá configurar o objeto conexão
    //O método irá retornar o próprio objeto  conexão, ou seja, ao final de tudo será retornado o estado da conexão, isto é, conexão aberta ou não
    public Connection iniciarConexao(){
        //Configuração do Driver de banco de dados
        try {
            Class.forName(DRIVER);
            //Configuração do caminho (IP), usuário, senha e banco de dados
            //O método getCpnneciton() tenta iniciar a conexão com os valores
            // Passados como parâmetro
            conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexao;
    }

    //Método para fechar o banco de dados
    public void close(){
        //Tratamento para conexao
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
