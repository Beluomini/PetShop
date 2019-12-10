package GUIs;

import DAOs.*;
import Entidades.*;
import GUIs.GUIAnimalListagem;
import myUtil.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;
import myUtil.DateTextField;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import myUtil.UsarGridBagLayout;

public class GUIAnimal extends JDialog {

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

    JLabel labelIdAnimal = new JLabel("IdAnimal");
    JTextField textFieldIdAnimal = new JTextField(20);
    JLabel labelNomeAnimal = new JLabel("Nome Animal");
    JTextField textFieldNomeAnimal = new JTextField(20);
    JLabel labelDataNasc = new JLabel("DataNasc");
    JTextField textFieldDataNasc = new JTextField(20);
    JLabel labelPorteIdPorte = new JLabel("Porte");
    JTextField textFieldPorteIdPorte = new JTextField(20);

//Daos para FK
    DAOAnimal daoAnimal = new DAOAnimal();
    DAOPorte daoPorte = new DAOPorte();

//Entidades para FK
    Animal unidadeDeMedida = new Animal();

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");
    
    MyDateTimeFormat myDateTimeFormat = new MyDateTimeFormat();

    String acao = "";//variavel para facilitar insert e update
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Animal produto;

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

    private void habilitarAtributos(boolean idAnimal, boolean nomeAnimal, boolean quantidadeEstoqueAnimal, boolean statusIdStatus, boolean unidadeDeMedidaIdAnimal) {
        if (idAnimal) {
            textFieldIdAnimal.requestFocus();
            textFieldIdAnimal.selectAll();
        }
        textFieldIdAnimal.setEnabled(idAnimal);
        textFieldIdAnimal.setEditable(idAnimal);
        textFieldNomeAnimal.setEditable(quantidadeEstoqueAnimal);
        textFieldDataNasc.setEditable(statusIdStatus);
        textFieldPorteIdPorte.setEditable(unidadeDeMedidaIdAnimal);

    }

    public void zerarAtributos() {
        textFieldNomeAnimal.setText("");
        textFieldDataNasc.setText("");
        textFieldPorteIdPorte.setText("");
    }
    Color corPadrao = labelIdAnimal.getBackground();

