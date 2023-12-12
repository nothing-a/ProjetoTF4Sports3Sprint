package classes.interfaceOBJECT;

public interface Iservice<T> extends Comparable<T> {
    boolean   remover(T t);
    boolean  incluir(T t);
    boolean  alterar(Long codigo,String nome, String marca, String time, String tamanho, String quantidade, String valor);
    T  pesquisar(String t);
    boolean  mostrar();
}
