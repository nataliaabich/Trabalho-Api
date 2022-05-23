package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controlador implements Initializable {
    @FXML
    TextArea textArea;
    @FXML
    TextField textField;
    @FXML
    Button consultar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void evento_botao(ActionEvent event) {
        if(!this.textField.getText().equals("")) {
            try {
                AcessoAPI acesso = new AcessoAPI(this.textField.getText());
                this.textArea.setText(acesso.getConteudoInterface());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
