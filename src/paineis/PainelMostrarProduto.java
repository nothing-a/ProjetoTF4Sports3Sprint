package paineis;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import classes.Produto;
import conexao.comandos.ComandosProduto;

public class PainelMostrarProduto extends JPanel {
    private JLabel jlImagem;
    private ImageIcon imagem;
    private JButton jbMostrar;
    private JTextArea jtaMostrar; //mostrar textos maiores
    private JTable table;
    private JScrollPane jspMostrar;

    public PainelMostrarProduto() {
        super();

        setSize(1295, 838);
        setLayout(null);

        Color minhacor = new Color(0, 0, 0);
        setBackground(minhacor);

        iniciarComponente();
        criarEventos();
    }



    private void iniciarComponente() {
        //Adicionando imagem
        imagem = new ImageIcon(getClass().getResource("/imagens/MostrarDadosProduto.png"));
        jlImagem = new JLabel(imagem);

        //Criando os objetos
        jbMostrar = new JButton("Mostrar");
        jtaMostrar = new JTextArea();
        jspMostrar = new JScrollPane(jtaMostrar);

        //Adicioanando os objetos
        add(jbMostrar);
        add(jspMostrar);
        add(jlImagem);

        //Dimensionando os objetos
        jbMostrar.setBounds(890, 652, 120, 30);
        jspMostrar.setBounds(695, 120, 505, 515);
        jlImagem.setBounds(-7, -20, 1295, 838);


    }

    private void criarEventos() {
        //Adicionando uma ação ao botao mostrar
        jbMostrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ComandosProduto mostrar = new ComandosProduto();
                jtaMostrar.setText("********************************CADASTRO***************************** \n ");
                for (Produto produto :  mostrar.mostrarProduto()) {
                    jtaMostrar.append(produto.mostrarProduto() + "\n");
                }
            }
        });


    }








}