    public GUIAnimal(Point posicao, Dimension dimensao) {
        setTitle("CRUD - Animal");
        setSize(dimensao);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnNext.setToolTipText("Próximo novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar toolbar1 = new JToolBar();
        toolbar1.add(labelIdAnimal);
        toolbar1.add(textFieldIdAnimal);
        toolbar1.add(btnRetrieve);
        toolbar1.add(btnCreate);
        toolbar1.add(btnNext);
        toolbar1.add(btnUpdate);
        toolbar1.add(btnDelete);
        toolbar1.add(btnSave);
        toolbar1.add(btnCancel);
        toolbar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

//atritubos não chave, todos no painel centro
        JPanel centro = new JPanel();
        UsarGridBagLayout usarGridBagLayout = new UsarGridBagLayout(centro);
        usarGridBagLayout.add(labelNomeAnimal, textFieldNomeAnimal, corPadrao);
        usarGridBagLayout.add(labelDataNasc, textFieldDataNasc, Color.yellow);
        usarGridBagLayout.add(labelPorteIdPorte, textFieldPorteIdPorte, Color.yellow);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdAnimal.requestFocus();
        textFieldIdAnimal.selectAll();
        textFieldIdAnimal.setBackground(Color.GREEN);
        labelAviso.setText("Digite um IdAnimal e clic [Pesquisar]");

//--------------- listeners ----------------- 
        textFieldIdAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                produto = new Animal();
                textFieldIdAnimal.setText(textFieldIdAnimal.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdAnimal.getText().equals("")) {
                    List<String> listaAuxiliar = daoAnimal.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdAnimal.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdAnimal.requestFocus();
                            textFieldIdAnimal.selectAll();
                        }
                    }

                    textFieldIdAnimal.requestFocus();
                    textFieldIdAnimal.selectAll();
                } else {
                    try {
                        produto.setIdAnimal(Integer.valueOf(textFieldIdAnimal.getText()));
                        produto = daoAnimal.obter(produto.getIdAnimal());
                        if (produto != null) { //se encontrou na lista
                            textFieldNomeAnimal.setText(String.valueOf(produto.getNomeAnimal()));
                            textFieldDataNasc.setText(String.valueOf(produto.getDataNascAnimal()));
                            textFieldPorteIdPorte.setText(String.valueOf(produto.getPorteIdPorte()+ "-" + produto.getPorteIdPorte().getNomePorte()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdAnimal.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdAnimal.setOpaque(true);
                        textFieldIdAnimal.selectAll();
                        textFieldIdAnimal.requestFocus();
                        textFieldIdAnimal.setBackground(Color.red);
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
                textFieldNomeAnimal.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });

        // ---------------------- botao next ------------------------------
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int prox = daoAnimal.autoIdAnimal();
                textFieldIdAnimal.setText(String.valueOf(prox));
                btnRetrieve.doClick();
                btnCreate.doClick();
            }
        });

//-----------------------------  SAVE ------------------------------------------
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
                if (acao.equals("insert")) {
                    produto = new Animal();
                }
                try {
                    produto.setIdAnimal(Integer.valueOf((textFieldIdAnimal.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldIdAnimal.setBackground(Color.red);
                }
                produto.setIdAnimal(Integer.valueOf(textFieldIdAnimal.getText()));
                try {
                    produto.setNomeAnimal((textFieldNomeAnimal.getText()));
                } catch (Exception erro4) {
                    deuRuim = true;
                    textFieldNomeAnimal.setBackground(Color.red);
                }
                try {
                    produto.setDataNascAnimal(myDateTimeFormat.getStringParaDataBr(textFieldNomeAnimal.getText()));
                } catch (Exception erro4) {
                    deuRuim = true;
                    textFieldNomeAnimal.setBackground(Color.red);
                }try {
                    produto.setPorteIdPorte(daoPorte.obter(Integer.valueOf(textFieldPorteIdPorte.getText())));
                } catch (Exception erro4) {
                    deuRuim = true;
                    textFieldPorteIdPorte.setBackground(Color.red);
                }
                
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoAnimal.inserir(produto);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoAnimal.atualizar(produto);
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
                        "Confirma a exclusão do registro <ID = " + produto.getIdAnimal()+ ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoAnimal.remover(produto);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                }
            }
        });// ----------------   Janela Pesquisar para FKs -----------------
        textFieldDataNasc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoAnimal.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldDataNasc.getBounds().y + textFieldDataNasc.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldDataNasc.setText(selectedItem);

                        //preparar para salvar

                    } else {
                        textFieldDataNasc.requestFocus();
                        textFieldDataNasc.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        });
        textFieldPorteIdPorte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoAnimal.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldPorteIdPorte.getBounds().y + textFieldPorteIdPorte.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldPorteIdPorte.setText(selectedItem);

                        //preparar para salvar
                        unidadeDeMedida = daoAnimal.obter(String.valueOf(aux[0]));

                    } else {
                        textFieldPorteIdPorte.requestFocus();
                        textFieldPorteIdPorte.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        });
        textFieldNomeAnimal.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeAnimal.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeAnimal.setBackground(corPadrao);
            }
        });
        textFieldDataNasc.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldDataNasc.setBackground(Color.orange);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldDataNasc.setBackground(Color.yellow);
            }
        });
        textFieldPorteIdPorte.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldPorteIdPorte.setBackground(Color.orange);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldPorteIdPorte.setBackground(Color.yellow);
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
        setLocation(posicao);
        setVisible(true);//faz a janela ficar visível  
    }
    
    public static void main(String[] args) {
        new GUIAnimal(new Point(880, 250), new Dimension(800, 600));
    }
}
