/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afpa.dal;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author 80010-89-09
 */
public class GUIController implements Initializable {

    @FXML
    private TableView<Client> list_client;
    @FXML
    private TableColumn<Client, String> col_nom;
    @FXML
    private TableColumn<Client, String> col_prenom;
    @FXML
    private TableColumn<Client, String> col_id;
    //load model
    ObservableList<Client> model = FXCollections.observableArrayList();
    @FXML
    private VBox VBOX;
    @FXML
    private Pane pane_ajout;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_ville;

    @FXML
    private Pane pane_detail;
    @FXML
    private TextField txt_nom1;
    @FXML
    private TextField txt_prenom1;
    @FXML
    private TextField txt_ville1;
    @FXML
    private Label Error_modif;
    @FXML
    private Label Error_ajout;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ClientDAO dao = new ClientDAO();
        model.addAll(dao.List());

        // Jonction du tableau avec les données
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));

        // On indique au TableView quelle modèle observer pour trouver les données(vue)
        list_client.setItems(model);

    }
//ajout

    @FXML
    private void click_add(ActionEvent event) { //clic sur button ajout
        pane_ajout.setVisible(true);//afficher le formulaire ajout
        pane_detail.setVisible(false);
    }

    @FXML
    private void OK_Insert(ActionEvent event) {
        ClientDAO ajout = new ClientDAO();
        Client c = new Client();
        boolean isOk = true;
        Pattern r_text = Pattern.compile("^[^@+=|><\\]\\[{}]+$");

        String nom = this.txt_nom.getText();
        String prenom = this.txt_prenom.getText();
        String ville = this.txt_ville.getText();
        if (r_text.matcher(nom).matches()) {
            c.setNom(txt_nom.getText());// assigner les valeurs nom saisi au c a inserer 
            txt_nom.setStyle("");

        } else {
            txt_nom.setStyle("-fx-border-color :red");
            Error_ajout.setVisible(true);
            isOk = false;

        }
        if (r_text.matcher(prenom).matches()) {
            c.setNom(txt_prenom.getText());// assigner les valeurs nom saisi au c a inserer 
            txt_prenom.setStyle("");

        } else {
            txt_prenom.setStyle("-fx-border-color :red");
            Error_ajout.setVisible(true);
            isOk = false;

        }
        if (r_text.matcher(ville).matches()) {
            c.setNom(txt_ville.getText());// assigner les valeurs nom saisi au c a inserer 
            txt_ville.setStyle("");

        } else {
            txt_ville.setStyle("-fx-border-color :red");
            Error_ajout.setVisible(true);
            isOk = false;

        }
        if (isOk) { // si regex accepté
            Error_ajout.setVisible(false);
            c.setNom(txt_nom.getText());// assigner les valeurs nom saisi au c a inserer 
            c.setPrenom(txt_prenom.getText());
            c.setVille(txt_ville.getText());
            ajout.Insert(c);//appel de la methose insert de la class clientDAO
            model.add(c);//ajout dans le tableau vu (model)
            
        }
    }
//modif

    @FXML
    private void click_update(ActionEvent event) {
        pane_detail.setVisible(true);//afficher le formulaire detail
        pane_ajout.setVisible(false);
        int idx = list_client.getSelectionModel().getSelectedItem().getId(); //on recupere l'id et on stock
        txt_nom1.setText(list_client.getSelectionModel().getSelectedItem().getNom());
        txt_prenom1.setText(list_client.getSelectionModel().getSelectedItem().getPrenom());
        txt_ville1.setText(list_client.getSelectionModel().getSelectedItem().getVille());

        /*
        int idx = list_client.getSelectionModel().getSelectedItem().Id; //on recupere l'id et on stock
        ClientDAO affiche = new ClientDAO();
        Client cli = affiche.Find(idx);
        affiche.Find(idx);//on passe l id en argu pour la supp
        txt_ville1.setText(cli.getVille());
        txt_nom1.setText(cli.getNom());
        txt_prenom1.setText(cli.getPrenom());*/
    }
