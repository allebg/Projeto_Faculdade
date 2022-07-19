package Business;

import Entity.AlunoEntity;
import Entity.CursoTipo;
import Entity.InscricoesEntity;
import Infraestructure.ValidationException;
import Repository.AlunoRepository;

import java.util.List;

public class AlunoBusiness {

    private AlunoRepository mAlunoRepository;

    public AlunoBusiness() {
        this.mAlunoRepository = new AlunoRepository();
    }

    public void validate(AlunoEntity alunoEntity) throws ValidationException {

        if ("".equals(alunoEntity.getNome())) {
            throw new ValidationException("Nome Obrigatório");
        }
        if ("".equals(alunoEntity.getSobrenome())) {
            throw new ValidationException(("Sobrenome Obrigatório"));
        }
    }

    public AlunoEntity getAlunoByID(int id) {
        return this.mAlunoRepository.getAlunoByID(id);
    }

    public List<AlunoEntity> getList() {
        return this.mAlunoRepository.getList();
    }

    public void create(AlunoEntity alunoEntity) {
        alunoEntity.setId(this.mAlunoRepository.createID());
        this.mAlunoRepository.create(alunoEntity);
    }

    public void update(AlunoEntity alunoEntity) {
        
    }

    public void delete(int id) {

    }

    public InscricoesEntity getTotalAlunos() {
        InscricoesEntity inscricoesEntity = new InscricoesEntity();
        List<AlunoEntity> list = this.getList();
        for (AlunoEntity alunoEntity : list) {
            if (alunoEntity.getCursoTipo() == CursoTipo.INFORMATICA)
                inscricoesEntity.setInformatica(inscricoesEntity.getInformatica() + 1);
            else if (alunoEntity.getCursoTipo() == CursoTipo.MATEMATICA)
                inscricoesEntity.setMatematica(inscricoesEntity.getMatematica() + 1);
            else
                inscricoesEntity.setEngenharia(inscricoesEntity.getEngenharia() + 1);
        }
        return inscricoesEntity;
    }


}
