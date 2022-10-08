package com.odaviml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AgendaInterface implements Initializable {
    //////////////////////////////////////////////////////////////////
    //  INPUTS
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

    @FXML
    private ChoiceBox<String> tipoSelect;;

    //////////////////////////////////////////////////////////////////
    //Botões

    @FXML
    private Button removerBotao;
    
    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button editarBotao;
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

    private String[] tps = {"Celular", "Comercial", "Casa"};

    @FXML
    private void cadastrarBTN() throws IOException {
        String nome = nomeID.getText();
        String numero = numeroID.getText();
        String tipo = tipoSelect.getValue();
        String email = emailID.getText();
        String rua = ruaID.getText();
        String bairro = bairroID.getText();
        Alert a = new Alert(AlertType.NONE);
        
        if (nome.isEmpty() || numero.isEmpty()) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Campo Nome/Número não pode estar vazio");
            a.show();
        }
        else if (DAO.consultarPorNome(nome) != null) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Contato ja cadastrado");
            a.show();
            limpaInputs();
        }
        else {
            Service.AdicionarContato(nome, numero, tipo, email, rua, bairro);
            carregarTabela();
            limpaInputs();
        }
    }

    Integer codigo;
    @FXML
    private void removerBTN() throws IOException {
        contatosTabela.getItems().removeAll(contatosTabela.getSelectionModel().getSelectedItem());
        Service.removerContato(codigo);
        carregarTabela();
    }

    @FXML
    private void editarBTN() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
        AgendaContatos.class.getResource("editar.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Editar");
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tipoSelect.setValue("Celular");
        tipoSelect.getItems().addAll(tps);
        nomeTabela.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void itemSelecionado(DTO dto) {
        System.out.println(dto.getCodigo());
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
        contatosTabela.setOnMouseClicked(e -> itemSelecionado());
    }

    public void limpaInputs(){
        nomeID.setText("");
        numeroID.setText("");
        emailID.setText("");
        ruaID.setText("");
        bairroID.setText("");
    }
    
}
