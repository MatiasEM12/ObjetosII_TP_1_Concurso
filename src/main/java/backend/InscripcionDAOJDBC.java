package backend;

import Entities.Inscripcion;
import backend.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAOJDBC implements  InscripcionDAO {
    @Override
    public void create(Inscripcion inscripcion) {
        if(inscripcion==null)throw  new IllegalArgumentException("La inscripcion es nulla,no se puede persistir");

        final String SQL="INSERT INTO inscripciones (id,hora_inscripcion,id_concurso,id_participante) VALUES (?,?,?,?)";

        try(Connection conn=ConnectionManager.getConnection();
            PreparedStatement st= conn.prepareStatement(SQL)){

            Date sqlDate = Date.valueOf(inscripcion.fechaInscripcion());

            st.setString(1,inscripcion.id());
            st.setDate(2,sqlDate);
            st.setString(3,inscripcion.concursoId());
            st.setString(4,inscripcion.participanteId());
            st.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
           ConnectionManager.disconnect();
        }
    }

    @Override
    public void update(Inscripcion inscripcion) {
        throw new RuntimeException("InscriptosDAOJDBC update no implementado ");
    }

    @Override
    public void remove(String id) {
        throw new RuntimeException("InscriptosDAOJDBC remove por id no implementado ");
    }

    @Override
    public void remove(Inscripcion inscripcion) {
        throw new RuntimeException("InscriptosDAOJDBC remove por inscripcion no implementado ");
    }

    @Override
    public Inscripcion find(String id) {
        throw new RuntimeException("InscriptosDAOJDBC find por id no implementado ");
    }

    @Override
    public List<Inscripcion> findAll() {
        throw new RuntimeException("InscriptosDAOJDBC Listar por inscripcion no implementado ");
    }

    @Override
    public List<String> findAllInscriptos() {

        List<String>inscriptos= new ArrayList<>();
        final String SQL="SELECT * FROM inscripciones ";

        try(Connection conn=ConnectionManager.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL)){

            while(rs.next()) {

                LocalDate localDate = rs.getDate("hora_inscripcion").toLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                String horaString = localDate.format(formatter);
                String inscripto= horaString+", "+ rs.getString("id_participante") +", "+rs.getString("id_concurso");

                inscriptos.add(inscripto);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            ConnectionManager.disconnect();
        }

        return inscriptos;
    }

    public void truncateTabla() {
        final String SQL = "TRUNCATE TABLE inscripciones";

        try (Connection conn=ConnectionManager.getConnection();
             PreparedStatement st= conn.prepareStatement(SQL)) {

            st.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            ConnectionManager.disconnect();
        }
    }


}