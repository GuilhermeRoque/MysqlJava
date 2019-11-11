package pojo;

public class Curso {
    private int idCurso;
    private String nome;
    private String inicioLetivo;
    private String fimLetivo;
    private String inicioFeriasInverno;
    private String fimFeriasInverno;

    public Curso(int idCurso, String nome, String inicioLetivo, String fimLetivo, String inicioFeriasInverno, String fimFeriasInverno) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.inicioLetivo = inicioLetivo;
        this.fimLetivo = fimLetivo;
        this.inicioFeriasInverno = inicioFeriasInverno;
        this.fimFeriasInverno = fimFeriasInverno;
    }

    public Curso(String nome, String inicioLetivo, String fimLetivo, String inicioFeriasInverno, String fimFeriasInverno) {
        this.nome = nome;
        this.inicioLetivo = inicioLetivo;
        this.fimLetivo = fimLetivo;
        this.inicioFeriasInverno = inicioFeriasInverno;
        this.fimFeriasInverno = fimFeriasInverno;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInicioLetivo() {
        return inicioLetivo;
    }

    public void setInicioLetivo(String inicioLetivo) {
        this.inicioLetivo = inicioLetivo;
    }

    public String getFimLetivo() {
        return fimLetivo;
    }

    public void setFimLetivo(String fimLetivo) {
        this.fimLetivo = fimLetivo;
    }

    public String getInicioFeriasInverno() {
        return inicioFeriasInverno;
    }

    public void setInicioFeriasInverno(String inicioFeriasInverno) {
        this.inicioFeriasInverno = inicioFeriasInverno;
    }

    public String getFimFeriasInverno() {
        return fimFeriasInverno;
    }

    public void setFimFeriasInverno(String fimFeriasInverno) {
        this.fimFeriasInverno = fimFeriasInverno;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "idCurso=" + idCurso +
                ", nome='" + nome + '\'' +
                ", inicioLetivo='" + inicioLetivo + '\'' +
                ", fimLetivo='" + fimLetivo + '\'' +
                ", inicioFeriasInverno='" + inicioFeriasInverno + '\'' +
                ", fimFeriasInverno='" + fimFeriasInverno + '\'' +
                '}';
    }
}
