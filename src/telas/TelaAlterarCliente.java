package telas;

import classes.Cliente;
import conexao.comandos.ComandosCliente;
import paineis.PainelPerfil;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

public class TelaAlterarCliente extends JFrame{
    private JLabel jlImagem;
    private JTextField jtfNome, jtfCpf, jtfEmail, jtfEndereco, jtfGenero, jtfContato;
    private MaskFormatter mkfCpf, mkfContato;
    private JPasswordField jpfSenha;
    private JButton jbSalvar, jbVoltar;
    private ImageIcon imagem;
    private Cliente cliente; //Passando o úsuario que foi conectado
    private ComandosCliente comandoCliente = new ComandosCliente();


    public TelaAlterarCliente(Cliente cliente) {
        super();
        this.cliente = cliente;

        setSize(1295, 838);
        Color minhacor = new Color(0, 0, 0);
        setBackground(minhacor);
        setLocationRelativeTo(getContentPane());
        setLayout(null);

        Color minhaCor = new Color(3, 127, 252);
        setBackground(minhaCor);

        iniciarComponente();
        criarEventos();
    }
        private void iniciarComponente() {
        try {
            mkfCpf = new MaskFormatter("###.###.###-##");
            mkfContato = new MaskFormatter("(##)#####-####");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //Criando objeto
        imagem = new ImageIcon(getClass().getResource("/imagens/MeuPerfil.png"));
        jlImagem = new JLabel(imagem);

        jtfNome = new JTextField();
        jtfCpf = new JFormattedTextField(mkfCpf);
        jtfEndereco = new JTextField();
        jtfGenero = new JTextField();
        jtfContato = new JFormattedTextField(mkfContato);
        jtfEmail = new JTextField();
        jpfSenha = new JPasswordField();

        jbSalvar = new JButton("Salvar");
        jbVoltar = new JButton("Deletar");

        jtfNome.setText(cliente.getNome());
        jtfCpf.setText(cliente.getCpf());
        jtfEndereco.setText(cliente.getEndereco());
        jtfGenero.setText(cliente.getGenero());
        jtfContato.setText(cliente.getContato());
        jtfEmail.setText(cliente.getEmail());
        jpfSenha.setText(cliente.getSenha());

        //Adicionando objeto
        add(jtfNome);
        add(jtfCpf);
        add(jtfEndereco);
        add(jtfGenero);
        add(jtfContato);
        add(jtfEmail);
        add(jpfSenha);

        add(jbSalvar);
        add(jbVoltar);

        add(jlImagem);

        //Dimensionando objeto
        jtfNome.setBounds(660, 380,200,30);
        jtfCpf.setBounds(950, 670,200,30);
        jtfEndereco.setBounds(660, 670,200,30);
        jtfGenero.setBounds(950, 575,200,30);
        jtfContato.setBounds(660, 575,200,30);
        jtfEmail.setBounds(660, 475, 200,30);
        jlImagem.setBounds(-7, -20, 1295, 838);

        jbSalvar.setBounds(780, 725, 150, 35);
        jbVoltar.setBounds(955, 725, 150,35);

        jlImagem.setBounds(-7, -20, 1295, 838);
    }
    private void criarEventos() {
        //Adicionando ação do botao Salvar
        jbSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Cliente alterarCliente = new Cliente(cliente.getId(), jtfNome.getText(),
                            jtfCpf.getText(),jtfEmail.getText(),jtfEndereco.getText(),
                            jtfContato.getText(), jtfGenero.getText()
                            , jpfSenha.getText());

                    comandoCliente.updateCliente(alterarCliente);
                } catch (SQLException ex) {

                }
                setVisible(false);
                JOptionPane.showMessageDialog(null, "Informação alterada com sucesso", "TF4 Sports", JOptionPane.INFORMATION_MESSAGE);
                PainelPerfil.atualiza();
                setVisible(false);
            }
        });//fim do evento do botão Salvar

        //Adicionando ação do botao Voltar
        jbVoltar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TelaLogin cliente = new TelaLogin("Unix Sports");
                cliente.setVisible(true);
            }
        });//fim do evento do botão Voltar


    }
}
