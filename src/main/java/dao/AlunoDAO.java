package dao;

import db.ConnectionFactory;
import pojo.Aluno;
import pojo.Emprestimo;
import pojo.Equipamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AlunoDAO {

    public Aluno buscaAluno(int idAluno) {
        String sql = "select * from Aluno where idAluno = ?";
        Aluno aluno = null;
        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, RETURN_GENERATED_KEYS)) {

            stmt.setInt(1,idAluno);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                aluno = new Aluno(
                        rs.getInt("idAluno"),
                        rs.getString("nome"),
                        rs.getInt("idCurso"),
                        rs.getBoolean("estado"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return aluno;
    }

    public int atualizaAluno(Aluno aluno) {
        String sql = "update Aluno set estado = ? where idAluno = ?";
        int rows = 0;
        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, RETURN_GENERATED_KEYS)) {

            stmt.setBoolean(1,aluno.isEstado());
            stmt.setInt(1,aluno.getIdAluno());
            rows = stmt.executeUpdate();


        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return rows;
    }

    public List<Aluno> buscaTodosAlunos() {
        String sql = "select * from Aluno";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                alunos.add(new Aluno(
                        rs.getInt("idAluno"),
                        rs.getString("nome"),
                        rs.getInt("idCurso"),
                        rs.getBoolean("estado")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return alunos;
    }

    public List<Aluno> buscaTodosAlunosInvalidos() {
        String sql = "select * from Aluno where estado = 0";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                alunos.add(new Aluno(
                        rs.getInt("idAluno"),
                        rs.getString("nome"),
                        rs.getInt("idCurso"),
                        rs.getBoolean("estado")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return alunos;
    }

    public List<Aluno> buscaAlunosEquipamento(int idEquipamento) {
        String sql = "select * from Emprestimo_has_Equipamento natural join Emprestimo natural join Equipamento natural join Aluno where idEquipamento = ?";

        List<Aluno> alunos = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1,idEquipamento);
            ResultSet rs = stmt.executeQuery();

            int idAluno = 0;
            int idEmprestimo = 0;
            int novaidAluno = 0;
            int novaidEmprestimo = 0;

            Aluno aluno = null;
            Emprestimo emprestimo = null;

            if(rs.next()){
                novaidAluno = rs.getInt("idAluno");
                idAluno = novaidAluno;
                aluno = new Aluno(
                        rs.getInt("idAluno"),
                        rs.getString("nome"),
                        rs.getInt("idCurso"),
                        rs.getBoolean("estado"));

                novaidEmprestimo = rs.getInt("idEmprestimo");
                idEmprestimo = novaidEmprestimo;
                emprestimo = new Emprestimo(
                        rs.getInt("idEmprestimo"),
                        rs.getInt("idAluno"),
                        rs.getString("dataRetirada"),
                        rs.getString("dataEntrega"),
                        rs.getString("dataAgendadaEntrega"),
                        rs.getString("dataAgendadaRetirada"),
                        rs.getInt("motivo"),
                        rs.getInt("count"));
            }

            rs.previous();

            while (rs.next()){
                novaidAluno = rs.getInt("idAluno");
                novaidEmprestimo = rs.getInt("idEmprestimo");
                if (novaidAluno == idAluno){
                    if (novaidEmprestimo == idEmprestimo){
                        if (rs.isLast()){
                            aluno.addEmprestimo(emprestimo);
                            alunos.add(aluno);
                        }
                    }
                    else {
                        aluno.addEmprestimo(emprestimo);
                        emprestimo = new Emprestimo(
                                rs.getInt("idEmprestimo"),
                                rs.getInt("idAluno"),
                                rs.getString("dataRetirada"),
                                rs.getString("dataEntrega"),
                                rs.getString("dataAgendadaEntrega"),
                                rs.getString("dataAgendadaRetirada"),
                                rs.getInt("motivo"),
                                rs.getInt("count"));

                        idEmprestimo = novaidEmprestimo;
                        if (rs.isLast()){
                            aluno.addEmprestimo(emprestimo);
                            alunos.add(aluno);
                        }
                    }
                }
                else {
                    aluno.addEmprestimo(emprestimo);
                    alunos.add(aluno);
                    aluno = new Aluno(
                            rs.getInt("idAluno"),
                            rs.getString("nome"),
                            rs.getInt("idCurso"),
                            rs.getBoolean("estado"));

                    emprestimo = new Emprestimo(
                            rs.getInt("idEmprestimo"),
                            rs.getInt("idAluno"),
                            rs.getString("dataRetirada"),
                            rs.getString("dataEntrega"),
                            rs.getString("dataAgendadaEntrega"),
                            rs.getString("dataAgendadaRetirada"),
                            rs.getInt("motivo"),
                            rs.getInt("count"));

                    idEmprestimo = novaidEmprestimo;
                    idAluno = novaidAluno;
                    if (rs.isLast()){
                        aluno.addEmprestimo(emprestimo);
                        alunos.add(aluno);
                    }

                }
            }

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return alunos;
    }

    public List<Emprestimo> buscaEquipamentosAluno(int idAluno) {
        String sql = "select * from Emprestimo_has_Equipamento natural join Equipamento natural join Emprestimo where idAluno = ? order by idEmprestimo;";

        List<Emprestimo> emprestimos = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1,idAluno);
            ResultSet rs = stmt.executeQuery();

            int idEmprestimo = 0;
            int novaidEmprestimo = 0;

            Emprestimo emprestimo = null;

            if(rs.next()){
                novaidEmprestimo = rs.getInt("idEmprestimo");
                idEmprestimo = novaidEmprestimo;
                emprestimo = new Emprestimo(
                        rs.getInt("idEmprestimo"),
                        rs.getInt("idAluno"),
                        rs.getString("dataRetirada"),
                        rs.getString("dataEntrega"),
                        rs.getString("dataAgendadaEntrega"),
                        rs.getString("dataAgendadaRetirada"),
                        rs.getInt("motivo"),
                        rs.getInt("count"));
            }

            rs.previous();

            while (rs.next()){
                novaidEmprestimo = rs.getInt("idEmprestimo");
                if (novaidEmprestimo == idEmprestimo){
                    Equipamento equipamento = new Equipamento(
                            rs.getInt("idEquipamento"),
                            rs.getString("descricao"));
                    emprestimo.addEquipamento(equipamento);
                    if (rs.isLast()){
                        emprestimos.add(emprestimo);
                    }
                }
                else {
                    emprestimos.add(emprestimo);
                    emprestimo = new Emprestimo(
                            rs.getInt("idEmprestimo"),
                            rs.getInt("idAluno"),
                            rs.getString("dataRetirada"),
                            rs.getString("dataEntrega"),
                            rs.getString("dataAgendadaEntrega"),
                            rs.getString("dataAgendadaRetirada"),
                            rs.getInt("motivo"),
                            rs.getInt("count"));

                    Equipamento equipamento = new Equipamento(
                            rs.getInt("idEquipamento"),
                            rs.getString("descricao"));
                    emprestimo.addEquipamento(equipamento);
                    idEmprestimo = novaidEmprestimo;
                    if (rs.isLast()){
                        emprestimos.add(emprestimo);
                    }
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return emprestimos;
    }
}
