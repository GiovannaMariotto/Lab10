/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void select(ActionEvent event) {
    	River r;
    	List<Flow> flows = null;
    	if(this.boxRiver.getValue()!=null) {
    		r = this.boxRiver.getValue();
    		 flows = new ArrayList<>(model.getFlows(r));
    		 this.txtEndDate.setText(flows.get(flows.size()-1).getDay().toString()); 
    		 this.txtStartDate.setText(flows.get(0).getDay().toString());
    		 this.txtFMed.setText(""+model.getMedia(flows));
    		 this.txtNumMeasurements.setText(""+flows.size());
    		 
    	}
    	
    	
    }
    @FXML
    void doSimula(ActionEvent event) {
    	this.txtResult.clear();
    	River r =this.boxRiver.getValue();
    	List<String> result;
    	Double k ;
    	try {
    		k = Double.parseDouble(this.txtK.getText());
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		return ;
    	}
    	
    	if(k==null||r==null) {
    		this.txtResult.setText("ERRORE NELL'INPUT");
    		return;
    	}
    	this.txtResult.appendText("Fiume scelto: "+r.getName().toUpperCase()+"\n\n");;
    	List<Flow> flows = new ArrayList<>(model.getFlows(r));
    	Double fmedio = model.getMedia(flows);
    	
    	result = new LinkedList(model.simula(k,fmedio,r));
    	
    	for(String s : result) {
    		if(s!=null) {
    			this.txtResult.appendText(s+"\n");
    		}
    	}
    	
    	
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxRiver.getItems().addAll(model.loadRivers());
    }
}
