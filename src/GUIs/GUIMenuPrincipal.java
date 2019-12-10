package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import myUtil.CentroDoMonitorMaior;

public class GUIMenuPrincipal extends JFrame {

    private Container cp;
    private Point p;
    private JPanel pnNorte = new JPanel();
    private JPanel pnCentro = new JPanel();
    private JLabel lbTitulo = new JLabel("PetShop");
    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    private JLabel labelComImagemDeTamanhoDiferente = new JLabel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuCadastros = new JMenu("Cadastros");
    
//------------------------ Mudar Cor --------------------------------
    private JColorChooser colorchooser = new JColorChooser();
    private Color color;
    private JButton btMudarCor = new JButton("Personalizar");
    
    public Color corPadrao;
    
    private JLabel lbEspaco = new JLabel("     ");
//------------------------ Itens do Menu ----------------------------
    private JMenuItem crudGUITarefa = new JMenuItem("Animal");
    private JMenuItem crudGUIPorte = new JMenuItem("Porte");

    public GUIMenuPrincipal(Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle("PetShop");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pnNorte.add(lbTitulo);
        lbTitulo.setFont(fonte);
        pnNorte.setBackground(Color.LIGHT_GRAY);
        
        pnNorte.add(lbEspaco);
        
        pnNorte.add(btMudarCor);
        btMudarCor.setSize(50, 80);
         //para ajustar o tamanho de uma imagem
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/Imagens/petShop.jpg"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(500, 450, Image.SCALE_FAST));

            labelComImagemDeTamanhoDiferente = new JLabel();
            labelComImagemDeTamanhoDiferente.setIcon(icone);
        } catch (Exception e) {
            System.out.println("erro ao carregar a imagem");
        }
        
        pnCentro.add(labelComImagemDeTamanhoDiferente, BorderLayout.NORTH);
        pnCentro.setBackground(Color.BLACK);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);
        menuCadastros.add(crudGUITarefa);
        menuCadastros.add(crudGUIPorte);
        

        crudGUITarefa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIAnimal2 crudGUIAnimal2 = new GUIAnimal2(p, dimensao);
            }
        });

        crudGUIPorte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIPorte crudGUIPorte = new GUIPorte(p, dimensao);
            }
        });

        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
        
        
        //%%%%%%%%%%%%%%%%%%%%%%%%%BOTAO ALTERAR COR%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

        btMudarCor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = colorchooser.showDialog(null, "Escolher Cor", null);
                pnCentro.setBackground(color);
                pnNorte.setBackground(color);
                corPadrao = color;
            }
        });
    } //fecha o contrutor

    public static void main(String[] args) {
        new GUIMenuPrincipal(new Dimension(400, 300));
    }
}
