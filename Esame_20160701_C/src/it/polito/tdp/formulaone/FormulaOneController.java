package it.polito.tdp.formulaone;


import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.formulaone.model.Constructor;
import it.polito.tdp.formulaone.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FormulaOneController {
	
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Constructor> boxCostruttori;

    @FXML
    private TextField textInputK;

    @FXML
    private TextArea txtResult;

	private Model model;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	Constructor c = boxCostruttori.getValue();
    	if(c!=null) {
    		txtResult.setText(model.creaGrafo(c));
    	}else {
    		showMessage("Errore: Selezione un Costruttore dal menù a tendina");
    	}
    }

    @FXML
    void doTrovaDreamTeam(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxCostruttori != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert textInputK != null : "fx:id=\"textInputK\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'FormulaOne.fxml'.";

    }
    
    public void setModel(Model model){
		this.model = model;
    	boxCostruttori.getItems().addAll(model.getCostruttori());
    }
    
    private void showMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.show();
	}
}
