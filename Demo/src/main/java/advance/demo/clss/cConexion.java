package advance.demo.clss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class cConexion {
    private static final String URL = "jdbc:mysql://localhost:3306/DBCuetillo";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "";
    private static cConexion instancia;

    public cConexion() {
        // Constructor privado para evitar la creación de instancias
    }

    public static cConexion obtenerInstancia() {
        if (instancia == null) {
            instancia = new cConexion();
        }
        return instancia;
    }

    public Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("Conexión establecida correctamente");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
        }
        return conexion;
    }

    public List<cAlumno> obtenerDatosAlumnos() {
        List<cAlumno> alumnos = new ArrayList<>();
        String query = "SELECT * FROM tAlumnos";
        try (Connection conexion = conectar();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            Map<String, String> carreras = obtenerCarreras();
            Map<String, String> semestres = obtenerSemestres();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String nombre = resultSet.getString("Nombre");
                String apellido = resultSet.getString("Apellido");
                int edad = resultSet.getInt("Edad");
                int dni = resultSet.getInt("DNI");
                char genero = resultSet.getString("Genero").charAt(0);
                String carreraId = resultSet.getString("Carrera");
                String semestreId = resultSet.getString("Semestre");

                String carrera = carreras.get(carreraId);
                String semestre = semestres.get(semestreId);

                cAlumno alumno = new cAlumno(id, nombre, apellido, edad, dni, genero, carrera, semestre);
                alumnos.add(alumno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnos;
    }
    
    public List<cAsignaturas> obtenerDatosAsignatura() {
        List<cAsignaturas> asignatura = new ArrayList<>();
        String query = "SELECT * FROM tAsignaturas";
        try (Connection conexion = conectar();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            Map<String, String> carreras = obtenerCarreras();
            Map<String, String> semestres = obtenerSemestres();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String descripcion = resultSet.getString("Descripcion");
                String semestreId = resultSet.getString("Semestre");
                int numeroCreditos = resultSet.getInt("numeroCreditos");
                String carreraId = resultSet.getString("Carrera");

                String carrera = carreras.get(carreraId);
                String semestre = semestres.get(semestreId);

                cAsignaturas asig = new cAsignaturas(id, descripcion, semestre, numeroCreditos, carrera);
                asignatura.add(asig);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asignatura;
    }

    public Map<String, String> obtenerCarreras() 
    {
        Map<String, String> carreras = new LinkedHashMap<>();
        String query = "SELECT ID, Descripcion FROM tCarrera";
        try (Connection conexion = conectar();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String carreraId = resultSet.getString("ID");
                String descripcion = resultSet.getString("Descripcion");
                carreras.put(carreraId, descripcion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carreras;
    }
    
    public Map<String, String> obtenerSemestres() 
    {
        Map<String, String> semestres = new LinkedHashMap<>();
        String query = "SELECT ID, Descripcion FROM tSemestres";
        try (Connection conexion = conectar();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String semestreId = resultSet.getString("ID");
                String descripcion = resultSet.getString("Descripcion");
                semestres.put(semestreId, descripcion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return semestres;
    }

    public boolean modificarAlumno(cAlumno alumno, String semestre, String carrera) 
    {
        try (Connection connection = conectar()) {
            String sql = "UPDATE tAlumnos SET nombre = ?, apellido = ?, edad = ?, dni = ?, genero = ?, carrera = ?, semestre = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, alumno.getNombre());
            statement.setString(2, alumno.getApellido());
            statement.setInt(3, alumno.getEdad());
            statement.setInt(4, alumno.getDNI());
            statement.setString(5, String.valueOf(alumno.getGenero()));
            statement.setString(6, getIdCarrera(carrera));
            statement.setString(7, getIdSemestre(semestre));
            statement.setString(8, alumno.getId());

            int filasAfectadas = statement.executeUpdate();

            statement.close();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean modificarAsignatura(cAsignaturas asignatura, String semestre, String carrera) 
    {
        try (Connection connection = conectar()) {
            String sql = "UPDATE tAsignaturas SET Descripcion = ?, Semestre = ?, numeroCreditos = ?, Carrera = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, asignatura.getDescripcion());
            statement.setString(2, getIdSemestre(semestre));
            statement.setInt(3, asignatura.getNumeroCreditos());
            statement.setString(4, getIdCarrera(carrera));
            statement.setString(5, asignatura.getId());

            int filasAfectadas = statement.executeUpdate();

            statement.close();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean agregarAlumno(cAlumno alumno, String Semestre, String Carrera) {
        try (Connection connection = conectar()) {
            int siguienteNumero = obtenerSiguienteNumeroAlumno();
            String idAlumno = String.format("A%04d", siguienteNumero);

            String sql = "INSERT INTO tAlumnos (id, nombre, apellido, edad, dni, genero, carrera, semestre) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idAlumno);
            statement.setString(2, alumno.getNombre());
            statement.setString(3, alumno.getApellido());
            statement.setInt(4, alumno.getEdad());
            statement.setInt(5, alumno.getDNI());
            statement.setString(6, String.valueOf(alumno.getGenero()));
            statement.setString(7, getIdCarrera(Carrera));
            statement.setString(8, getIdSemestre(Semestre));

            int filasAfectadas = statement.executeUpdate();

            statement.close();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregarAsignatura(cAsignaturas asignatura, String Semestre, String Carrera) {
        try (Connection connection = conectar()) {
            int siguienteNumero = obtenerSiguienteNumeroAsignatura();
            String idAsignatura = String.format("AS%04d", siguienteNumero);

            String sql = "INSERT INTO tAsignaturas (id, Descripcion, Semestre, numeroCreditos, Carrera) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idAsignatura);
            statement.setString(2, asignatura.getDescripcion());
            statement.setString(3, getIdSemestre(Semestre));
            statement.setInt(4, asignatura.getNumeroCreditos());
            statement.setString(5, getIdCarrera(Carrera));

            int filasAfectadas = statement.executeUpdate();

            statement.close();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private int obtenerSiguienteNumeroAlumno() {
        int siguienteNumero = 0;
        String query = "SELECT MAX(CAST(SUBSTRING(id, 2) AS UNSIGNED)) AS max_numero FROM tAlumnos";
        try (Connection conexion = conectar();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                siguienteNumero = resultSet.getInt("max_numero") + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(cConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return siguienteNumero;
    }
    
    private int obtenerSiguienteNumeroAsignatura() {
        int siguienteNumero = 0;
        String query = "SELECT MAX(CAST(SUBSTRING(id, 2) AS UNSIGNED)) AS max_numero FROM tAsignatura";
        try (Connection conexion = conectar();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                siguienteNumero = resultSet.getInt("max_numero") + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(cConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return siguienteNumero;
    }    
    
    private String getIdCarrera(String descripcion) 
    {
        String query = "SELECT ID FROM tCarrera WHERE Descripcion = ?";
        String carreraId = "";
        try (Connection conexion = conectar();PreparedStatement statement = conexion.prepareStatement(query)) 
        {

            statement.setString(1, descripcion);
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                while (resultSet.next()) 
                {
                    carreraId = resultSet.getString("ID");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(cConexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return carreraId;
    }
    
    private String getIdSemestre(String descripcion) 
    {
        String query = "SELECT ID FROM tSemestres WHERE Descripcion = ?";
        String semestreId = "";
        try (Connection conexion = conectar();PreparedStatement statement = conexion.prepareStatement(query)) 
        {

            statement.setString(1, descripcion);
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                while (resultSet.next()) 
                {
                    semestreId = resultSet.getString("ID");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(cConexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return semestreId;
    }    
}
