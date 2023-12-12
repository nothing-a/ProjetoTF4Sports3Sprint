package paineis;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import classes.Cliente;
import telas.TelaLogin;
import telas.TelaLoja;


public class PainelSobre extends JPanel {
    private JLabel jlImagem;
    private ImageIcon imagem;


    public PainelSobre(String string) {
        super();

        setSize(1295,838);
        setLayout(null);

        Color minhacor = new Color(0, 0, 0);
        setBackground(minhacor);

        iniciarComponente();
    }

    private void iniciarComponente() {
        //Adicionando imagem
        imagem = new ImageIcon(getClass().getResource("/imagens/SobreNos.png"));
        jlImagem = new JLabel(imagem);

        add(jlImagem);
        //Dimensionando a imagem
        jlImagem.setBounds(-7, -20,1295,838);



    }


    }

