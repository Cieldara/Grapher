/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import grapher.fc.Function;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

/**
 *
 * @author GONTARD Benjamin
 */
public class RemoveHandler implements EventHandler<ActionEvent> {
    ListView<Function>list;
    public RemoveHandler(ListView<Function>list){
        super();
        this.list = list;
    }
    
     @Override
     public void handle(ActionEvent event) {
        int itemSelected = list.getSelectionModel().getSelectedIndex();
        if(itemSelected >= 0)
            list.getItems().remove(itemSelected);  
     }
}
