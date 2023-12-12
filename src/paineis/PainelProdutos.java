package paineis;

import classes.Cliente;
import telas.TelaLoja;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PainelProdutos extends JPanel{
    private JLabel jlImagem;
    private ImageIcon imagem;
    private Cliente cliente;

    public PainelProdutos(Cliente cliente) {
        super();
        this.cliente = cliente;

        setSize(1295, 838);
        Color minhacor = new Color(0, 0, 0);
        setBackground(minhacor);
        setLayout(null);

        Color minhaCor = new Color(3, 127, 252);
        setBackground(minhaCor);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        TelaLoja loja = new TelaLoja("TF4 Sports", cliente, null);

        //Adicionando imagem
        imagem = new ImageIcon(getClass().getResource("/imagens/Camisas.png"));
        jlImagem = new JLabel(imagem);

        add(jlImagem);

        //Dimensionando imagem
        jlImagem.setBounds(-7, -20, 1295, 838);

    }

}
