package com.odaviml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private List<DTO> listClientes = new ArrayList();
    private ObservableList<DTO> observableListClientes;

    //
    //////////////////////////////////////////////////////////////////

    //Botão cadastrar
    @FXML
    private void cadastrarBTN() throws IOException {
        String nome = nomeID.getText();
        String numero = numeroID.getText();
        String tipo = tipoSelect.getValue();
        Service.AdicionarContato(nome, numero, tipo);
        carregarTabela(nome, numero, tipo);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tipoSelect.getItems().addAll(tps);
    }

    public void carregarTabela(String nome, String numero, String tipo) {

        chaveTabela.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nomeTabela.setCellValueFactory(new PropertyValueFactory<>("nome"));
        numeroTabela.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tipoTabela.setCellValueFactory(new PropertyValueFactory<>("tipo"));


        contatosTabela.setItems(DAO.getObservableListClientes());
        System.out.println("Adicionado a Tabela");
    }

}
