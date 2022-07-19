package Entity;

public class AlunoEntity {
    private int id;
    private String nome;
    private String sobrenome;
    private CursoTipo cursoTipo;
    private boolean promocional;


    public AlunoEntity(String nome, String sobrenome, CursoTipo cursoTipo, boolean promocional) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cursoTipo = cursoTipo;
        this.promocional = promocional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public CursoTipo getCursoTipo() {
        return cursoTipo;
    }

    public void setCursoTipo(CursoTipo cursoTipo) {
        this.cursoTipo = cursoTipo;
    }

    public boolean isPromocional() {
        return promocional;
    }

    public void setPromocional(boolean promocional) {
        this.promocional = promocional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
