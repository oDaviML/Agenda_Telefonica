package com.odaviml;

import java.io.IOException;

import com.odaviml.grupos.GrupoDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GrupoInterface {

    @FXML
    private Button atualizarBTN;

    @FXML
    private Button cadastrarBTN;

    @FXML
    private TextField grupoAntigoInput;

    @FXML
    private TextField grupoInput;

    @FXML
    private TextField grupoNovoInput;

    @FXML
    private Button removerBTN;

    Alert a = new Alert(AlertType.NONE);

    @FXML
    private void cadastraGrupo() throws IOException {
        String nome = grupoInput.getText();
        
        if (nome.isEmpty()) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Campo não pode estar vazio");
            a.show();
        }
        else if (GrupoDAO.consultaPorNome(nome) != null) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Grupo já cadastrado");
            a.show();
            limpaInputs();
        }
        else {
            Service.adicionarGrupo(nome);
            limpaInputs();
            Stage stage = (Stage) cadastrarBTN.getScene().getWindow();
            a.setAlertType(AlertType.CONFIRMATION);
            a.setContentText("Grupo cadastrado");
            a.showAndWait();
            stage.close();
        }
    }

    @FXML
    private void removeGrupo() throws IOException {

        String nome = grupoAntigoInput.getText();
        if (nome.isEmpty()) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Campo não pode estar vazio");
            a.show();
        }
        else if (GrupoDAO.consultaPorNome(nome) == null) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Grupo não existe");
            a.show();
            limpaInputs();
        }
        else {
            Service.removerGrupo(nome);
            Stage stage = (Stage) removerBTN.getScene().getWindow();
            a.setAlertType(AlertType.CONFIRMATION);
            a.setContentText("Grupo removido");
            a.showAndWait();
            stage.close();
        }
    }

    @FXML
    private void atualizaGrupo() throws IOException {
        String nomeAntigo = grupoAntigoInput.getText();
        String nomeNovo = grupoNovoInput.getText();
        
        if (nomeAntigo.isEmpty()||nomeNovo.isEmpty()) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Campos não podem estar vazios");
            a.show();
        }
        else if (GrupoDAO.consultaPorNome(nomeAntigo) == null) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Grupo não foi cadastrado");
            a.show();
            limpaInputs();
        }
        else {
            Service.editarTipo(nomeAntigo, nomeNovo);
            limpaInputs();
            Stage stage = (Stage) cadastrarBTN.getScene().getWindow();
            a.setAlertType(AlertType.CONFIRMATION);
            a.setContentText("Grupo Atualizado");
            a.showAndWait();
            stage.close();
        }
    }
    
    public void limpaInputs(){
        grupoInput.setText("");
        grupoAntigoInput.setText("");
        grupoNovoInput.setText("");
    }

}
