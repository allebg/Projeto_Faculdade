package UI;

import Business.AlunoBusiness;
import Entity.AlunoEntity;
import Entity.CursoTipo;
import Infraestructure.ValidationException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Aluno extends FaculdadeFrame { //Essa classe herda todos os métodos e atributos da classe pai, UI.FaculdadeFrame
    private JPanel rootPanel;
    private JTextField textNome;
    private JTextField textSobrenome;
    private JRadioButton radioMatematica;
    private JRadioButton radioinformatica;
    private JRadioButton radioEngenharia;
    private JPanel radioButtonInformatica;
    private JCheckBox checkPromocional;
    private JButton ButtonSalvar;
    private JButton buttonSair;

    private AlunoBusiness mAlunoBusiness;
    private int mAlunoID = 0;


    public Aluno() {

        this.setContentPane(rootPanel);
        this.setSize(600, 350);
        this.setTitle("Cadastro de Alunos");
        super.defaultConfigurations(); //Aqui é usado o método de configuração default da classe pai, UI.FaculdadeFrame, utilizando o comando super.

        this.mAlunoBusiness = new AlunoBusiness();
        this.radioMatematica.setSelected(true);
        this.setEvents();


    }

    public void setEvents() {
        this.ButtonSalvar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSave();
            }
        });

        this.buttonSair.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                dispose();
            }
        });

    }

    private void handleSave() {
        try {
            String nome = this.textNome.getText().trim();
            String sobrenome = this.textSobrenome.getText().trim();

        /*if(nome.equals("")) {
            //throw new Exception("Dados obrigatórios")
            JOptionPane.showMessageDialog(new JFrame(), "Nome obrigatório", "Dados incompletos", JOptionPane.ERROR_MESSAGE);
        }*/
            CursoTipo cursoTipo;
            if (radioEngenharia.isSelected())
                cursoTipo = CursoTipo.ENGENHARIA;
            else if (radioinformatica.isSelected()) {
                cursoTipo = CursoTipo.INFORMATICA;
            } else
                cursoTipo = CursoTipo.MATEMATICA;

            boolean promocional = this.checkPromocional.isSelected();

            AlunoEntity alunoEntity = new AlunoEntity(nome, sobrenome, cursoTipo, promocional);
            this.mAlunoBusiness.validate(alunoEntity);

             if(mAlunoID ==0){
                 this.mAlunoBusiness.create(alunoEntity);
             }else{
                 alunoEntity.setId(this.mAlunoID);
                this.mAlunoBusiness.update(alunoEntity);
                this.mAlunoBusiness.create(alunoEntity);
             }

            new Main();
            dispose();
        } catch (ValidationException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dados incompletos", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void setAlunoID(int id) {
        this.mAlunoID = id;
        AlunoEntity alunoEntity = this.mAlunoBusiness.getAlunoByID(id);
        this.textNome.setText(alunoEntity.getNome());
        this.textSobrenome.setText(alunoEntity.getSobrenome());
        this.checkPromocional.setSelected(alunoEntity.isPromocional());
        this.radioMatematica.setSelected(alunoEntity.getCursoTipo()==CursoTipo.MATEMATICA);
        this.radioinformatica.setSelected(alunoEntity.getCursoTipo()==CursoTipo.INFORMATICA);
        this.radioEngenharia.setSelected(alunoEntity.getCursoTipo()==CursoTipo.ENGENHARIA);

    }
}
