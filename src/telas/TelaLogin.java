package telas;

import classes.Adimin;
import classes.Cliente;
import conexao.comandos.ComandosCliente;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;


public class TelaLogin extends JFrame{
    private JLabel jlImagem;
    private JTextField jtfCpf;
    private MaskFormatter mkfCpf;
    private  JPasswordField jpfSenha;
    private JButton jbEntrar, jbCadastrar;
    private ImageIcon imagem;
    private ComandosCliente comandoCliente = new ComandosCliente();


    public TelaLogin(String title) throws HeadlessException {
        super(title);
        setSize(1295,838);
        Font fonte = new Font("Serif", Font.BOLD, 30);
        Color minhaCor = new Color(3, 127, 252);
        getContentPane().setBackground(minhaCor);
        setLocationRelativeTo(getContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        iniciaComponentes();
        criarEventos();
    }
    private void iniciaComponentes() {
        try {
            mkfCpf = new MaskFormatter("###.###.###-##");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Criando objetos
        Font fonte = new Font("Serif", Font.BOLD, 20);
        Font fonteErr = new Font("Serif", Font.BOLD, 15);
        imagem = new ImageIcon(getClass().getResource("/imagens/Login.png"));
        jlImagem = new JLabel(imagem);
        URL icon = this.getClass().getResource("/imagens/IconTF4.png");
        jlImagem = new JLabel(imagem);
        Image IconeTitulo = Toolkit.getDefaultToolkit().getImage(icon);
        this.setIconImage(IconeTitulo);

        jtfCpf = new JFormattedTextField(mkfCpf);
        jtfCpf.setFont(fonte);

        jpfSenha = new JPasswordField();

        jbEntrar = new JButton("Entrar");
        jbCadastrar = new JButton("Cadastrar");

        //Adicionando objeto
        add(jtfCpf);

        add(jpfSenha);
        add(jbEntrar);
        add(jbCadastrar);
        add(jlImagem);

        //Dimensionando os objetos
        jtfCpf.setBounds(703, 310, 200, 35);
        jpfSenha.setBounds(703, 450, 200, 35);

        jbEntrar.setBounds(880, 590, 120, 35);
        jbCadastrar.setBounds(880, 645, 120, 35);

        jlImagem.setBounds(-7, -50, 1295, 900);

    }
    private void criarEventos() {

        //Adicionando ação do botao Cadastrar
        jbCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastro cadastro = new TelaCadastro("TF4 Sports");
                cadastro.setVisible(true);
                setVisible(false);
            }
        });//fim do evento do botão cadastrar

        //Adicionando ação do botao Entrar
        jbEntrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean achou = false;
                String cpf = jtfCpf.getText();
                String senha = String.valueOf(jpfSenha.getPassword());
                Cliente entity = comandoCliente.buscarCpf(cpf);

                try {
                if (jtfCpf.getText().equals(Adimin.cpf) && Adimin.senha.equals(String.valueOf(jpfSenha.getPassword()))) {
                    TelaAdmin telaAdimin = new TelaAdmin("TF4 Sports");
                    telaAdimin.setVisible(true);
                    setVisible(false);
                    achou = true;

                }else {


                    if (!achou && entity.getSenha().equals(senha)) {
                        TelaLoja loja = new TelaLoja(null, entity, null);
                        loja.setVisible(true);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login ou senha incorreto!");
                    }
                }
                   } catch (NullPointerException ex){
                       JOptionPane.showMessageDialog(null, "Usuário inexistente");
                   }



                jtfCpf.setText("");
                jpfSenha.setText("");

            }
        }); //fim do evento do botão Entrar

    }

    public static void main(String[] args) {
        TelaLogin login = new TelaLogin("TF4 Sports");
        login.setVisible(true);


    }

}
