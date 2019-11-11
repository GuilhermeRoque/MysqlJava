package pojo;

public class Componente {
    private int idComponente;
    private String nome;

    public Componente(String nome) {
        this.nome = nome;
    }

    public int getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(int idComponente) {
        this.idComponente = idComponente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Componente{" +
                "idComponente=" + idComponente +
                ", nome='" + nome + '\'' +
                '}';
    }
}
