/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import DAOs.DAOPorte;
import DAOs.DAOAnimal;
import Entidades.Porte;
import Entidades.Animal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import myTools.Ferramentas;
import myUtil.DateTextField;
import myUtil.JanelaPesquisar;
import myUtil.MyDateTimeFormat;

/**
 *
 * @author Lucas
 */
public class GUIAnimal2 extends JDialog {
    
    

    DateTextField tfDate = new DateTextField();
    private Container cp;

    private JPanel pnSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnMeio = new JPanel(new GridLayout(4, 2));
    private JPanel pnAdicional = new JPanel();
    private JPanel pnAdicional1 = new JPanel();

    private JLabel lbTitulo = new JLabel("Animal: ");

    private JLabel lbIdAnimal = new JLabel("IdAnimal");
    private JTextField tfIdAnimal = new JTextField(5);
    private JLabel lbNomeAnimal = new JLabel("Nome: ");
    private JTextField tfNomeAnimal = new JTextField();
    private JLabel lbPorte = new JLabel("Porte: ");
    private JTextField tfPorte = new JTextField(11);
    private JLabel lbDataNasc = new JLabel("Data de Nascimento: ");

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeNext = new ImageIcon(getClass().getResource("/icones/next.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnNext = new JButton(iconeNext);
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    Animal animal = new Animal();
    Porte porte = new Porte();

    DAOPorte daoPorte = new DAOPorte();
    DAOAnimal daoAnimal = new DAOAnimal();

    MyDateTimeFormat myDateTimeFormat = new MyDateTimeFormat();

    String acao = "";//variavel para facilitar insert e update
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");

    Ferramentas fer = new Ferramentas();

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnNext.setEnabled(r);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnNext.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean idAnimal, boolean nomeAnimal, boolean quantidadeEstoqueAnimal, boolean statusIdStatus, boolean unidadeDeMedidaIdPorte) {
        if (idAnimal) {
            tfIdAnimal.requestFocus();
            tfIdAnimal.selectAll();
        }
        tfIdAnimal.setEnabled(idAnimal);
        tfIdAnimal.setEditable(idAnimal);
        tfNomeAnimal.setEditable(quantidadeEstoqueAnimal);
        tfPorte.setEditable(statusIdStatus);
        tfDate.setEditable(unidadeDeMedidaIdPorte);

    }

    public void zerarAtributos() {
        tfNomeAnimal.setText("");
        tfPorte.setText("");
    }

    public GUIAnimal2(Point posicao, Dimension dimensao) {
        setSize(700, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout(3, 1));
        setTitle("Animal");

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false);

        cp.add(pnSuperior, BorderLayout.NORTH);
        pnSuperior.add(lbTitulo, BorderLayout.CENTER);
        pnSuperior.add(tfIdAnimal, BorderLayout.WEST);
        pnSuperior.add(btnRetrieve, BorderLayout.WEST);
        pnSuperior.add(btnCreate, BorderLayout.WEST);
        pnSuperior.add(btnNext, BorderLayout.WEST);
        pnSuperior.add(btnUpdate, BorderLayout.WEST);
        pnSuperior.add(btnDelete, BorderLayout.WEST);
        pnSuperior.add(btnSave, BorderLayout.WEST);
        pnSuperior.add(btnCancel, BorderLayout.WEST);
        pnSuperior.add(btnList, BorderLayout.WEST);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        cp.add(pnMeio, BorderLayout.CENTER);
        pnMeio.add(lbNomeAnimal);
        pnMeio.add(tfNomeAnimal);
        tfNomeAnimal.setEditable(false);
        pnMeio.add(lbPorte);
        pnMeio.add(tfPorte);
        tfPorte.setEditable(false);
        pnMeio.add(lbDataNasc);
        pnMeio.add(tfDate);
        tfDate.setToolTipText("Clique para editar");

        pnAvisos.add(labelAviso);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        tfIdAnimal.requestFocus();
        tfIdAnimal.selectAll();
        labelAviso.setText("Digite um IdAnimal e clic [Pesquisar]");

        //------------------------------------- A C T I O N -----------------------------------------
        tfIdAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                animal = new Animal();
                tfIdAnimal.setText(tfIdAnimal.getText().trim());//caso tenham sido digitados espaços

                if (tfIdAnimal.getText().equals("")) {
                    List<String> listaAuxiliar = daoAnimal.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            tfIdAnimal.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            tfIdAnimal.requestFocus();
                            tfIdAnimal.selectAll();
                        }
                    }

