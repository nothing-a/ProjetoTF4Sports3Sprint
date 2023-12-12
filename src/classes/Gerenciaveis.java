package classes;

public class Gerenciaveis {
    protected String nome;

    public Gerenciaveis(String nome) {
        this.nome = nome;
    }

    public Gerenciaveis() {

    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
