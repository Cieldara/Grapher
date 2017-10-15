/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

/**
 *
 * @author GONTARD Benjamin
 */
public class RemoveHandlerTable implements EventHandler<ActionEvent> {
    TableView<ColorFunction>table;
    public RemoveHandlerTable(TableView<ColorFunction>table){
        super();
        this.table = table;
    }
    
     @Override
     public void handle(ActionEvent event) {
        int itemSelected = table.getSelectionModel().getSelectedIndex();
        if(itemSelected >= 0)
            table.getItems().remove(itemSelected);  
     }
}
