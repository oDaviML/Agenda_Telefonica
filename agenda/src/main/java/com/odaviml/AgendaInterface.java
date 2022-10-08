package com.odaviml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class AgendaInterface implements Initializable {

    //////////////////////////////////////////////////////////////////
    //  INPUTS

    @FXML
    private Button botaoCadastrar;

    @FXML
    private TextField nomeID;

    @FXML
    private TextField numeroID;

    @FXML
    private TextField emailID;

    @FXML
    private TextField bairroID;

    @FXML
    private TextField ruaID;

    private String[] tps = {"Celular", "Comercial", "Casa"};

    @FXML
    private ChoiceBox<String> tipoSelect;;

    //////////////////////////////////////////////////////////////////



    //////////////////////////////////////////////////////////////////
    //  Tabela
    @FXML
    private TableView<DTO> contatosTabela;

    @FXML
    private TableColumn<DTO, Integer> chaveTabela;

    @FXML
    private TableColumn<DTO, String> nomeTabela;

    @FXML
    private TableColumn<DTO, String> numeroTabela;

    @FXML
    private TableColumn<DTO, String> tipoTabela;

    @FXML
    private TableColumn<DTO, String> emailTabela;

    @FXML
    private TableColumn<DTO, String> ruaTabela;

    @FXML
    private TableColumn<DTO, String> bairroTabela;
    //
    //////////////////////////////////////////////////////////////////

    //Botão cadastrar
    @FXML
    private void cadastrarBTN() throws IOException {
        String nome = nomeID.getText();
        String numero = numeroID.getText();
        String tipo = tipoSelect.getValue();
        String email = emailID.getText();
        String rua = ruaID.getText();
        String bairro = bairroID.getText();
        
        if (nome.isEmpty() || numero.isEmpty()) {
            Alert a = new Alert(AlertType.NONE);
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Campo Nome/Número não pode estar vazio");
            a.show();
        }
        else {
            Service.AdicionarContato(nome, numero, tipo, email, rua, bairro);
            carregarTabela();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tipoSelect.setValue("Celular");
        tipoSelect.getItems().addAll(tps);
    }

    public void carregarTabela() {

        chaveTabela.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nomeTabela.setCellValueFactory(new PropertyValueFactory<>("nome"));
        numeroTabela.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tipoTabela.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        emailTabela.setCellValueFactory(new PropertyValueFactory<>("email"));
        ruaTabela.setCellValueFactory(new PropertyValueFactory<>("rua"));
        bairroTabela.setCellValueFactory(new PropertyValueFactory<>("bairro"));

        contatosTabela.setItems(DAO.getObservableListClientes());
    }

}
