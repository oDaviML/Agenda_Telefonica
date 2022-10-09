package com.odaviml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EditarInterface implements Initializable{
    @FXML
    private TextField bairroID;

    @FXML
    private Button confirmaBotao;

    @FXML
    private TextField emailID;

    @FXML
    private TextField nomeID;

    @FXML
    private TextField numeroID;

    @FXML
    private TextField ruaID;

    @FXML
    private ChoiceBox<String> tipoID;

    @FXML
    private TextField entradaID;

    private String[] tps = {"Celular", "Comercial", "Casa"};
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tipoID.setValue("Celular");
        tipoID.getItems().addAll(tps);
    }

    @FXML
    private void confirmaBTN() throws IOException  {
        String idString = entradaID.getText();
        String nome = nomeID.getText();
        String numero = numeroID.getText();
        String tipo = tipoID.getValue();
        String email = emailID.getText();
        String rua = ruaID.getText();
        String bairro = bairroID.getText();
        Alert a = new Alert(AlertType.NONE);
        
        if (nome.isEmpty() || numero.isEmpty() || idString.isEmpty()) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Campos Nome/Número/ID não podem estar vazio");
            a.show();
        }
        else if (idString.substring(0).matches("[A-Z]*") || idString.substring(0).matches("[a-z]*")) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Campo ID deve ser um número");
            a.show();
            limpaInputs();
        }
        Integer id = Integer.valueOf(idString);
        if (DAO.consultaPorID(id) == null) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("ID não cadastrado");
            a.show();
        }
        else {
            Service.editarContato(id, nome, numero, tipo, email, rua, bairro);
        }
    }

    public void limpaInputs(){
        nomeID.setText("");
        numeroID.setText("");
        emailID.setText("");
        ruaID.setText("");
        bairroID.setText("");
    }

}
