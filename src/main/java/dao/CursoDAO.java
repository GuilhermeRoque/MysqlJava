package dao;

import db.ConnectionFactory;
import pojo.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class CursoDAO {

    public Curso buscaCurso(int idCurso) {
        String sql = "select * from Curso where idCurso = ?";
        Curso curso = null;
        try (Connection conexao = ConnectionFactory.getDBConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, RETURN_GENERATED_KEYS)) {

            stmt.setInt(1,idCurso);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                curso = new Curso(
                        rs.getString("nome"),
                        rs.getString("inicioLetivo"),
                        rs.getString("fimLetivo"),
                        rs.getString("inicioFeriasInverno"),
                        rs.getString("fimFeriasInverno")
                );
            }

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return curso;
    }
}
