package backend;

import Entities.Inscripcion;
import Backend.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;




public class InscripcionDAOJDBC implements InscripcionDAO {


    public static final String INSERTAR = "insertar";
    public static final String ACTUALIZAR = "actualizar";
    public static final String FINDALL = "listar";
    public static final String TRUNCATE = "truncate";
    private <T> T conexion(Function<Connection, T> criterio, String accion){
        T resultado;
        try (Connection connection =ConnectionManager.getConnection()) {
            connection.setAutoCommit(false);


            resultado=criterio.apply(connection);


            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Error al " + accion + " usuario", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al " + accion + " usuario", e);
        }
        return resultado;
    }

    @Override
    public void create(Inscripcion inscripcion) {
        if(inscripcion==null)throw  new IllegalArgumentException("La inscripcion es nulla,no se puede persistir");


        final String SQL="INSERT INTO inscripciones (id,hora_inscripcion,id_concurso,id_participante) VALUES (?,?,?,?)";

        this.conexion((connection) -> {;
            Date sqlDate = Date.valueOf(inscripcion.getFechaInscripcion());
            try(PreparedStatement statement = connection.prepareStatement(SQL)) {

                statement.setString(1, inscripcion.getId());
                statement.setDate(2, sqlDate);
                statement.setString(3, inscripcion.getConcursoId());
                statement.setString(4, inscripcion.getParticipanteId());
                statement.executeUpdate();
                return null;
            } catch (SQLException e) {
                throw new RuntimeException("Error al preparar la consulta de " + INSERTAR + " usuario", e);
            }
        }, INSERTAR);

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

        final String SQL = "SELECT * FROM inscripciones ";


        return this.conexion((connection) -> {
            List<String> inscriptos = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(SQL);
                 ResultSet rs = statement.executeQuery(SQL)) {

                while (rs.next()) {

                    LocalDate localDate = rs.getDate("hora_inscripcion").toLocalDate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    String horaString = localDate.format(formatter);
                    String inscripto = horaString + ", " + rs.getString("id_participante") + ", " + rs.getString("id_concurso");

                    inscriptos.add(inscripto);


                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return inscriptos;
        }, FINDALL);
    }
    @Override
    public void truncateTabla(){
        final String SQL = "TRUNCATE TABLE inscripciones";

        this.conexion((connection) -> {
            try (PreparedStatement statement = connection.prepareStatement(SQL)) {
                statement.executeUpdate();
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, TRUNCATE);
    }


}
