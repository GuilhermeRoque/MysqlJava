package dao;

import db.ConnectionFactory;
import pojo.Equipamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class EquipamentoDAO {

    public boolean verificaEquipamentoEmprestado(int idEquipamento){
        String sql = "select * from Emprestimo_has_Equipamento natural join Emprestimo where idEquipamento = ? and dataEntrega is NULL";
        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1,idEquipamento);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                rs.close();
                return true;
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }

        return false;
    }

    public List<Equipamento> buscaEquipamentoEmprestado(int idEmprestimo,int idAluno){

        String sql = "select idEquipamento,descricao from Emprestimo_has_Equipamento natural join Emprestimo natural join Equipamento where idEmprestimo = ? and idAluno = ? and dataEntrega is NULL";
        List<Equipamento> equipamentos = new ArrayList<>();

        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1,idEmprestimo);
            stmt.setInt(2,idAluno);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                equipamentos.add(new Equipamento(
                        rs.getInt("idEquipamento"),
                        rs.getString("descricao")
                ));
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }

        return equipamentos;
    }

    public int criaEmprestimoEquipamento(int idEquipamento,int idEmprestimo,int idAluno){
        String sql = "insert into Emprestimo_has_Equipamento (idEquipamento,idEmprestimo,idAluno) values (?,?,?)";
        int id = 0;
        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql,RETURN_GENERATED_KEYS)) {

            stmt.setInt(1,idEquipamento);
            stmt.setInt(2,idEmprestimo);
            stmt.setInt(3,idAluno);
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

}