//1 ere façon

    @FXML
    private void OK_Update(ActionEvent event) {

        boolean isOk = true;
        ClientDAO modif = new ClientDAO();
        Client c = list_client.getSelectionModel().getSelectedItem();

        //text a comparer regex
        Pattern r_text = Pattern.compile("^[^@+=|><\\]\\[{}]+$");

        String nom = this.txt_nom1.getText();
        String prenom = this.txt_prenom1.getText();
        String ville = this.txt_ville1.getText();
        if (r_text.matcher(nom).matches()) {
            c.setNom(txt_nom1.getText());// assigner les valeurs nom saisi au c a inserer 
            txt_nom1.setStyle("");

        } else {
            txt_nom1.setStyle("-fx-border-color :red");
            Error_modif.setVisible(true);
            isOk = false;

        }

        if (r_text.matcher(prenom).matches()) {
            c.setPrenom(txt_prenom1.getText());
            txt_prenom1.setStyle("");

        } else {
            txt_prenom1.setStyle("-fx-border-color :red");
            Error_modif.setVisible(true);
            isOk = false;

        }
        if (r_text.matcher(ville).matches()) {
            c.setVille(txt_ville1.getText());
            txt_ville1.setStyle("");

        } else {
            txt_ville1.setStyle("-fx-border-color :red");
            Error_modif.setVisible(true);
            isOk = false;
        }
        if (isOk) {
            Error_modif.setVisible(false);
            c.setNom(txt_nom1.getText());// assigner les valeurs nom saisi au c a inserer 
            c.setPrenom(txt_prenom1.getText());
            c.setVille(txt_ville1.getText());
            modif.Update(c);
            list_client.refresh();
            pane_detail.setVisible(false);
        }
    }
// 2 facon

    /*
    @FXML
    private void OK_Update(ActionEvent event) {
        boolean isOk = true;
        //text a comparer regex
        Pattern r_text = Pattern.compile("^[^@+=|><\\]\\[{}]+$");
        //
        String nom = this.txt_nom1.getText();
        String prenom = this.txt_prenom1.getText();
        String ville = this.txt_ville1.getText();
        if (!r_text.matcher(nom).matches()) {
            isOk = false;
            txt_nom1.setStyle("-fx-border-color :red");
            Error_modif.setVisible(true);
        } else {
            txt_nom1.setStyle("");

        }

        if (!r_text.matcher(prenom).matches()) {
            isOk = false;
            txt_prenom1.setStyle("-fx-border-color :red");
            Error_modif.setVisible(true);
        } else {
            txt_prenom1.setStyle("");
        }
        if (!r_text.matcher(ville).matches()) {
            isOk = false;
            txt_ville1.setStyle("-fx-border-color :red");
            Error_modif.setVisible(true);
        } else {
            txt_ville1.setStyle("");
        }
        if (true == isOk) {
            Error_modif.setVisible(false);

            ClientDAO modif = new ClientDAO();
//        Client c = new Client();
//        c.Id = list_client.getSelectionModel().getSelectedItem().Id; //on recupere l'id et on stock
            Client c = list_client.getSelectionModel().getSelectedItem();
            c.setNom(txt_nom1.getText());// assigner les valeurs nom saisi au c a inserer 
            c.setPrenom(txt_prenom1.getText());
            c.setVille(txt_ville1.getText());
            modif.Update(c);
            list_client.refresh();
            pane_detail.setVisible(false);
        }
    }


     */
    @FXML
    private void Effacer(ActionEvent event) {
        txt_nom.clear();
        txt_prenom.clear();
        txt_ville.clear();
        txt_nom1.clear();
        txt_prenom1.clear();
        txt_ville1.clear();
    }

    @FXML
    private void delete(ActionEvent event) {
        //supprimer ligne avec remove (getselectionmodel c'est colonne getselectionindex c'est index)
        // supprimer une ligne par son index
        ClientDAO supp = new ClientDAO();
        int idx = list_client.getSelectionModel().getSelectedItem().getId(); //on recupere l'id et on stock
        model.remove(list_client.getSelectionModel().getSelectedIndex());//supp dans la vu
        supp.Delete(idx);//on passe l id en argu pour la supp
    }

    @FXML
    private void Annuler(ActionEvent event) {
        txt_nom.clear();
        txt_prenom.clear();
        txt_ville.clear();
        pane_detail.setVisible(false);
        pane_ajout.setVisible(false);

    }

    private boolean setVisible() {
        throw new UnsupportedOperationException("Not supported yet.");
//To change body of generated methods, choose Tools | Templates.
    }

}
