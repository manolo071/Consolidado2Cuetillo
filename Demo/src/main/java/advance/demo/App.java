package advance.demo;
import advance.demo.clss.cConexion;
import advance.demo.clss.cAlumno;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<cAlumno> personData = FXCollections.observableArrayList();
	public App() {
		// Add some sample data
		personData.add(new cAlumno("A0001","Hans", "Muster",15,72696560,'M',"C0001","S0001"));
		personData.add(new cAlumno("A0002","Ruth", "Mueller",15,72696560,'M',"C0001","S0001"));
		personData.add(new cAlumno("A0003","Heinz", "Kurz",15,72696560,'M',"C0001","S0001"));
		personData.add(new cAlumno("A0004","Cornelia", "Meier",15,72696560,'M',"C0001","S0001"));
		personData.add(new cAlumno("A0005","Werner", "Meyer",15,72696560,'M',"C0001","S0001"));
		personData.add(new cAlumno("A0006","Lydia", "Kunz",15,72696560,'M',"C0001","S0001"));
		personData.add(new cAlumno("A0007","Anna", "Best",15,72696560,'M',"C0001","S0001"));
		personData.add(new cAlumno("A0008","Stefan", "Meier",15,72696560,'M',"C0001","S0001"));
		personData.add(new cAlumno("A0009","Martin", "Mueller",15,72696560,'M',"C0001","S0001"));
        }
        
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sistema de Pagos Cuetillo");
        
        initRootLayout();

        MostrarVentana("Inicio");
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cConexion conexion = new cConexion();
    }

    public void MostrarVentana(String ventana) 
    {
        try 
        {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(ventana+".fxml"));
            AnchorPane Ventana = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(Ventana);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
	
    public Stage getPrimaryStage() {
            return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    //
    public ObservableList<cAlumno> getPersonData() 
    {
        return personData;
    }
        
    public boolean showAlumnoEditDialog(cAlumno alumno) 
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AlumnoEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(alumno);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }   
}