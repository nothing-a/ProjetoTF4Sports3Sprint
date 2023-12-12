package paineis;

import classes.Produto;
import conexao.comandos.ComandosProduto;
import telas.TelaAdmin;
import telas.TelaAlterarProduto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;

public class PainelAlterarDeletar extends JPanel{
    private JLabel jlImagem;
    private ImageIcon imagem;
    private JButton jbAlterar, jbDeletar;
    Object[] tabela = {"Codigo", "Camisa", "Quantidade",  "Valor", "Tamanho", "Marca", "Time"};
    static String[] col = {"Codigo", "Camisa", "Quantidade",  "Valor", "Tamanho", "Marca", "Time"};
    public static DefaultTableModel tableModel = new DefaultTableModel(col, 0);
    static JTable table = new JTable(tableModel);
    static List<Produto> listaCompleta = new ArrayList<>();
    Produto produto;
    JFrame topFrame = null;
    int indice = 0;

    public PainelAlterarDeletar(JFrame tela) {
        super();

        this.produto = produto;
        topFrame = tela;
        tableModel.setRowCount(0);
        tableModel.fireTableDataChanged();

        setSize(1295, 838); //Determinando o tamanho do painel
        setLayout(null); //Desabilitando o layout automatico que o proprio JAVA faz
        Color minhacor = new Color(0, 0, 0); //Criando um objeto da classe Color
        setBackground(minhacor); //Adicionando cor ao painel
        iniciarComponentes();
        criarComponenetes();
        inserirTabela();
    }
    //Método para se deletar os dados que se tem na tabela
    private static void deletarDadosTabela(){
        for (int i = tableModel.getRowCount()-1; i >= 0; i--) {
            tableModel.removeRow(i);
            System.out.println("11111");
        }
//        tableModel = new DefaultTableModel(col, 0);
//        table = new JTable(tableModel);
        tableModel.fireTableDataChanged();
    }
    //Método para adicionar e mostrar o que foi adicionado ao BCD
    public static void inserirTabela() {
        ComandosProduto comandos = new ComandosProduto();

        for (int i = tableModel.getRowCount()-1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

//        deletarDadosTabela();
        tableModel.setRowCount(0);
        tableModel.fireTableDataChanged();
        tableModel.addRow(col);
        listaCompleta.clear();

        listaCompleta = comandos.pesquisarProduto(null);

        for (Produto produto : listaCompleta) {
            Object[] data = {produto.getCodigo(), produto.getNome(), produto.getQuantidade(),
                    produto.getValor(), produto.getTamanho(), produto.getMarca(),
                    produto.getTime()};
            tableModel.addRow(data);
        }

    }
    private void iniciarComponentes() {
        //objeto
        jbAlterar = new JButton("ALTERAR");
        jbDeletar = new JButton("DELETAR");

        imagem = new ImageIcon(getClass().getResource("/imagens/AlterarDeletar.png"));
        jlImagem = new JLabel(imagem);

        //adicionar
        add(jbAlterar);
        add(jbDeletar);
        add(table);
        add(jlImagem);

        jbAlterar.setBackground(new Color(59, 89, 182));
        jbAlterar.setForeground(Color.WHITE);
        jbAlterar.setFocusPainted(false);
        jbAlterar.setFont(new Font("Tahoma", Font.BOLD, 12));

        jbDeletar.setBackground(new Color(59, 89, 182));
        jbDeletar.setForeground(Color.WHITE);
        jbDeletar.setFocusPainted(false);
        jbDeletar.setFont(new Font("Tahoma", Font.BOLD, 12));

        //dimensionar

        jbAlterar.setBounds(720, 670, 95, 25);
        jbDeletar.setBounds(840, 670, 95, 25);
        table.setBounds(415, 145, 814,500);
        jlImagem.setBounds(-7, -20, 1295, 838);
    }
    private void criarComponenetes() {

        //Adicionando ação ao botao de Alterar
        jbAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long codigo = Long.valueOf(JOptionPane.showInputDialog(null,"Entre com o código do produto para alterar", "TF4 Sports", JOptionPane.INFORMATION_MESSAGE));
                    ComandosProduto comandos = new ComandosProduto();
                    boolean achou = false;
                    for (Produto p : listaCompleta) {
                        if(p.getCodigo() == codigo){
                            TelaAlterarProduto alterar = new TelaAlterarProduto("TF4 Sports", p, topFrame, indice);
                            alterar.setVisible(true);
                            topFrame.setVisible(false);
                            achou = true;
                            break;
                        }
                        indice++;
                    }
                    if (achou == false){
                        JOptionPane.showMessageDialog(null, "Produto inexistente!", "TF4 Sports", JOptionPane.WARNING_MESSAGE);
                    }

                }catch (RuntimeException ex){

                }
            }

        });
        //Adicionando ação ao botao de Deletar
        jbDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                Long codigo = Long.valueOf(JOptionPane.showInputDialog(null, "Entre com o código do produto a qual deseja excluir", "TF4 Sports", JOptionPane.WARNING_MESSAGE));
                ComandosProduto comandosProduto = new ComandosProduto();
                if (comandosProduto.deletarProduto(codigo)) {
                    comandosProduto.deletarProduto(codigo);
                    TelaAdmin x = (TelaAdmin) topFrame;
                    x.atualizarTabela();
                    topFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Produto inexistente!");
                }
                }catch (Exception ex){

                }
            }
        });

    }
}
