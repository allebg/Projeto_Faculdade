package UI;

import Business.AlunoBusiness;
import Entity.AlunoEntity;
import Entity.CursoTipo;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Mensalidade extends FaculdadeFrame { //Essa classe herda todos os métodos e atributos da classe pai, UI.FaculdadeFrame

    private JPanel rootPanel;
    private JLabel labelNomeSobrenome;
    private JLabel labelCurso;
    private JButton buttonSair;
    private JLabel labelMensalidade;

    private AlunoBusiness mAlunoBusiness;

    public Mensalidade(){

        this.setContentPane(rootPanel);
        this.setSize(600,200);
        this.setTitle("Mensalidade");

        this.mAlunoBusiness = new AlunoBusiness();

        super.defaultConfigurations(); //Aqui é usado o método de configuração default da classe pai, UI.FaculdadeFrame, utilizando o comando super.
        this.setEvents();
    }
    private void setEvents(){
        this.buttonSair.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main();
                dispose();
            }
        });
    }

    public void setAlunoID(int id) {
        AlunoEntity alunoEntity = this.mAlunoBusiness.getAlunoByID(id);

        this.labelNomeSobrenome.setText(String.format("%s %s", alunoEntity.getNome(), alunoEntity.getSobrenome()));
        this.labelCurso.setText(alunoEntity.getCursoTipo().toString());
        float custo = 500;
        if(alunoEntity.isPromocional()){
            this.labelMensalidade.setText(String.format("R$ %.2f", custo*0.7));
        }else{
            this.labelMensalidade.setText(String.format("R$ %.2f", custo));
        }
    }
}
