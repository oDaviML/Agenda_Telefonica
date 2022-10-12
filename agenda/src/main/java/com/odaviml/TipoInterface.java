package com.odaviml;

import java.io.IOException;

import com.odaviml.tipos.TipoDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TipoInterface {

    @FXML
    private TextField TipoNovoInput;

    @FXML
    private Button cadastrarBTN;

    @FXML
    private Button removerBTN;

    @FXML
    private TextField tipoAntigoInput;

    @FXML
    private TextField tipoInput;

    Alert a = new Alert(AlertType.NONE);

    @FXML
    private void cadastraTipo() throws IOException {
        String nome = tipoInput.getText();
        
        if (nome.isEmpty()) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Campo não pode estar vazio");
            a.show();
        }
        else if (TipoDAO.consultaPorNome(nome) != null) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Tipo já cadastrado");
            a.show();
            limpaInputs();
        }
        else {
            Service.adicionarTipo(nome);
            limpaInputs();
            Stage stage = (Stage) cadastrarBTN.getScene().getWindow();
            a.setAlertType(AlertType.CONFIRMATION);
            a.setContentText("Tipo cadastrado");
            a.showAndWait();
            stage.close();
        }
    }

    @FXML
    private void removeTipo() throws IOException {

        String nome = tipoAntigoInput.getText();
        if (nome.isEmpty()) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Campo não pode estar vazio");
            a.show();
        }
        else if (TipoDAO.consultaPorNome(nome) == null) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Tipo não existe");
            a.show();
            limpaInputs();
        }
        else {
            Service.removerTipo(nome);
            Stage stage = (Stage) removerBTN.getScene().getWindow();
            a.setAlertType(AlertType.CONFIRMATION);
            a.setContentText("Tipo removido");
            a.showAndWait();
            stage.close();
        }
    }

    @FXML
    private void atualizaTipo() throws IOException {
        String nomeAntigo = tipoAntigoInput.getText();
        String nomeNovo = TipoNovoInput.getText();
        
        if (nomeAntigo.isEmpty()||nomeNovo.isEmpty()) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Campos não podem estar vazios");
            a.show();
        }
        else if (TipoDAO.consultaPorNome(nomeAntigo) == null) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Tipo não foi cadastrado");
            a.show();
            limpaInputs();
        }
        else {
            Service.editarTipo(nomeAntigo, nomeNovo);
            limpaInputs();
            Stage stage = (Stage) cadastrarBTN.getScene().getWindow();
            a.setAlertType(AlertType.CONFIRMATION);
            a.setContentText("Tipo Atualizado");
            a.showAndWait();
            stage.close();
        }
    }
    
    public void limpaInputs(){
        tipoInput.setText("");
        tipoAntigoInput.setText("");
        TipoNovoInput.setText("");
    }

}
