package advance.demo;
import advance.demo.clss.cConexion;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
   
    private static Scene scene;
    @Override
    public void start(Stage primaryStage) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        AnchorPane root = loader.load();

        // Crear una escena y asignarla a la ventana principal
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sistema de Pagos Cuetillo");
        
        //initRootLayout();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {     
        try {
            MostrarVentana("Inicio");
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
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
	
    public Stage getPrimaryStage() {
            return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
     
   
}