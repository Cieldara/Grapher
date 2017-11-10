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
 * 
 * Handler utilisé pour les boutons Ajouter et Supprimer.
 */
public class FunctionButton extends Button{
    
    public FunctionButton(String text,ListView<Function>list){
        super(text);
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Si le texte du bouton est "+"
                if(text.equals("+")){
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setContentText("Entrez une nouvelle expression :");
                    dialog.setTitle("Expression");
                    dialog.setHeaderText("Expression");
                    Optional<String> result = dialog.showAndWait();
                    //Si l'utilisateur a entré une nouvelle fonction dans le champ prévu à cet effet : l'ajouter dans la table avec le ColorPicker associé
                    if(result.isPresent()){
                        Function f = FunctionFactory.createFunction(dialog.getResult());
                         list.getItems().add(f);
                    }   
                }
                //Sinon si le texte du bouton est "-"
                else{
                    int itemSelected = list.getSelectionModel().getSelectedIndex();
                    //Si un élément est sélectionné : le retirer de la liste.
                    if(itemSelected >= 0)
                        list.getItems().remove(itemSelected);
                }
            }
        });
    }
}