                    tfIdAnimal.requestFocus();
                    tfIdAnimal.selectAll();
                } else {
                    try {
                        animal.setIdAnimal(Integer.valueOf(tfIdAnimal.getText()));
                        animal = daoAnimal.obter(animal.getIdAnimal());
                        if (animal != null) { //se encontrou na lista
                            tfNomeAnimal.setText(String.valueOf(animal.getNomeAnimal()));
                            tfDate.setText(String.valueOf(animal.getDataNascAnimal()));
                            tfPorte.setText(String.valueOf(animal.getPorteIdPorte()+ "-" + animal.getPorteIdPorte().getNomePorte()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        tfIdAnimal.setBackground(Color.green);
                    } catch (Exception x) {
                        tfIdAnimal.setOpaque(true);
                        tfIdAnimal.selectAll();
                        tfIdAnimal.requestFocus();
                        tfIdAnimal.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false, true, true, true, true);
                tfNomeAnimal.requestFocus();
                tfPorte.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int prox = daoAnimal.autoIdAnimal();
                tfIdAnimal.setText(String.valueOf(prox));
                btnRetrieve.doClick();
                btnCreate.doClick();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
                if (acao.equals("insert")) {
                    animal = new Animal();
                }
                try {
                    animal.setIdAnimal(Integer.valueOf((tfIdAnimal.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    tfIdAnimal.setBackground(Color.red);
                }
                animal.setIdAnimal(Integer.valueOf(tfIdAnimal.getText()));
                try {
                    animal.setNomeAnimal((tfNomeAnimal.getText()));
                } catch (Exception erro4) {
                    deuRuim = true;
                    tfNomeAnimal.setBackground(Color.red);
                }
                try {
                    animal.setDataNascAnimal(myDateTimeFormat.getStringParaDataBr(tfDate.getText()));
                } catch (Exception erro4) {
                    deuRuim = true;
                    tfDate.setBackground(Color.red);
                }
                try {
                    animal.setPorteIdPorte(daoPorte.obter(Integer.valueOf(tfPorte.getText())));
                } catch (Exception erro4) {
                    deuRuim = true;
                    tfPorte.setBackground(Color.red);
                }

                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoAnimal.inserir(animal);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoAnimal.atualizar(animal);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true, false, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija");
                    labelAviso.setBackground(Color.red);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, false, false, false, false);
                mostrarBotoes(true);
            }
        });

        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIAnimalListagem guiAnimalListagem = new GUIAnimalListagem(daoAnimal.listInOrderId(), getBounds().x, getBounds().y, dimensao);
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true, true, true);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + animal.getIdAnimal() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoAnimal.remover(animal);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                }
            }
        });

        // ----------------   Janela Pesquisar para FKs -----------------
        tfDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoPorte.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            tfDate.getBounds().y + tfDate.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfDate.setText(selectedItem);

                        //preparar para salvar
                    } else {
                        tfDate.requestFocus();
                        tfDate.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        });

        tfPorte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoPorte.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            tfPorte.getBounds().y + tfPorte.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfPorte.setText(selectedItem);

                        //preparar para salvar
                        porte = daoPorte.obter(String.valueOf(aux[0]));

                    } else {
                        tfPorte.requestFocus();
                        tfPorte.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        });

        tfNomeAnimal.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfNomeAnimal.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfNomeAnimal.setBackground(Color.white);
            }
        });

        tfDate.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfDate.setBackground(Color.orange);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfDate.setBackground(Color.yellow);
            }
        });

        tfPorte.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfPorte.setBackground(Color.orange);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfPorte.setBackground(Color.yellow);
            }
        });

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai   
                dispose();
            }
        });

        pack();
        setModal(true);
        setLocationRelativeTo(null);//centraliza no monitor
        setVisible(true);
    }
}
