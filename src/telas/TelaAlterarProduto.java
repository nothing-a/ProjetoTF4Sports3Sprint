package telas;

import classes.Produto;
import conexao.comandos.ComandosProduto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaAlterarProduto extends JFrame {
    private JLabel jlImagem;
    private JTextField jtfNomeProduto, jtfQuantidade, jtfCodigo, jtfValor, jtfTamanho, jtfMarca, jtfTime;
    private ImageIcon imagem;
    private JButton jbSalvar, jbVoltar;
    private Produto produto;
    Object[] tabela = {"Codigo", "Camisa", "Quantidade",  "Valor", "Tamanho", "Marca", "Time"};
    String col[] = {"Codigo", "Camisa", "Quantidade",  "Valor", "Tamanho", "Marca", "Time"};
    DefaultTableModel tableModel = new DefaultTableModel(col, 0);
    private JTable table = new JTable(tableModel);
    public ComandosProduto comandos = new ComandosProduto();

    JFrame topFrame;
    int indice;

    public TelaAlterarProduto(String title, Produto produto, JFrame tela, int indice) throws HeadlessException {
        super(title);
        this.produto = produto;
        topFrame = tela;
        this.indice = indice;
        setSize(1295,838);
        setLayout(null);
        Color minhaCor = new Color(3, 127, 252);
        getContentPane().setBackground(minhaCor);
        setLocationRelativeTo(getContentPane());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        iniciaComponentes();
        criarEventos();
    }

    private void iniciaComponentes() {
        //Criando objeto
        imagem = new ImageIcon(getClass().getResource("/imagens/CadastroProduto.png"));
        jlImagem = new JLabel(imagem);

        jtfNomeProduto = new JTextField();
        jtfQuantidade = new JTextField();
        jtfCodigo = new JTextField();
        jtfValor = new JTextField();
        jtfTamanho = new JTextField();
        jtfMarca = new JTextField();
        jtfTime = new JTextField();

        jtfNomeProduto.setText(produto.getNome());
        jtfQuantidade.setText(produto.getQuantidade());
        jtfCodigo.setText((String.valueOf(produto.getCodigo())));
        jtfValor.setText(produto.getValor());
        jtfTamanho.setText(produto.getTamanho());
        jtfMarca.setText(produto.getMarca());
        jtfTime.setText(produto.getTime());

        jbSalvar = new JButton("SALVAR");
        jbVoltar = new JButton("VOLTAR");

        //adicionar
        add(jtfNomeProduto);
        add(jtfQuantidade);
        add(jtfCodigo);
        add(jtfValor);
        add(jtfTamanho);
        add(jtfMarca);
        add(jtfTime);

        add(jbSalvar);
        add(jbVoltar);

        add(jlImagem);

        //dimensionar

        jtfNomeProduto.setBounds(617, 165, 230, 35);
        jtfQuantidade.setBounds(617, 287, 230, 35);
        jtfCodigo.setBounds(973, 287, 230, 35);
        jtfValor.setBounds(617, 405, 230, 35);
        jtfTamanho.setBounds(973, 405, 230, 35);
        jtfMarca.setBounds(617, 510, 230, 35);
        jtfTime.setBounds(617, 620, 230, 35);

        jbSalvar.setBounds(880, 700, 110, 25);
        jbVoltar.setBounds(880, 735, 110, 25);

        jlImagem.setBounds(-7, -20, 1295, 838);

    }
    private void criarEventos() {
        //Adicionando ação do botao Salvar
        jbSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    produto = new Produto(jtfNomeProduto.getText(), Long.parseLong(jtfCodigo.getText()), jtfQuantidade.getText(), jtfValor.getText(), jtfTamanho.getText(),jtfMarca.getText(),jtfTime.getText());
                    comandos.updateProduto(produto);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                setVisible(false);
                TelaAdmin x = (TelaAdmin) topFrame;
                x.atualizarTabela();
                topFrame.setVisible(true);

            }
        });//fim do evento do botão Salvar

        //Adicionando ação do botao Voltar
        jbVoltar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                topFrame.setVisible(true);
            }
        });//fim do evento do botão Voltar


    }
}
