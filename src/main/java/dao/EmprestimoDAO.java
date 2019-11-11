package dao;

import db.ConnectionFactory;
import pojo.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class EmprestimoDAO {
    public int criaEmprestimo(Emprestimo emprestimo) {
        String sql = "insert into Emprestimo (idAluno,dataRetirada," +
                "dataAgendadaEntrega,dataAgendadaRetirada,motivo,count) values (?,?,?,?,?,?)";
        int id = 0;
        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, RETURN_GENERATED_KEYS)) {

            stmt.setInt(1,emprestimo.getIdAluno());
            stmt.setString(2,emprestimo.getDataRetirada());
            stmt.setString(3,emprestimo.getDataAgendadaEntrega());
            stmt.setString(4,emprestimo.getDataAgendadaRetirada());
            stmt.setInt(5,emprestimo.getMotivo());
            stmt.setInt(6,emprestimo.getCount());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return id;
    }

    public int atualizaEmprestimo(Emprestimo emprestimo) {
        String sql = "update Emprestimo set dataRetirada = ?, dataEntrega = ?, dataAgendadaEntrega = ?, " +
                "dataAgendadaRetirada = ?, count = ?, motivo = ? " +
                "where idEmprestimo = ? and idAluno = ?";
        int rows = 0;
        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1,emprestimo.getDataRetirada());
            stmt.setString(2,emprestimo.getDataEntrega());
            stmt.setString(3,emprestimo.getDataAgendadaEntrega());
            stmt.setString(4,emprestimo.getDataAgendadaRetirada());
            stmt.setInt(5,emprestimo.getCount());
            stmt.setInt(6,emprestimo.getMotivo());
            stmt.setInt(7,emprestimo.getIdEmprestimo());
            stmt.setInt(8,emprestimo.getIdAluno());
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return rows;
    }

    public List<Emprestimo> EmprestimoEstadoAluno(int idAluno, boolean estado) {
        String sql;
        if(estado){
            sql = "select * from Emprestimo natural join Aluno where dataEntrega is not NULL and idAluno = ?";
        }else {
            sql = "select * from Emprestimo natural join Aluno where dataEntrega is NULL and idAluno = ?";
        }
        List<Emprestimo> lista = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1,idAluno);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                lista.add(new Emprestimo(
                        rs.getInt("idEmprestimo"),
                        rs.getInt("idAluno"),
                        rs.getString("dataRetirada"),
                        rs.getString("dataEntrega"),
                        rs.getString("dataAgendadaEntrega"),
                        rs.getString("dataAgendadaRetirada"),
                        rs.getInt("motivo"),
                        rs.getInt("count")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return lista;
    }
}
