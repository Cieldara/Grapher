/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import grapher.fc.Function;
import grapher.fc.FunctionFactory;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;

import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;

/**
 *
 * @author GONTARD Benjamin
 * 
 * Handler utilisé pour l'ajout d'éléments dans la TableView
 */
public class AddHandlerTable implements EventHandler<ActionEvent> {
    TableView<ColorFunction>table;
    GrapherCanvas canvas;
    public AddHandlerTable(TableView<ColorFunction>table,GrapherCanvas canvas){
        super();
        this.table = table;
        this.canvas = canvas;
    }
    
     @Override
     public void handle(ActionEvent event) {
          TextInputDialog dialog = new TextInputDialog();
            dialog.setContentText("Entrez une nouvelle expression :");
            dialog.setTitle("Expression");
            dialog.setHeaderText("Expression");
            Optional<String> result = dialog.showAndWait();

            
            //Si l'utilisateur a entré une nouvelle fonction dans le champ prévu à cet effet : l'ajouter dans la table avec le ColorPicker associé
            if(result.isPresent()){
                ObservableList<ColorFunction> col = table.getItems();
                Function f = FunctionFactory.createFunction(dialog.getResult());
                ColorPicker color = new ColorPicker(Color.BLACK);
                color.setOnAction(new ColorPickerHandler(table, canvas));
                col.add(new ColorFunction(f,color));
            }   
     }
}
