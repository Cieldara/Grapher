/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

/**
 *
 * @author GONTARD Benjamin
 */
public class ColorPickerHandler implements EventHandler<ActionEvent> {
    TableView<ColorFunction> table;
    GrapherCanvas canvas;

    public ColorPickerHandler(TableView<ColorFunction> table, GrapherCanvas canvas) {
        super();
        this.table = table;
        this.canvas = canvas;
    }
    
    

    @Override
    public void handle(ActionEvent event) {
        ArrayList<ColorFunction> func = new ArrayList<>();
        for(ColorFunction col : table.getItems()){
            func.add(col);
        }
        canvas.initFunctions(func);  
    }  
}
