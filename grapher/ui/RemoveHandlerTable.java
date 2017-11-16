/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

/**
 *
 * @author GONTARD Benjamin
 */
public class RemoveHandlerTable implements EventHandler<ActionEvent> {

    TableView<ColorFunction> table;

    public RemoveHandlerTable(TableView<ColorFunction> table) {
        super();
        this.table = table;
    }

    @Override
    public void handle(ActionEvent event) {

        ObservableList<ColorFunction> list = table.getSelectionModel().getSelectedItems();
        ArrayList<Integer> intList = new ArrayList<>();
        table.getItems().removeAll(list);
    }
}
