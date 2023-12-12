package paineis;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Produto;
import conexao.comandos.ComandosProduto;


public class PainelCadastroProduto extends JPanel {

    private JLabel jlImagem;
    private JTextField jtfNomeProduto, jtfQuantidade, jtfCodigo, jtfValor, jtfTamanho, jtfMarca, jtfTime;
    private ImageIcon imagem;
    private JButton jbCadastrar;
    ComandosProduto produto = new ComandosProduto();

    public PainelCadastroProduto() {
        super();
        setSize(1295, 838);
        setLayout(null);
        Color minhacor = new Color(0, 0, 0);
        setBackground(minhacor);
        iniciarComponentes();
        criarEventos();
    }

    private void iniciarComponentes() {
        //objeto
        imagem = new ImageIcon(getClass().getResource("/imagens/CadastroProduto.png"));
        jlImagem = new JLabel(imagem);

        jtfNomeProduto = new JTextField();
        jtfQuantidade = new JTextField();
        jtfCodigo = new JTextField();
        jtfValor = new JTextField();
        jtfTamanho = new JTextField();
        jtfMarca = new JTextField();
        jtfTime = new JTextField();

        jbCadastrar = new JButton("Cadastrar");

        //adicionar
        add(jtfNomeProduto);
        add(jtfQuantidade);
        add(jtfCodigo);
        add(jtfValor);
        add(jtfTamanho);
        add(jtfMarca);
        add(jtfTime);

        add(jbCadastrar);
        add(jlImagem);

        jbCadastrar.setBackground(new Color(59, 89, 182));
        jbCadastrar.setForeground(Color.WHITE);
        jbCadastrar.setFocusPainted(false);
        jbCadastrar.setFont(new Font("Tahoma", Font.    BOLD, 12));

        //dimensionar
        jtfNomeProduto.setBounds(617, 165, 230, 35);
        jtfQuantidade.setBounds(617, 287, 230, 35);
        jtfCodigo.setBounds(973, 287, 230, 35);
        jtfValor.setBounds(617, 405, 230, 35);
        jtfTamanho.setBounds(973, 405, 230, 35);
        jtfMarca.setBounds(617, 510, 230, 35);
        jtfTime.setBounds(617, 620, 230, 35);


        jbCadastrar.setBounds(880, 700, 130, 35);
        jlImagem.setBounds(-7, -20, 1295, 838);
    }

    private void criarEventos() {
        jbCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeProduto;
                String quantidade;
                String valor;
                String tamanho;
                String marca;
                String time;

                nomeProduto = jtfNomeProduto.getText();
                quantidade =jtfQuantidade.getText();
                valor = jtfValor.getText();
                tamanho = jtfTamanho.getText();
                marca = jtfMarca.getText();
                time = jtfTime.getText();

                    if (!nomeProduto.isEmpty()
                            && !quantidade.isEmpty()
                            && !valor.isEmpty()
                            && !tamanho.isEmpty()
                            && !marca.isEmpty()
                            && !time.isEmpty()
                            ) {
                        if (!produto.procurarProdutoNome(nomeProduto)) {
                            produto.inserirBD(new Produto(nomeProduto, null, quantidade, valor, tamanho, marca, time));
                            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso");

                        }else{
                            JOptionPane.showMessageDialog(null, "Produto já cadastrado!", "TF4 Sports", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }else {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Unix Sports", JOptionPane.WARNING_MESSAGE);
                    }


                jtfNomeProduto.setText("");
                jtfQuantidade.setText("");
                jtfCodigo.setText("");
                jtfValor.setText("");
                jtfTamanho.setText("");
                jtfMarca.setText("");
                jtfTime.setText("");


            }
        });
    }



}
