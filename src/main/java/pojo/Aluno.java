package pojo;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private int idAluno;
    private String nome;
    private int idCurso;
    private boolean estado;
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Aluno(int idAluno, String nome, int idCurso,boolean estado) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.idCurso = idCurso;
        this.estado = estado;
    }

    public Aluno(String nome, int idCurso, boolean estado) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.idCurso = idCurso;
        this.estado = estado;
    }

    public void addEmprestimo(Emprestimo emprestimo){
        this.emprestimos.add(emprestimo);
    }
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public String toString() {
        if(estado){
            return "Aluno: " + nome + " " + "matricula: " + idAluno + " estado: OK";
        }else {
            return "Aluno: " + nome + " " + "matricula: " + idAluno + " estado: INVÁLIDO";
        }
    }
}
