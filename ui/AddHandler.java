/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import grapher.fc.Function;
import grapher.fc.FunctionFactory;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author GONTARD Benjamin
 */
public class AddHandler implements EventHandler<ActionEvent> {
    ListView<Function>list;
    public AddHandler(ListView<Function>list){
        super();
        this.list = list;
    }
    
     @Override
     public void handle(ActionEvent event) {
          TextInputDialog dialog = new TextInputDialog();
            dialog.setContentText("Entrez une nouvelle expression :");
            dialog.setTitle("Expression");
            dialog.setHeaderText("Expression");
            Optional<String> result = dialog.showAndWait();

            if(result.isPresent()){
                Function f = FunctionFactory.createFunction(dialog.getResult());
                 list.getItems().add(f);
            }   
     }
}
