package com.odaviml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

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
    private ChoiceBox<String> grupoSelect;;

    @FXML
    private Spinner<Integer> entradaID;

    private String[] tps = {"Celular", "Comercial", "Casa"};
    private String[] grps = {"Família", "Trabalho", "Escola"};
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tipoID.setValue("Celular");
        tipoID.getItems().addAll(tps);
        grupoSelect.setValue("Família");
        grupoSelect.getItems().addAll(grps);
        Integer max = DAO.getCodigo();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, max);
        entradaID.setValueFactory(valueFactory);
    }
    @FXML
    private void confirmaBTN() throws IOException  {
        Integer id = entradaID.getValue();
        String nome = nomeID.getText();
        String nmr = numeroID.getText();
        Alert a = new Alert(AlertType.NONE);
        try {
            Long numero = Long.parseLong(nmr);
            String tipo = tipoID.getValue();
            String email = emailID.getText();
            String rua = ruaID.getText();
            String bairro = bairroID.getText();
            String grupo = grupoSelect.getValue();
            
            if (nome.isEmpty() || numero == null) {
                a.setAlertType(AlertType.WARNING);
                a.setContentText("Campos Nome/Número/ID não podem estar vazio");
                a.show();
            }
            else if (DAO.consultaPorID(id) == null) {
                a.setAlertType(AlertType.WARNING);
                a.setContentText("ID não cadastrado");
                a.show();
                limpaInputs();
            }
            else if (DAO.consultaPorNome(nome) != null) {
                a.setAlertType(AlertType.WARNING);
                a.setContentText("Contato ja cadastrado");
                a.show();
                limpaInputs();
            }
            else {
                Service.editarContato(id, nome, numero, tipo, email, rua, bairro, grupo);
                Stage stage = (Stage) confirmaBotao.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Número não pode conter letras");
            a.show();
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
