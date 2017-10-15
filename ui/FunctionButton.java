/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import grapher.fc.Function;
import grapher.fc.FunctionFactory;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author GONTARD Benjamin
 */
public class FunctionButton extends Button{
    
    public FunctionButton(String text,ListView<Function>list){
        super(text);
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(text.equals("+")){
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
                else{
                    int itemSelected = list.getSelectionModel().getSelectedIndex();
                    if(itemSelected >= 0)
                        list.getItems().remove(itemSelected);
                }
            }
        });
    }
}
