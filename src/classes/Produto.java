package classes;

import classes.interfaceOBJECT.Iservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Produto extends Gerenciaveis implements Serializable, Iservice<Produto> {

    //declarando as variaveis
    private String quantidade;
    private Long codigo;
    private String valor;
    private String tamanho;
    private String marca;
    private String time;
    public static Produto ref = new Produto();
    public static List<Produto> produtoList = new ArrayList<>();

    //criando construtor
    public Produto() {
    }
    public Produto(String nome, Long codigo, String quantidade, String valor, String tamanho, String marca, String time) {
        super(nome);
        this.quantidade = quantidade;
        this.codigo = codigo;
        this.valor = valor;
        this.tamanho = tamanho;
        this.marca = marca;
        this.time = time;
    }

    //mostrar produtos
    public String mostrarProduto() {
        return "Tipo do Produto: " + super.nome
                + "\nQuantidade: " + this.quantidade
                + "\nValor do código: " + this.codigo
                + "\nValor do produto: " + this.valor
                + "\nTamanho: " + this.tamanho
                + "\nMarca: " + this.marca
                + "\nTime: " + this.time
                + "\n \n";
    }


    @Override
    public boolean remover(Produto produto) {
        for (Produto produt : produtoList) {
        if (produt.equals(produto)) {
            produtoList.remove(produto);
            return true;
        }
    }
        return false;
    }

    @Override
    public boolean incluir(Produto produto) {
        produtoList.add(produto);
        return false;
    }

    @Override
    public boolean alterar(Long codigo,String nome, String marca, String time, String tamanho, String quantidade, String valor) {
        for (Produto produt : produtoList) {
            if (produt.codigo.equals(codigo)) {
                produt.setCodigo(codigo);
                produt.setNome(nome);
                produt.setMarca(marca);
                produt.setTime(time);
                produt.setTamanho(tamanho);
                produt.setQuantidade(quantidade);
                produt.setValor(valor);
                return true;
            }
        }
        return false;
    }

    //Métodos sobrescrito
    @Override
    public Produto pesquisar(String produto) {
        for (Produto product : produtoList) {
            if (product.getNome().equals(produto)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean mostrar() {
        for (Produto product : produtoList){
            System.out.println("Tipo do Produto: " + product.getNome()
                    + "\nQuantidade: " + product.getQuantidade()
                    + "\nValor do código: " + product.getCodigo()
                    + "\nValor do produto: " + product.getValor()
                    + "\nTamanho: " + product.getTamanho()
                    + "\nMarca: " + product.getMarca()
                    + "\nTime: " + product.getTime()
                    + "\n \n");
        }
        return false;
    }

    @Override
    public int compareTo(Produto o) {
        return 0;
    }

    //Getters & Setters
    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTamanho() { return tamanho;}

    public void setTamanho(String tamanho) {this.tamanho = tamanho;}

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
