package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class BSTWindow extends Application {
	
	public static TreeModel trees;
	
	@Override
	public void start(Stage primaryStage) {
		
		trees = TreeModel.getInstance();
		try {
			BorderPane root = new BorderPane();
			ListView<String> center = new ListView<String>();
			center.setItems(trees.getOutput());
			trees.ageTreeTest();
			root.setCenter(center);
			root.setLeft(setUpControls());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private VBox setUpControls() {
		VBox controls = new VBox();
		
		// create data management buttons
		Button generateDataButton = new Button("Generate Data");
		Button saveDataButton = new Button("Save Data");
		Button loadDataButton = new Button("Load Data");
		controls.getChildren().addAll(generateDataButton, saveDataButton, loadDataButton);
		
		// set actions for data management buttons
		generateDataButton.setOnMouseClicked(event -> {
			generateData();			
		});
		
		return controls;
	}
	
	private void generateData() {
		//ask the user where they would like to save the data to
		// generate data and save it to the file
		// load the generated data into the system
	}
	
	private void saveData() {
		// take the data that is currently loaded into the system and save it to a file
	}
	
	private void loadData() {
		// let the user choose a tile to load into the system
	}
}
