package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class BSTWindow extends Application {
	/* TODO add verification and error messages for user inputs
	 * TODO indent results as a tree when printing
	 * 
	 * Done - find people with different length names
	 * 
	 * A Grade:
	 * TODO Allow name changes
	 * TODO Monitor Skew
	 * TODO Graphics
	 * */

	public static TreeModel trees;
	
	@Override
	public void start(Stage primaryStage) {
		
		trees = TreeModel.getInstance();
		fileLoader(new File("DefaultPeople.txt"));
		
		try {
			BorderPane root = new BorderPane();
			ListView<String> center = new ListView<String>();
			center.setItems(trees.getOutput());
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
		
		// data management controls
		Button generateDataButton = new Button("Generate Data");
		Button saveDataButton = new Button("Save Data");
		Button loadDataButton = new Button("Load Data");
		controls.getChildren().addAll(generateDataButton, saveDataButton, loadDataButton);
		
		// set actions for data management buttons
		generateDataButton.setOnMouseClicked(event -> {
			generateData();			
		});
		
		loadDataButton.setOnMouseClicked(event -> {
			loadData();			
		});
		
		// print controls
		ChoiceBox<String> treeChoiceBox = new ChoiceBox<String>();
		ObservableList<String> treeList = treeChoiceBox.getItems();
		treeList.add("Age");
		treeList.add("First Name");
		treeList.add("Last Name");
		
		ChoiceBox<String> printTypeChoiceBox = new ChoiceBox<String>();
		ObservableList<String> printTypeList = printTypeChoiceBox.getItems();
		printTypeList.add("In-Order Depth First");
		printTypeList.add("Breath First");
		printTypeList.add("Pre-Order Depth First");
		printTypeList.add("Post-Order Depth First");
		
		Button printTreeButton = new Button("Print Tree");
		controls.getChildren().addAll(treeChoiceBox, printTypeChoiceBox, printTreeButton);
		
		printTreeButton.setOnMouseClicked(event -> {
			trees.print(treeChoiceBox.getValue(), printTypeChoiceBox.getValue());		
		});
		
		
		// search controls
		ChoiceBox<String> searchTypeChoiceBox = new ChoiceBox<String>();
		ObservableList<String> searchTypeeList = searchTypeChoiceBox.getItems();
		searchTypeeList.add("Age");
		searchTypeeList.add("First Name");
		searchTypeeList.add("Last Name");
		
		TextField searchTextField = new TextField();
		
		Button searchButton = new Button("Search");
		
		controls.getChildren().addAll(searchTypeChoiceBox, searchTextField, searchButton);
		
		searchButton.setOnMouseClicked(event -> {
			trees.search(searchTypeChoiceBox.getValue(), searchTextField.getText());		
		});
		
		// search by length controls
		ChoiceBox<String> nameTypeChoiceBox = new ChoiceBox<String>();
		ObservableList<String> nameTypeList = nameTypeChoiceBox.getItems();
		nameTypeList.add("First Name");
		nameTypeList.add("Last Name");
		nameTypeList.add("Full Name");
		
		ChoiceBox<String> comparisonTypeChoiceBox = new ChoiceBox<String>();
		ObservableList<String> comparisonTypeList = comparisonTypeChoiceBox.getItems();
		comparisonTypeList.add("Greater Than");
		comparisonTypeList.add("Less Than");
		comparisonTypeList.add("Equal To");
		
		TextField lengthTextField = new TextField();
		
		Button lengthSearchButton = new Button ("Search");
		
		controls.getChildren().addAll(nameTypeChoiceBox, comparisonTypeChoiceBox, lengthTextField, lengthSearchButton);
		
		lengthSearchButton.setOnMouseClicked(event -> {
			trees.lengthSearch(nameTypeChoiceBox.getValue(), comparisonTypeChoiceBox.getValue(), lengthTextField.getText());		
		});
		
		
		return controls;
	}
	
	private void generateData() {
		
		// ArrayLists of names to randomly choose from when generating randon names
		ArrayList<String> firstNames = new ArrayList<String>();
		ArrayList<String> lastNames = new ArrayList<String>();
		
		
		// generate data, load to system and save to the file
		ArrayList<Person> newPeople = new ArrayList<Person>();
		
		try (Scanner scanner = new Scanner(new File("Names.txt"))) {
			while(scanner.hasNext()) {
				// get names and add to names lists
				firstNames.add(scanner.next());
				lastNames.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		// create people with random combinations of firstName, lastName and age
		for (int i = 0; i < 20; i++) {
			String firstName = firstNames.get((int) Math.floor(Math.random()*(firstNames.size())+0));
			String lastName = lastNames.get((int) Math.floor(Math.random()*(lastNames.size())+0));
			int age = (int) Math.floor(Math.random()*(100)+0);
			Person newPerson = new Person(firstName, lastName, age);
			// add person to list for later writing to file
			newPeople.add(newPerson);
		}
		
		
		// Create a new file to write to
		Stage saveStage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File to Save New Data");
		fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		
		File newFile = fileChooser.showSaveDialog(saveStage);
		
		if (!newFile.getName().endsWith(".txt")){
			newFile = new File(newFile.getAbsolutePath() + ".txt"); // adds .txt to the end of a new file name
		}
		
		
		// write to the new file
        if (newFile != null) { 
			try {
				PrintStream ps = new PrintStream(newFile);
				for (Person person : newPeople) {
					ps.println(person.toString());
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }	
	}
	
	
	private void saveData() {
		// take the data that is currently loaded into the system and save it to a file
		//ask the user where they would like to save the data to
		Stage saveStage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Data");
		File file = fileChooser.showSaveDialog(saveStage);
        if (file != null) {
            
        }
	}
	
	
	private void loadData() {
		
		// choose a file to load
		Stage openStage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select a File to Load");
		fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File loadFile = fileChooser.showOpenDialog(openStage);

		fileLoader(loadFile);
		
	}
	
	private void fileLoader(File loadFile) {
		
		// create comparators and BSTs 
		PersonAgeComparator ageComparator = new PersonAgeComparator();
		PersonFirstNameComparator firstNameComparator = new PersonFirstNameComparator();
		PersonLastNameComparator lastNameComparator = new PersonLastNameComparator();
		trees.setAgeTree(new BinarySearchTree<Person>(ageComparator));
		trees.setFirstNameTree(new BinarySearchTree<Person>(firstNameComparator));
		trees.setLastNameTree(new BinarySearchTree<Person>(lastNameComparator));
		
		// scan file and load data into BSTs
		if (loadFile.getName().endsWith(".txt")) {
			try (Scanner scanner = new Scanner(loadFile)) {
				while(scanner.hasNext()) {
					// get data and create Person
					int ID = scanner.nextInt();
					String firstName = scanner.next();
					String lastName = scanner.next();
					int age = scanner.nextInt();
					
					// load newPerson into BSTs
					Person newPerson = new Person(ID, firstName, lastName, age);
					System.out.println(newPerson.toString());
					trees.getAgeTree().add(newPerson);
					trees.getFirstNameTree().add(newPerson);
					trees.getLastNameTree().add(newPerson);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
