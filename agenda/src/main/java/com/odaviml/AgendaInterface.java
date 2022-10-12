package com.odaviml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.odaviml.tipos.TipoDAO;
import com.odaviml.grupos.GrupoDAO;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @FXML
    private MenuItem grupoBotao;

    @FXML
    private MenuItem tipoBotao;

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

    private String[] buscarSelStrings = {"ID", "Nome", "Número"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        buscarSelect.setValue("ID");
        buscarSelect.getItems().addAll(buscarSelStrings);
        carregarTabela();
    }

    Alert a = new Alert(AlertType.NONE);

    @FXML
    private void cadastrarBTN() throws IOException {
        try {
            String nome = nomeID.getText();
            String nmr = numeroID.getText();
            String tipo = tipoSelect.getValue();
            String email = emailID.getText();
            String rua = ruaID.getText();
            String bairro = bairroID.getText();
            String grupo = grupoSelect.getValue();

            Integer numero = Integer.parseInt(nmr);
            
            if (nome.isEmpty() || numero==null) {
                a.setAlertType(AlertType.WARNING);
                a.setContentText("Campos Nome/Número não podem estar vazio");
                a.show();
            }
            else if (DAO.consultaPorNome(nome) != null) {
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
        } catch (Exception e) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Número não pode conter letras");
            a.show();
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
        a.setAlertType(AlertType.CONFIRMATION);
        a.setContentText("Contato editado, modificação será exibida\nao cadastrar/remover novo contato");
        a.showAndWait();
        carregarTabela();
    }

    Integer codigo;
    public Integer getRow() {
        DTO dto = contatosTabela.getSelectionModel().getSelectedItem();
        return codigo = dto.getCodigo();
    }

    @FXML
    private void buscarBTN() throws IOException {
        try {
        String buscaInput = buscarInput.getText();
        String buscaSelect = buscarSelect.getValue();
        DTO consulta;
        if (buscaInput.isEmpty()) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Campo não pode estar vazio");
            a.show();
        }
        else {
            switch (buscaSelect) {
                case "ID":
                        Integer id = Integer.parseInt(buscaInput);
                        consulta = Service.consultaPorID(id);
                        if (consulta == null) {
                            a.setAlertType(AlertType.WARNING);
                            a.setContentText("ID não encontrado");
                            a.show();
                        }
                        else {
                            a.setAlertType(AlertType.INFORMATION);
                            a.setContentText(textoConsulta(consulta));
                            a.show();
                        }
                    break;
                case "Nome":
                    consulta = Service.consultaPorNome(buscaInput);
                    if (consulta == null) {
                        a.setAlertType(AlertType.WARNING);
                        a.setContentText("Nome não encontrado");
                        a.show();
                    }
                    else {
                        a.setAlertType(AlertType.INFORMATION);
                        a.setContentText(textoConsulta(consulta));
                        a.show();
                    }
                    break;
                case "Número":
                    try {
                        Integer telefone = Integer.parseInt(buscaInput);
                        consulta = Service.consultaPorTelefone(telefone);
                        if (consulta == null) {
                            a.setAlertType(AlertType.WARNING);
                            a.setContentText("Telefone não encontrado");
                            a.show();
                        }
                        else {
                            a.setAlertType(AlertType.INFORMATION);
                            a.setContentText(textoConsulta(consulta));
                            a.show();
                        }
                    } catch (Exception e) {
                        a.setAlertType(AlertType.WARNING);
                        a.setContentText("Telefone deve ser um número");
                        a.show();
                    }
                    break;
                }
            }
            
        }catch (Exception e) {
            a.setAlertType(AlertType.WARNING);
            a.setContentText("ID deve ser um número");
            a.show();
        }
    }

    @FXML
    private void interfaceTipo() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(AgendaContatos.class.getResource("tipo.fxml"));
        stage.getIcons().add(new Image(AgendaContatos.class.getResourceAsStream("contato.png")));
        stage.setScene(new Scene(root));
        stage.setTitle("Tipo");
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        tipoSelect.getItems().remove(TipoDAO.getTipoLista());
        tipoSelect.getItems().addAll(TipoDAO.getTipoLista());
        tipoSelect.setValue(TipoDAO.getPrimeiroItem());
    }

    @FXML
    private void interfaceGrupo() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(AgendaContatos.class.getResource("grupo.fxml"));
        stage.getIcons().add(new Image(AgendaContatos.class.getResourceAsStream("contato.png")));
        stage.setScene(new Scene(root));
        stage.setTitle("Grupo");
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

        grupoSelect.getItems().remove(GrupoDAO.getGrupoLista());
        grupoSelect.getItems().addAll(GrupoDAO.getGrupoLista());
        grupoSelect.setValue(GrupoDAO.getPrimeiroItem());
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

    private String textoConsulta (DTO consulta) {
        String nome, tipo, grupo, email;
        Integer ID, numero;
        ID = consulta.getCodigo();
        nome = consulta.getNome();
        numero = consulta.getTelefone();
        email = consulta.getEmail();
        tipo = consulta.getTipo();
        grupo = consulta.getGrupo();
        String textoconsultaString = "ID: " + ID + "\nNome: " + nome + "\nNúmero: " + numero + "\nEmail: " + email+ "\nTipo: " + tipo + "\nGrupo: " + grupo;
        return textoconsultaString;
    }

    public void limpaInputs(){
        nomeID.setText("");
        numeroID.setText("");
        emailID.setText("");
        ruaID.setText("");
        bairroID.setText("");
    }
}
