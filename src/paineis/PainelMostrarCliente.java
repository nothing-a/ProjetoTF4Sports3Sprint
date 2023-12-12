package paineis;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import classes.Cliente;
import conexao.comandos.ComandosCliente;
import conexao.comandos.ComandosProduto;
import telas.TelaAdmin;

public class PainelMostrarCliente extends JPanel {
    private JLabel jlImagem;
    private ImageIcon imagem;
    private JButton jbMostrar, jbDeletar;
    private JTextArea jtaMostrar; //mostrar textos maiores
    private JScrollPane jspMostrar;


    public PainelMostrarCliente() {
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
        imagem = new ImageIcon(getClass().getResource("/imagens/MostrarDadosCliente.png"));
        jlImagem = new JLabel(imagem);

        //Criando objeto do botão
        jbMostrar = new JButton("Mostrar");
        jbDeletar = new JButton("Deletar");

        //Criando objeto do JTextArea
        jtaMostrar = new JTextArea();

        //Criando objeto do Scroll do JTextArea
        jspMostrar = new JScrollPane(jtaMostrar);

        //Adicionando os objetos ao painel
        add(jbMostrar);
        add(jbDeletar);

        add(jspMostrar);

        add(jlImagem);

        //Dimensionando os objetos criados
        jbMostrar.setBounds(830, 652, 120, 30);
        jbDeletar.setBounds(970, 652, 120, 30);
        jspMostrar.setBounds(695, 120, 505, 515);
        jlImagem.setBounds(-7, -20, 1295, 838);

    }

    private void criarEventos() {
        //Adicionando uma ação ao botão de mostrar
        jbMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComandosCliente mostrar = new ComandosCliente();
                jtaMostrar.setText("********************************CADASTRO***************************** \n ");
                for (Cliente cliente : mostrar.mostrarCliente()) {
                    jtaMostrar.append(cliente.mostrarDados() + "\n");
                }
            }
        });
        //Adicionando uma ação ao botão de deletar
        jbDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Long codigo = Long.valueOf(JOptionPane.showInputDialog(null, "Entre com o código do cliente a qual deseja excluir", "TF4 Sports", JOptionPane.WARNING_MESSAGE));
                    ComandosCliente comandosCliente = new ComandosCliente();
                    if (comandosCliente.deletarCliente(codigo)) {
                        comandosCliente.deletarCliente(codigo);

                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente inexistente!");
                    }
                }catch (Exception ex){

                }
            }
        });

    }








}
