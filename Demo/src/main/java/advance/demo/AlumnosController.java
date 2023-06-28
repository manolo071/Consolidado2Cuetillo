/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package advance.demo;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author AJ
 */
public class AlumnosController implements Initializable {

    @FXML
    private TableView tblAlumnos;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private Spinner spEdad;
    @FXML
    private TextField txtDNI;
    @FXML
    private RadioButton rbMasculino;
    @FXML
    private RadioButton rbFemenino;
    @FXML
    private Button btnAgregar;
    @FXML
    private ComboBox<String> cbCarrera;
    @FXML
    private ComboBox<String> cbSemestre;
    @FXML
    private TextField txtId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Start();        
        InicializarTable();
        CargarDatos();
        Nuevo();   
        btnAgregar.setDisable(true);
    }   
    
    public void Start()
    {       

        ToggleGroup toggleGroup = new ToggleGroup();    
        rbMasculino.setToggleGroup(toggleGroup);
        rbFemenino.setToggleGroup(toggleGroup);
        SpinnerValueFactory<Integer> edadValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        spEdad.setValueFactory(edadValueFactory);        
        rbMasculino.setSelected(true);
    }
    
    public void InicializarTable()
    {
        TableColumn<cAlumno, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setVisible(false);

        TableColumn<cAlumno, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<cAlumno, String> apellidoColumn = new TableColumn<>("Apellido");
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        TableColumn<cAlumno, Integer> edadColumn = new TableColumn<>("Edad");
        edadColumn.setCellValueFactory(new PropertyValueFactory<>("edad"));

        TableColumn<cAlumno, Integer> dniColumn = new TableColumn<>("DNI");
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("DNI"));

        TableColumn<cAlumno, Character> generoColumn = new TableColumn<>("Género");
        generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));

        TableColumn<cAlumno, String> carreraColumn = new TableColumn<>("Carrera");
        carreraColumn.setCellValueFactory(new PropertyValueFactory<>("carrera"));

        TableColumn<cAlumno, String> semestreColumn = new TableColumn<>("Semestre");
        semestreColumn.setCellValueFactory(new PropertyValueFactory<>("semestre"));

        // Agregar las columnas al TableView
        tblAlumnos.getColumns().addAll(idColumn, nombreColumn, apellidoColumn, edadColumn, dniColumn, generoColumn, carreraColumn, semestreColumn);
    }
    
    public void CargarDatos()
    {        
        cConexion con = new cConexion();
        Map<String, String> carreras = con.obtenerCarreras();
        Map<String, String> semestres = con.obtenerSemestres();
        ObservableList<String> opcionesCarreras = FXCollections.observableArrayList(carreras.values());
        ObservableList<String> opcioneSemestres = FXCollections.observableArrayList(semestres.values());

        cbCarrera.setItems(opcionesCarreras);
        cbSemestre.setItems(opcioneSemestres);

        List<cAlumno> alumnos = con.obtenerDatos();
        ObservableList<cAlumno> data = FXCollections.observableArrayList(alumnos);
        tblAlumnos.setItems(data);
    }
    
    @FXML
    public void RecuperarDatos() 
    {
        tblAlumnos.getSelectionModel().selectedItemProperty().addListener((ObservableValue obs, Object oldSelection, Object newSelection) -> 
        {
            if (newSelection != null) 
            {
                cAlumno alumnoSeleccionado =  (cAlumno) newSelection;
                String id = alumnoSeleccionado.getId();
                String nombre = alumnoSeleccionado.getNombre();
                String apellido = alumnoSeleccionado.getApellido();
                int edad = alumnoSeleccionado.getEdad();
                int dni = alumnoSeleccionado.getDNI();
                char genero = alumnoSeleccionado.getGenero();
                String carrera = alumnoSeleccionado.getCarrera();                
                String semestre = alumnoSeleccionado.getSemestre();
                txtId.setText(id);
                txtNombre.setText(nombre);
                txtApellido.setText(apellido);
                spEdad.getValueFactory().setValue(edad);                
                txtDNI.setText(dni+"");
                if(genero == 'M')
                {
                    rbMasculino.setSelected(true);
                }
                else
                {
                    rbFemenino.setSelected(true);
                }
                cbCarrera.setValue(carrera);
                cbSemestre.setValue(semestre);
            }
        });
        btnAgregar.setDisable(true);
    }
    
    public void RecuperarFields(int op) 
    {
        String Id = txtId.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        int edad = (int) spEdad.getValue();
        int dni = Integer.parseInt(txtDNI.getText());
        char genero = rbMasculino.isSelected() ? 'M' : 'F';
        String carrera = (String) cbCarrera.getSelectionModel().getSelectedItem();
        String semestre = (String) cbSemestre.getSelectionModel().getSelectedItem();
        if(op==1)
        {
            AgregarDatos(nombre, apellido, edad, dni, genero, carrera, semestre);
        }
        else
        {
            if(op==2)
            {
                ModificarDatos(Id, nombre, apellido, edad, dni, genero, carrera, semestre);
            }
        }
    }
    
    public void AgregarDatos(String nombre, String apellido, int edad, int dni, char genero,String carrera, String semestre) 
    {
        cAlumno alumno = new cAlumno(null, nombre, apellido, edad, dni, genero, null, null);
        cConexion con = new cConexion();
        boolean exito = con.agregarAlumno(alumno, semestre, carrera);

        if (exito) 
        {            
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Alumno agregado","El alumno se ha agregado correctamente."};
            Mensaje(alert, Mensaje);
            Nuevo();
            btnAgregar.setDisable(true);
            CargarDatos();
        } 
        else 
        {
            // Mostrar una alerta de error
            Alert alert = new Alert(AlertType.ERROR);
            String[] Mensaje = {"Error","Error al agregar alumno","Ha ocurrido un error al intentar agregar al alumno."};
            Mensaje(alert, Mensaje);
        }
    }
    
    public void ModificarDatos(String Id,String nombre, String apellido, int edad, int dni, char genero, String carrera, String semestre) 
    {
        cAlumno alumno = new cAlumno(Id, nombre, apellido, edad, dni, genero, null, null);
        cConexion con = new cConexion();
        boolean exito = con.modificarAlumno(alumno, semestre, carrera);

        if (exito) 
        {            
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Alumno modificado","El alumno se ha modificado correctamente."};
            Mensaje(alert, Mensaje);
            Nuevo();
            CargarDatos();
        } 
        else 
        {
            // Mostrar una alerta de error
            Alert alert = new Alert(AlertType.ERROR);
            String[] Mensaje = {"Error","Error al modificar alumno","Ha ocurrido un error al intentar modificar al alumno."};
            Mensaje(alert, Mensaje);
        }
    }
    
     @FXML
    public void Nuevo() 
    {
        btnAgregar.setDisable(false);
        txtId.setText("");
        txtId.setDisable(true);
        txtNombre.setText("");
        txtApellido.setText("");       
        txtDNI.setText("");
        cbCarrera.getSelectionModel().select(0);
        cbSemestre.getSelectionModel().select(0);
        rbMasculino.setSelected(true);
        spEdad.getValueFactory().setValue(16);
        txtNombre.requestFocus();
    }
    
    @FXML
    public void exit()
    {
        System.exit(0);
    }
    
    public void Mensaje(Alert alert, String[] Mensaje)
    {
        alert.setTitle(Mensaje[0]);
        alert.setHeaderText(Mensaje[1]);
        alert.setContentText(Mensaje[2]);
        alert.showAndWait();
    }

    @FXML
    private void AgregarDatos(ActionEvent event) {
        RecuperarFields(1);
    }
    
    @FXML
    private void ModifDatos(ActionEvent event) {
        RecuperarFields(2);
    }


}


