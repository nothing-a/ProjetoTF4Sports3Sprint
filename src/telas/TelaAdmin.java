package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import classes.Cliente;
import classes.Produto;
import paineis.*;

public class TelaAdmin extends JFrame {
    private JMenuBar jmbBarra;
    private JLabel jlImagem;
    private JMenu jmUnixSports, jmCadastrar, jmMostrar;
    private JMenuItem jmiSair, jmiProduto, jmiMostrarClientes, jmiMostrarProdutos, jmiPainelAlerarDeletar;
    private ImageIcon imagem;
    public PainelAlterarDeletar alterarDeletar = new PainelAlterarDeletar(this);

    public TelaAdmin(String arg0) throws HeadlessException {
        super();
        Color minhaCor = new Color(0, 0, 0);
        getContentPane().setBackground(minhaCor);
        setSize(1295, 838);
        setLocationRelativeTo( null );


        iniciarComponentes();
        criarEventos();
        atualizarTabela();
    }

    //Método para atualizar a tabela
    public void atualizarTabela(){
        alterarDeletar.inserirTabela();

    }

    private void iniciarComponentes() {
        Font fonte = new Font("Serif", Font.BOLD, 20);
        URL icon = this.getClass().getResource("/imagens/IconTF4.png");


        //objeto

        imagem = new ImageIcon(getClass().getResource("/imagens/Principal.png"));
        jlImagem = new JLabel(imagem);
        Image IconeTitulo = Toolkit.getDefaultToolkit().getImage(icon);
        this.setIconImage(IconeTitulo);

        jmbBarra = new JMenuBar();

        jmUnixSports = new JMenu("TF4 Sports");

        jmCadastrar = new JMenu("Cadastrar");

        jmMostrar = new JMenu("Mostrar Dados");

        jmiSair = new JMenuItem("Sair");

        jmiProduto = new JMenuItem("Produto");

        jmiMostrarClientes = new JMenuItem("Clientes");

        jmiMostrarProdutos = new JMenuItem("Produtos");

        jmiPainelAlerarDeletar = new JMenuItem("Alterar/deletar");

        //adicionar
        setJMenuBar(jmbBarra);

        jmbBarra.add(jmUnixSports);

        jmbBarra.add(jmCadastrar);

        jmbBarra.add(jmMostrar);

        jmUnixSports.add(jmiSair);

        jmCadastrar.add(jmiProduto);
        jmCadastrar.add(jmiPainelAlerarDeletar);

        jmMostrar.add(jmiMostrarClientes);
        jmMostrar.add(jmiMostrarProdutos);

        add(jlImagem);

        //Dimensionamento da imagem
        jlImagem.setBounds(0, 0, 1295, 838);

    }


    private void criarEventos() {
        //criar evento do menu item

        jmiSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TelaLogin login = new TelaLogin(null);
                setVisible(false);
                login.setVisible(true);
            }
        }); //fim do evento do JMenuItem Sair


        //Adicionando ação do JMenuItem
        jmiProduto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PainelCadastroProduto produto = new PainelCadastroProduto();
                getContentPane().removeAll();//remove todos os componentes da tela
                getContentPane().add(produto);//adicionando o painel
                getContentPane().validate();//validação dos componentes

            }
        });//fim do evento do JMenuItem Produto

        //Adicionando ação do JMenuItem
        jmiMostrarClientes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PainelMostrarCliente mostrarCliente = new PainelMostrarCliente();
                getContentPane().removeAll();//remove todos os componentes da tela
                getContentPane().add(mostrarCliente);//adicionando o painel
                getContentPane().validate();//validação dos componentes
            }
        });//fim do evento do JMenuItem Mostrar Clientes

        //Adicionando ação do JMenuItem
        jmiMostrarProdutos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PainelMostrarProduto mostrarProduto = new PainelMostrarProduto();
                getContentPane().removeAll();//remove todos os componentes da tela
                getContentPane().add(mostrarProduto);//adicionando o painel
                getContentPane().validate();//validação dos componentes
            }
        });//fim do evento do JMenuItem Mostrar Produtos

        //Adicionando ação do JMenuItem
        jmiPainelAlerarDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabela();
                getContentPane().removeAll();
                getContentPane().add(alterarDeletar);
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        });//fim do evento do JMenuItem que vai para o Painel de Alterar e Deletar Produtos



    }
}
