package com.odaviml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AgendaInterface implements Initializable {

    @FXML
    private Button botaoCadastrar;

    @FXML
    private TextField nomeID;

    @FXML
    private TextField numeroID;

    private String[] tps = {"Celular", "Comercial", "Casa"};

    @FXML
    private ChoiceBox<String> tipoSelect;;

    @FXML
    private void cadastrarBTN() throws IOException {
        String nome = nomeID.getText();
        String numero = numeroID.getText();
        String tipo = tipoSelect.getValue();
        System.out.println(nome + " " + numero + " " + tipo);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tipoSelect.getItems().addAll(tps);
    }


}
