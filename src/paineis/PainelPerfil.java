package paineis;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import classes.Cliente;
import classes.Produto;
import conexao.comandos.ComandosCliente;
import telas.TelaAlterarCliente;
import telas.TelaLoja;

public class PainelPerfil extends JPanel {
    JLabel jlImagem;
    static JLabel jlNome;
    static JLabel jlCpf;
    static JLabel jlEndereco;
    static JLabel jlGenero;
    static JLabel jlContato;
    static JLabel jlEmail;
    JButton jbAlterar, jbDeletar;
    ImageIcon imagem;
    ComandosCliente comandoCliente = new ComandosCliente();
    static Cliente cliente; //Passando o úsuario que foi conectado

    public PainelPerfil(Cliente cliente) {
        super();
        this.cliente = cliente;

        setSize(1295, 838);
        Color minhacor = new Color(0, 0, 0);
        setBackground(minhacor);
        setLayout(null);

        Color minhaCor = new Color(3, 127, 252);
        setBackground(minhaCor);

        iniciarComponente();
        criarEventos();
    }

    private void iniciarComponente() {
        TelaLoja loja = new TelaLoja("TF4 Sports", cliente, null);

        imagem = new ImageIcon(getClass().getResource("/imagens/MeuPerfil.png"));
        jlImagem = new JLabel(imagem);
        jlNome = new JLabel();
        jlCpf = new JLabel();
        jlEndereco = new JLabel();
        jlGenero = new JLabel();
        jlContato = new JLabel();
        jlEmail = new JLabel();

        jbAlterar = new JButton("Alterar");
        jbDeletar = new JButton("Deletar");

        add(jlNome);
        add(jlEmail);
        add(jlCpf);
        add(jlEndereco);
        add(jlGenero);
        add(jlContato);
        add(jbAlterar);
        add(jbDeletar);

        add(jlImagem);
        jlNome.setText(cliente.getNome());
        jlCpf.setText(cliente.getCpf());
        jlEndereco.setText(cliente.getEndereco());
        jlGenero.setText(cliente.getGenero());
        jlContato.setText(cliente.getContato());
        jlEmail.setText(cliente.getEmail());
        jlNome.setForeground(Color.white);

        jlCpf.setForeground(Color.white);

        jlEndereco.setForeground(Color.white);

        jlGenero.setForeground(Color.white);

        jlContato.setForeground(Color.white);

        jlEmail.setForeground(Color.white);

        jlNome.setBounds(665, 360,200,50);
        jlCpf.setBounds(955, 650,200,50);
        jlEndereco.setBounds(665, 650,200,50);
        jlGenero.setBounds(955, 555,200,50);
        jlContato.setBounds(665, 555,200,50);
        jlEmail.setBounds(665, 455, 200,50);

        jbAlterar.setBounds(780, 720, 150, 35);
        jbDeletar.setBounds(955, 720, 150,35);

        jlImagem.setBounds(-7, -20, 1295, 838);

    }
    public static void atualiza(){
        jlNome.setText(cliente.getNome ());
        jlCpf.setText(cliente.getCpf());
        jlEndereco.setText(cliente.getEndereco());
        jlGenero.setText(cliente.getGenero());
        jlContato.setText(cliente.getContato());
        jlEmail.setText(cliente.getEmail());

    }
    private void criarEventos(){
        jbAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean achou = false;
                    TelaAlterarCliente alterar = new TelaAlterarCliente(cliente);
                    alterar.setVisible(true);


            }

        });

        jbDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacao = JOptionPane.showConfirmDialog(null, "ATENÇÃO!" + "\n Você tem certeza que deseja excluir sua conta?", "TF4 Sports!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirmacao == JOptionPane.YES_OPTION) {
                    comandoCliente.deletarCliente(cliente.getId());
                    System.exit(0);
                } else if (confirmacao == JOptionPane.NO_OPTION) {
                    // O usuário escolheu "Não", então não faz nada.
                }
            }
        });

    }

}
