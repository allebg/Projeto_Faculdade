package Repository;

import Entity.AlunoEntity;

import java.util.ArrayList;
import java.util.List;


public class AlunoRepository {

    private static int mAlunoID = 0;
    private static List<AlunoEntity> mAlunoList = new ArrayList<>();

    public int createID() {
        return ++mAlunoID;
    }

    public List<AlunoEntity> getList() {
        return mAlunoList;
    }

    public AlunoEntity getAlunoByID(int id) {
        AlunoEntity alunoEntity = null;
        for (AlunoEntity aluno : mAlunoList) {
            if (aluno.getId() == id) {
                alunoEntity = aluno;
                break;
            }
        }
        return alunoEntity;
    }

    public void create(AlunoEntity alunoEntity) {
        mAlunoList.add(alunoEntity);
    }

    public void update(AlunoEntity alunoEntity) {
        for (AlunoEntity aluno : mAlunoList) {
            if (aluno.getId() == alunoEntity.getId()) {
                aluno.setNome(alunoEntity.getNome());
                aluno.setSobrenome(alunoEntity.getSobrenome());
                aluno.setCursoTipo(alunoEntity.getCursoTipo());
                aluno.setPromocional(alunoEntity.isPromocional());

            }
        }

    }

    public void delete(int id) {
        int indexRemove = 0;
        for (int i = 0; i < mAlunoList.size(); i++) {
            if (mAlunoList.get(i).getId() == id) {
                indexRemove = i;
                break;
            }
        }

        mAlunoList.remove(indexRemove);


    }
}
