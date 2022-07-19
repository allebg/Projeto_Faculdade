package UI;

import Business.AlunoBusiness;
import Entity.AlunoEntity;
import Entity.InscricoesEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class Main extends FaculdadeFrame { //Essa classe herda todos os métodos e atributos da classe pai, UI.FaculdadeFrame
    private JPanel rootPanel;
    private JTable tableAlunos;
    private JButton buttonNovo;
    private JButton ButtonMensalidade;
    private JButton ButtonExcluir;
    private JButton buttonEditar;
    private JLabel labelMatemática;
    private JLabel labelInformatica;
    private JLabel labelEngenharia;
    private AlunoBusiness mAlunoBusiness;
    private int mAlunoID = 0;

    public Main(){
        this.setContentPane(rootPanel);
        this.setSize(600,350);
        this.setTitle("Faculdade ");

        this.mAlunoBusiness = new AlunoBusiness();

        super.defaultConfigurations(); //Aqui é usado o método de configuração default da classe pai, UI.FaculdadeFrame, utilizando o comando super.
        this.setEvents();
        this.loadData();



    }

    public void setEvents(){
        this.buttonNovo.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Aluno();
                dispose();
            }
        });

        this.ButtonExcluir.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mAlunoID == 0){
                    JOptionPane.showMessageDialog(new JFrame(), "É necessário selecionar um aluno", "Aluno não selecionado", JOptionPane.ERROR_MESSAGE);
                }else{
                    mAlunoBusiness.delete(mAlunoID);
                    loadData();
                    mAlunoID = 0;
                }
            }
        });

        this.buttonEditar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mAlunoID == 0){
                    JOptionPane.showMessageDialog(new JFrame(), "É necessário selecionar um aluno", "Aluno não selecionado", JOptionPane.ERROR_MESSAGE);
                }else{
                    Aluno aluno = new Aluno();
                    aluno.setAlunoID(mAlunoID);
                    dispose();
                }
            }
        });

        this.ButtonMensalidade.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mAlunoID == 0){
                    JOptionPane.showMessageDialog(new JFrame(), "É necessário selecionar um aluno", "Aluno não selecionado", JOptionPane.ERROR_MESSAGE);
                }else {
                    Mensalidade mensalidade = new Mensalidade();
                    mensalidade.setAlunoID(mAlunoID);
                    dispose();
                }
            }
        });

        this.tableAlunos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){

                    if(tableAlunos.getSelectedRow() != -1){
                        mAlunoID = Integer.parseInt(tableAlunos.getModel().getValueAt(tableAlunos.getSelectedRow(),4).toString());
                    }
                }
            }
        });

    }

    private void loadData(){
        List<AlunoEntity> list = this.mAlunoBusiness.getList();

        String[] columnNames = {"Nome", "Sobrenome", "Curso", "Promocional", "ID"};
        DefaultTableModel model = new DefaultTableModel(new Object [0][0], columnNames);

        for( AlunoEntity alunoEntity : list){

            Object[] o = new Object [5];
            o[0] = alunoEntity.getNome();
            o[1] = alunoEntity.getSobrenome();
            o[2] = alunoEntity.getCursoTipo();
            o[3] = alunoEntity.isPromocional() ? "Sim" : "Não";
            o[4] = alunoEntity.getId();

            model.addRow(o);
        }

        this.tableAlunos.clearSelection();
        this.tableAlunos.setModel(model);

        this.tableAlunos.removeColumn(this.tableAlunos.getColumnModel().getColumn(4));

        InscricoesEntity inscricoesEntity = this.mAlunoBusiness.getTotalAlunos();
        this.labelMatemática.setText("Matemática: " + inscricoesEntity.getMatematica());
        this.labelInformatica.setText("Informática: " + inscricoesEntity.getInformatica());
        this.labelEngenharia.setText("Engenharia: " + inscricoesEntity.getEngenharia());



    }
}
