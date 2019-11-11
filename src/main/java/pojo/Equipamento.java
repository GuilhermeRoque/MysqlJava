package pojo;

public class Equipamento {
    private int idEquipamento;
    private String descricao;

    public Equipamento(int idEquipamento, String descricao) {
        this.idEquipamento = idEquipamento;
        this.descricao = descricao;
    }

    public Equipamento(String descricao) {
        this.descricao = descricao;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Equipamento: " + idEquipamento + " nome: " + descricao;
    }
}
