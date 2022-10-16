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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class BSTWindow extends Application {
	/* TODO add verification and error messages for user inputs
	 * Done indent results as a tree when printing
	 * TODO format breath first results when printing
	 * 
	 * Done - find people with different length names
	 * 
	 * A Grade:
	 * TODO Allow name changes
	 * 	- have options for changing first and last name
	 * Done -  delete a node from all trees but hold onto it to update it's name
	 * 	- update it's name
	 * 	- add it back to the tree
	 * 
	 *	
	 * TODO Monitor Skew
	 * Done - Graphics
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
			
			ScrollPane scrollPane = new ScrollPane(setUpControls());
		    scrollPane.setFitToHeight(true);
			
			
			root.setLeft(scrollPane);
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
		VBox dataManagementVB = new VBox();
		Label dataManagementHeading = new Label("Data Management");
		dataManagementHeading.getStyleClass().add("heading");
		
		Button generateDataButton = new Button("Generate Data");
		Button saveDataButton = new Button("Save Data");
		Button loadDataButton = new Button("Load Data");
		
		HBox buttonHBox = new HBox();
		buttonHBox.getStyleClass().add("controls");
		buttonHBox.getChildren().addAll(generateDataButton, saveDataButton, loadDataButton);
		dataManagementVB.getChildren().addAll(dataManagementHeading, buttonHBox);
		
		// set actions for data management buttons
		generateDataButton.setOnMouseClicked(event -> {
			generateData();			
		});
		
		loadDataButton.setOnMouseClicked(event -> {
			loadData();			
		});
		
		// print controls
		VBox printingVB = new VBox();
		printingVB.getStyleClass().add("controls");
		Label printingHeading = new Label("Display A Tree");
		printingHeading.getStyleClass().add("heading");
		
		Label chooseATree = new Label("Choose a tree to display:  ");
		ChoiceBox<String> treeChoiceBox = new ChoiceBox<String>();
		ObservableList<String> treeList = treeChoiceBox.getItems();
		treeList.add("Age");
		treeList.add("First Name");
		treeList.add("Last Name");
		
		Label chooseADisplay = new Label("Choose a display type:  ");
		ChoiceBox<String> printTypeChoiceBox = new ChoiceBox<String>();
		ObservableList<String> printTypeList = printTypeChoiceBox.getItems();
		printTypeList.add("In-Order Depth First");
		printTypeList.add("Breath First");
		printTypeList.add("Pre-Order Depth First");
		printTypeList.add("Post-Order Depth First");
		
		
		HBox chooseATreeHB = new HBox();
		chooseATreeHB.getChildren().addAll(chooseATree, treeChoiceBox);
		
		HBox chooseADisplayHB = new HBox();
		chooseADisplayHB.getChildren().addAll(chooseADisplay, printTypeChoiceBox);
		
		Button printTreeButton = new Button("Display Tree");
		printingVB.getChildren().addAll(printingHeading, chooseATreeHB, chooseADisplayHB, printTreeButton);
		
		printTreeButton.setOnMouseClicked(event -> {
			trees.print(treeChoiceBox.getValue(), printTypeChoiceBox.getValue());		
		});
		
		
		// search controls
		VBox searchVB = new VBox();
		searchVB.getStyleClass().add("controls");
		Label searchHeading = new Label("Search");
		searchHeading.getStyleClass().add("heading");
		
		Label searchBy = new Label("Search by:  ");
		ChoiceBox<String> searchTypeChoiceBox = new ChoiceBox<String>();
		ObservableList<String> searchTypeeList = searchTypeChoiceBox.getItems();
		searchTypeeList.add("Age");
		searchTypeeList.add("First Name");
		searchTypeeList.add("Last Name");
		
		Label searchFor = new Label("Search for:  ");
		TextField searchTextField = new TextField();
		
		HBox searchByHB = new HBox();
		searchByHB.getChildren().addAll(searchBy, searchTypeChoiceBox);
		
		HBox searchTextByHB = new HBox();
		searchTextByHB.getChildren().addAll(searchFor, searchTextField);
		
		Button searchButton = new Button("Search");
		
		searchVB.getChildren().addAll(searchHeading, searchByHB, searchTextByHB, searchButton);
		
		searchButton.setOnMouseClicked(event -> {
			trees.search(searchTypeChoiceBox.getValue(), searchTextField.getText());		
		});
		
		// search by length controls
		VBox searchLengthVB = new VBox();
		searchLengthVB.getStyleClass().add("controls");
		
		Label searchLengthHeading = new Label("Filter by Name Length");
		searchLengthHeading.getStyleClass().add("heading");
		
		Label filterBy = new Label("Filter by:  ");
		ChoiceBox<String> nameTypeChoiceBox = new ChoiceBox<String>();
		ObservableList<String> nameTypeList = nameTypeChoiceBox.getItems();
		nameTypeList.add("First Name");
		nameTypeList.add("Last Name");
		nameTypeList.add("Full Name");
		
		Label filterType = new Label("Filter type:  ");
		ChoiceBox<String> comparisonTypeChoiceBox = new ChoiceBox<String>();
		ObservableList<String> comparisonTypeList = comparisonTypeChoiceBox.getItems();
		comparisonTypeList.add("Greater Than");
		comparisonTypeList.add("Less Than");
		comparisonTypeList.add("Equal To");
		
		
		Label lengthLabel = new Label("Length: ");
		TextField lengthTextField = new TextField();
		
		HBox filterByHB = new HBox();
		filterByHB.getChildren().addAll(filterBy, nameTypeChoiceBox);
		
		HBox filterTypeHB = new HBox();
		filterTypeHB.getChildren().addAll(filterType, comparisonTypeChoiceBox);
		
		HBox lengthHB = new HBox();
		lengthHB.getChildren().addAll(lengthLabel, lengthTextField);
		
		Button lengthSearchButton = new Button ("Filter");
		
		searchLengthVB.getChildren().addAll(searchLengthHeading, filterByHB, filterTypeHB, lengthHB, lengthSearchButton);
		
		lengthSearchButton.setOnMouseClicked(event -> {
			trees.lengthSearch(nameTypeChoiceBox.getValue(), comparisonTypeChoiceBox.getValue(), lengthTextField.getText());		
		});
		
		
		// delete node by ID controls
		VBox deleteVB = new VBox();
		deleteVB.getStyleClass().add("controls");
		Label deleteHeading = new Label("Delete by ID");
		deleteHeading.getStyleClass().add("heading");
		
		Label deleteIDLabel = new Label("ID: ");
		TextField nodeIDTextField = new TextField();
		
		HBox deleteIDHB = new HBox();
		deleteIDHB.getChildren().addAll(deleteIDLabel, nodeIDTextField);
		
		Button deleteNodeButton = new Button("Delete");
		
		deleteVB.getChildren().addAll(deleteHeading, deleteIDHB, deleteNodeButton);
		
		deleteNodeButton.setOnMouseClicked(event -> {
			trees.deleteNode(nodeIDTextField.getText());	
		});
		
		
		dataManagementVB.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
		printingVB.setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		searchVB.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
		searchLengthVB.setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		deleteVB.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
		controls.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
		controls.getChildren().addAll(dataManagementVB, printingVB, searchVB, searchLengthVB, deleteVB);
		controls.getStyleClass().add("controls");
		controls.setPrefWidth(350);
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
		PersonIDComparator idComparator = new PersonIDComparator();
		trees.setAgeTree(new BinarySearchTree<Person>(ageComparator));
		trees.setFirstNameTree(new BinarySearchTree<Person>(firstNameComparator));
		trees.setLastNameTree(new BinarySearchTree<Person>(lastNameComparator));
		trees.setIdTree(new BinarySearchTree<Person>(idComparator));
		
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
					trees.getIdTree().add(newPerson);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
