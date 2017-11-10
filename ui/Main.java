package grapher.ui;

import grapher.fc.Function;
import grapher.fc.FunctionFactory;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;




public class Main extends Application {
        
        public void initTable(TableView<ColorFunction> table, GrapherCanvas canvas){
            table.setEditable(true);
            table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            //Colonne pour le ColorPicker
             TableColumn<ColorFunction,ColorPicker> colorCol = new TableColumn<ColorFunction,ColorPicker>("Couleur");
             colorCol.setCellValueFactory(new PropertyValueFactory<ColorFunction, ColorPicker>("color"));
             
             //Colonne pour la fonction
             TableColumn<ColorFunction,Function> functionCol = new TableColumn<ColorFunction,Function>("Fonction");
             functionCol.setCellValueFactory(new PropertyValueFactory<ColorFunction, Function>("function"));
             //Nous la rendons editable
             functionCol.setEditable(true);
             functionCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Function>() {
                @Override
                    public String toString(Function object) {
                        return object.toString();
                    }

                    @Override
                    public Function fromString(String string) {
                        
                        return FunctionFactory.createFunction(string);
                    }
             }));
             functionCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ColorFunction, Function>>() {
                 @Override
                 public void handle(TableColumn.CellEditEvent<ColorFunction, Function> event) {
                     final Function f = event.getNewValue();
                     ColorFunction col = event.getRowValue();
                     col.setFunction(f);
                        int index = event.getTablePosition().getRow();
                        table.getItems().set(index, col);
                 }
             });
             
             //Quand une fonction de la liste va changer, etre supprimee ou ajoutee
             table.getItems().addListener(new InvalidationListener() {
                 @Override
                 public void invalidated(Observable observable) {
                     ArrayList<ColorFunction> func = new ArrayList<>();
                     for(ColorFunction col : table.getItems()){
                         func.add(col);
                     }
                     canvas.initFunctions(func);     
                 }
             });
             
             //Quand on va selectionner une ligne
             table.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                      
                        ObservableList<TablePosition> list = table.getSelectionModel().getSelectedCells();
                        ArrayList<Integer> intList = new ArrayList<>();
                        for(TablePosition tab : list)
                            intList.add(tab.getRow());
                        canvas.setBoldFunction(intList);
                    }
                });
             
             //Assigner les colonnes
             table.getColumns().setAll(colorCol,functionCol);
             //Peupler notre table avec les fonctions passees en parametres
             ObservableList<ColorFunction> list = FXCollections.observableArrayList();
            for(String expression : getParameters().getRaw()) {
			Function f = FunctionFactory.createFunction(expression);
                        ColorPicker color = new ColorPicker(Color.BLACK);
                        color.setOnAction(new ColorPickerHandler(table, canvas));
                        list.add(new ColorFunction(f, color));
            }    
            table.getItems().addAll(list);
        }
        
	public void start(Stage stage) {
                //Creation du canvas
                GrapherCanvas canvas = new GrapherCanvas(getParameters());
                
                
                //Creation et initialisation de la table
                TableView<ColorFunction> table = new TableView<>();
                initTable(table,canvas);
                
                //Le BorderPane qui sera placé a la racine de notre scene
		BorderPane root = new BorderPane();
                
                //Barre des boutons ajouter et supprimer
                ToolBar buttonBar = new ToolBar();
                //Boutons ajouter et supprimer
                Button add = new Button("+");
                add.setOnAction(new AddHandlerTable(table,canvas));
                Button remove = new Button("-");
                remove.setOnAction(new RemoveHandlerTable(table));
                buttonBar.getItems().addAll(add,remove);
                
                //Le BorderPane pour la list et les boutons
                BorderPane buttonPane = new BorderPane();
                buttonPane.setTop(table);
                buttonPane.setBottom(buttonBar);
                //Le splitPane pour séparer le canvas de la liste
                
                //Gestion du menu
                MenuBar menuBar = new MenuBar();
                Menu expression = new Menu("Expression");
                MenuItem addItem = new MenuItem("Ajouter");
                MenuItem removeItem = new MenuItem("Supprimer");
                addItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
                addItem.setOnAction(new AddHandlerTable(table,canvas));
                removeItem.setAccelerator(new KeyCodeCombination(KeyCode.BACK_SPACE, KeyCombination.CONTROL_DOWN));
                removeItem.setOnAction(new RemoveHandlerTable(table));
                menuBar.getMenus().add(expression);
                expression.getItems().addAll(addItem,removeItem);
                
                //Le splitpane qui séparera la table et le canvas.
                SplitPane split = new SplitPane();
                split.getItems().addAll(buttonPane,canvas);
		root.setCenter(split);
                root.setTop(menuBar);
                	
		stage.setTitle("grapher");
		stage.setScene(new Scene(root));
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
