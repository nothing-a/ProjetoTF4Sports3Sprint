package telas;

import classes.Adimin;
import classes.Cliente;
import conexao.comandos.ComandosCliente;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class TelaCadastro extends JFrame {
    private JLabel jlImagem;
    private JTextField jtfNome, jtfCpf, jtfEndereco, jtfContato, jtfEmail;
    private MaskFormatter mkfCpf, mkfContato;
    private JPasswordField jpfSenha;
    private ButtonGroup bgGenero;
    private JRadioButton jrbMasculino, jrbFeminino;
    private ImageIcon imagem;
    private JButton jbCadastrar, jbVoltar;

    public TelaCadastro(String title) throws HeadlessException {
        super(title);

        setSize(1295, 838);
        setLayout(null);
        Color minhaCor = new Color(3, 127, 252);
        getContentPane().setBackground(minhaCor);
        setLocationRelativeTo(getContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iniciaComponentes();
        criarEventos();

    }

    private void iniciaComponentes() {

       //Mascarando o CPF
        try {
            mkfCpf = new MaskFormatter("###.###.###-##");
            mkfContato = new MaskFormatter("(##)#####-####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Criando objeto
        imagem = new ImageIcon(getClass().getResource("/imagens/CadastroCliente.png"));
        jlImagem = new JLabel(imagem);

        jpfSenha = new JPasswordField();

        jtfNome = new JTextField();
        jtfEmail = new JTextField();
        jtfEndereco = new JTextField();
        jtfContato = new JFormattedTextField(mkfContato);
        jtfCpf = new JFormattedTextField(mkfCpf);

        bgGenero = new ButtonGroup();
        jrbMasculino = new JRadioButton("MASCULINO", true);
        jrbMasculino.setOpaque(false);
        jrbFeminino = new JRadioButton("FEMININO");
        jrbFeminino.setOpaque(false);

        jbCadastrar = new JButton("Cadastrar");
        jbVoltar = new JButton("Voltar");

        //Adicionando objeto
        add(jtfNome);
        add(jtfCpf);
        add(jtfEmail);
        add(jpfSenha);
        add(jtfContato);
        add(jtfEndereco);

        add(jrbMasculino);
        add(jrbFeminino);
        bgGenero.add(jrbMasculino);
        bgGenero.add(jrbFeminino);

        add(jbCadastrar);
        add(jbVoltar);
        add(jlImagem);

        //Dimensionando objeto
        jtfNome.setBounds(617, 200, 400, 30);
        jtfCpf.setBounds(973, 540, 230, 30);
        jtfEmail.setBounds(617, 315, 260, 30);
        jpfSenha.setBounds(973, 315, 230, 30);
        jtfContato.setBounds(617, 435, 230, 30);
        jtfEndereco.setBounds(617, 540, 260, 30);

        jrbMasculino.setBounds(973,410 , 100, 50);
        jrbMasculino.setForeground(new Color(255,255,255));
        jrbFeminino.setBounds(973, 440, 100, 50);
        jrbFeminino.setForeground(new Color(255,255,255));

        jbCadastrar.setBounds(880, 660, 100, 35);
        jbVoltar.setBounds(880, 710, 100, 35);

        jlImagem.setBounds(-7, -20, 1295, 838);

    }
    private void criarEventos() {
        //Adicionando ação do botao Cadastrar
        jbCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nome;
                String cpf;
                String email;
                String endereco;
                String contato;
                String genero;
                String senha;

                nome = jtfNome.getText();
                cpf =jtfCpf.getText();
                email = jtfEmail.getText();
                endereco = jtfEndereco.getText();
                contato = jtfContato.getText();
                genero = jrbFeminino.isSelected() ?"Feminino":"Masculino";
                senha = String.valueOf(jpfSenha.getPassword());
                try {
                    if (!nome.isEmpty()
                            && !cpf.isEmpty()
                            && !email.isEmpty()
                            && !endereco.isEmpty()
                            && !contato.isEmpty()
                            && !genero.isEmpty()) {

                        if (Adimin.cpf.equals(cpf)){
                            throw new NullPointerException();
                        }
                        ComandosCliente cliente = new ComandosCliente();
                        try{
                            cliente.inserirBD(new Cliente(null,nome, cpf, email, endereco, contato, genero, senha));
                            TelaLogin login = new TelaLogin("Cadastro");
                            login.setVisible(true);
                            setVisible(false);
                        }catch (RuntimeException ex){
                            JOptionPane.showMessageDialog(null,"Usuário já cadastrado!");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "TF4 Sports", JOptionPane.WARNING_MESSAGE);
                    }
                }catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(null,"Cadastro feito com sucesso!", "TF4 Sports", JOptionPane.WARNING_MESSAGE);
                }
                jtfNome.setText("");
                jtfCpf.setText("");
                jtfEmail.setText("");
                jtfEndereco.setText("");
                jtfContato.setText("");
                jpfSenha.setText("");

            }
        });//fim do evento do botão cadastrar

        //Adicionando ação do botao Voltar
        jbVoltar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TelaLogin cliente = new TelaLogin("TF4 Sports");
                cliente.setVisible(true);
            }
        });//fim do evento do botão Voltar


    }
}
