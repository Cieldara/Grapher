/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import grapher.fc.Function;
import javafx.scene.control.ColorPicker;

/**
 *
 * @author GONTARD Benjamin
 *
 * Notre TableView stockera des éléments de type ColorFunction. Deux colonnes :
 * -Une pour le ColorPicker -L'autre pour la fonction
 */
public class ColorFunction {

    public ColorPicker color;
    public Function function;

    public ColorFunction(Function f, ColorPicker c) {
        this.function = f;
        this.color = c;
    }

    public ColorPicker getColor() {
        return color;
    }

    public void setColor(ColorPicker color) {
        this.color = color;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

}
