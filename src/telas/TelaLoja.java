package telas;

import classes.Cliente;
import classes.Produto;
import paineis.PainelCadastroProduto;
import paineis.PainelPerfil;
import paineis.PainelProdutos;
import paineis.PainelSobre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TelaLoja extends JFrame {
    private JMenuBar jmbBarra;
    private JLabel jlImagem;
    private JMenu jmTF4, jmProdutos, jmPerfil, jmSobre ;
    private JMenuItem jmiSair, jmiPainelSobre, jmiCamisas, jmiPerfil;
    private ImageIcon imagem;

    private List<Produto> produtos = new ArrayList<>();
    private Cliente cliente;

    public TelaLoja(String arg0, Cliente cliente, List<Produto> produtos ) throws HeadlessException {
        super(arg0);
        this.cliente = cliente;
        Color minhaCor = new Color(0, 0, 0);
        getContentPane().setBackground(minhaCor);
        setSize(1295, 838);
        setLocationRelativeTo( null );


        iniciarComponentes();
        criarEventos();

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

        jmTF4 = new JMenu("Unix Sports");

        jmProdutos = new JMenu("Produtos");

        jmPerfil = new JMenu("Perfil");

        jmSobre = new JMenu("Sobre");

        jmiSair = new JMenuItem("Sair");

        jmiPainelSobre = new JMenuItem("Equipe");

        jmiCamisas = new JMenuItem("Camisas");

        jmiPerfil = new JMenuItem("Minha conta");

        //adicionar
        setJMenuBar(jmbBarra);

        jmbBarra.add(jmTF4);

        jmbBarra.add(jmProdutos);

        jmbBarra.add(jmPerfil);

        jmbBarra.add(jmSobre);

        jmTF4.add(jmiSair);

        jmProdutos.add(jmiCamisas);

        jmPerfil.add(jmiPerfil);
        add(jlImagem);

        jmSobre.add(jmiPainelSobre);

        jlImagem.setBounds(-7, -50,1295, 838);
    }
    private void criarEventos() {
        //criar evento do menu item

        jmiSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TelaLogin telaLogin = new TelaLogin("Tela Login");
                setVisible(false);
                telaLogin.setVisible(true);

            }
        });

        jmiPainelSobre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PainelSobre sobre = new PainelSobre("Sobre");
                getContentPane().removeAll();
                getContentPane().add(sobre);
                getContentPane().validate();
            }
        });

        jmiPerfil.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PainelPerfil mostrarCliente = new PainelPerfil(cliente);
                getContentPane().removeAll();//remove todos os componentes da tela
                getContentPane().add(mostrarCliente);//adicionando o painel
                getContentPane().validate();//validação dos componentes

            }
        });

        jmiCamisas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PainelProdutos produtos = new PainelProdutos(cliente);
                getContentPane().removeAll();
                getContentPane().add(produtos);
                getContentPane().validate();
            }
        });
    }
}
