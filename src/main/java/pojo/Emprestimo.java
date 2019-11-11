package pojo;

import java.util.ArrayList;
import java.util.List;

public class Emprestimo {
    private int idEmprestimo;
    private int idAluno;
    private String dataRetirada;
    private String dataEntrega;
    private String dataAgendadaEntrega;
    private String dataAgendadaRetirada;
    private int motivo;
    private int count;
    private List<Equipamento> equipamentos = new ArrayList<>();

    public Emprestimo(int idEmprestimo, int idAluno, String dataRetirada, String dataEntrega, String dataAgendadaEntrega, String dataAgendadaRetirada, int motivo, int count) {
        this.idEmprestimo = idEmprestimo;
        this.idAluno = idAluno;
        this.dataRetirada = dataRetirada;
        this.dataEntrega = dataEntrega;
        this.dataAgendadaEntrega = dataAgendadaEntrega;
        this.dataAgendadaRetirada = dataAgendadaRetirada;
        this.motivo = motivo;
        this.count = count;
    }

    public Emprestimo(int idAluno, String dataRetirada, String dataEntrega, String dataAgendadaEntrega, String dataAgendadaRetirada, int motivo, int count) {
        this.idAluno = idAluno;
        this.dataRetirada = dataRetirada;
        this.dataEntrega = dataEntrega;
        this.dataAgendadaEntrega = dataAgendadaEntrega;
        this.dataAgendadaRetirada = dataAgendadaRetirada;
        this.motivo = motivo;
        this.count = count;
    }

    public Emprestimo(int idAluno, String dataRetirada, String dataAgendadaEntrega, String dataAgendadaRetirada, int motivo, int count) {
        this.idAluno = idAluno;
        this.dataRetirada = dataRetirada;
        this.dataAgendadaEntrega = dataAgendadaEntrega;
        this.dataAgendadaRetirada = dataAgendadaRetirada;
        this.motivo = motivo;
        this.count = count;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public void addEquipamento(Equipamento equipamento){
        this.equipamentos.add(equipamento);
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(String dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getDataAgendadaEntrega() {
        return dataAgendadaEntrega;
    }

    public void setDataAgendadaEntrega(String dataAgendadaEntrega) {
        this.dataAgendadaEntrega = dataAgendadaEntrega;
    }

    public String getDataAgendadaRetirada() {
        return dataAgendadaRetirada;
    }

    public void setDataAgendadaRetirada(String dataAgendadaRetirada) {
        this.dataAgendadaRetirada = dataAgendadaRetirada;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        if(this.dataEntrega == null){
            return "Emprestimo: " + idEmprestimo + " dataRetirada: " + dataRetirada + " dataEntrege: NÂO ENTREGUE dataAgendadaEntrega: " + dataAgendadaEntrega  +
                    " dataAgendadaRetirada " + dataAgendadaRetirada + " renovações: " + count;
        }
        else {
            return "Emprestimo: " + idEmprestimo + " dataRetirada: " + dataRetirada + " dataEntregue: " + dataEntrega + " dataAgendadaEntrega: " + dataAgendadaEntrega  +
                    " dataAgendadaRetirada " + dataAgendadaRetirada + " renovações: " + count;
        }
    }
}
