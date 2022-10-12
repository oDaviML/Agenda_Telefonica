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
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
    private TextField buscarInput;

    @FXML
    private ChoiceBox<String> buscarSelect;

    @FXML
    private ChoiceBox<String> tipoSelect;;

    @FXML
    private ChoiceBox<String> grupoSelect;
    //////////////////////////////////////////////////////////////////
    //Botões

    @FXML
    private Menu botaoF5;

    @FXML
    private Button removerBotao;
    
    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button editarBotao;

    @FXML
    private Button buscarBTN;

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

    @FXML
    private TableColumn<DTO, String> grupoTabela;
    //
    //////////////////////////////////////////////////////////////////

    private String[] tps = {"Celular", "Comercial", "Casa"};
    private String[] grps = {"Família", "Trabalho", "Escola"};
    private String[] buscarSelStrings = {"ID", "Nome", "Número"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tipoSelect.setValue("Celular");
        tipoSelect.getItems().addAll(tps);

        grupoSelect.setValue("Família");
        grupoSelect.getItems().addAll(grps);

        buscarSelect.setValue("ID");
        buscarSelect.getItems().addAll(buscarSelStrings);

        carregarTabela();
    }

    Alert a = new Alert(AlertType.NONE);

    @FXML
    private void cadastrarBTN() throws IOException {
        String nome = nomeID.getText();
        String numero = numeroID.getText();
        String tipo = tipoSelect.getValue();
        String email = emailID.getText();
        String rua = ruaID.getText();
        String bairro = bairroID.getText();
        String grupo = grupoSelect.getValue();
        
        
        if (nome.isEmpty() || numero.isEmpty()) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Campos Nome/Número não podem estar vazio");
            a.show();
        }
        else if (DAO.consultarPorNome(nome) != null) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Contato ja cadastrado");
            a.show();
            limpaInputs();
        }
        else {
            Service.adicionarContato(nome, numero, tipo, email, rua, bairro, grupo);
            carregarTabela();
            limpaInputs();
        }
    }
    
    @FXML
    private void removerBTN() throws IOException {
        try {
            Service.removerContato(getRow());
            carregarTabela();
        } catch (Exception e) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Nenhum contato selecionado");
            a.show();
        }
    }

    @FXML
    private void editarBTN() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(AgendaContatos.class.getResource("editar.fxml"));
        stage.getIcons().add(new Image(AgendaContatos.class.getResourceAsStream("contato.png")));
        stage.setScene(new Scene(root));
        stage.setTitle("Editar");
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }

    Integer codigo;
    public Integer getRow() {
        DTO dto = contatosTabela.getSelectionModel().getSelectedItem();
        return codigo = dto.getCodigo();
    }

    @FXML
    private void buscarBTN() throws IOException {
        String buscaInput = buscarInput.getText();
        String buscaSelect = buscarSelect.getValue();

        switch (buscaSelect) {
            case "ID":
                
                break;
            case "Nome":
                
                break;
            case "Número":
            
                break;
        }
        a.setAlertType(AlertType.CONFIRMATION);
        a.setContentText("Campos Nome/Número não podem estar vazio " + buscaInput + " " + buscaSelect);
        a.show();
    }

    public void carregarTabela() {
        chaveTabela.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nomeTabela.setCellValueFactory(new PropertyValueFactory<>("nome"));
        numeroTabela.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tipoTabela.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        emailTabela.setCellValueFactory(new PropertyValueFactory<>("email"));
        ruaTabela.setCellValueFactory(new PropertyValueFactory<>("rua"));
        bairroTabela.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        grupoTabela.setCellValueFactory(new PropertyValueFactory<>("grupo"));

        contatosTabela.setItems(DAO.getObservableListClientes());
    }

    public void limpaInputs(){
        nomeID.setText("");
        numeroID.setText("");
        emailID.setText("");
        ruaID.setText("");
        bairroID.setText("");
    }
    
}
