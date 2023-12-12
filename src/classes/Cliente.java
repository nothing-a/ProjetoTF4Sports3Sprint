package classes;

import classes.interfaceOBJECT.Iservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Gerenciaveis implements Serializable {
    //declarando as variaveis
    private Long id;
    private String genero;
    private String cpf;
    private String email;
    private String endereco;
    private String contato;
    private String senha;
    public static Cliente ref = new Cliente();
    public static List<Cliente> clienteList = new ArrayList<>();

    public Cliente() {super(); }

    //criando o construtor
    public Cliente(Long id, String nome, String cpf, String email, String endereco, String contato, String genero, String senha) {
        super(nome);
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.contato = contato;
        this.genero = genero;
        this.senha = senha;
    }

    //criando o método mostrar dados
    public String mostrarDados() {
        return  "Código: " + this.id
                + "\nNome completo: " + super.nome
                + "\n CPF: " + this.cpf
                + "\n E-mail: " + this.email
                + "\n Endereço: " + this.endereco
                + "\n Contato: " + this.contato
                + "\n gênero: " + this.genero
                + "\n \n \n";
    }
        //Getter & Setter
    public Long getId(){ return id; }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
