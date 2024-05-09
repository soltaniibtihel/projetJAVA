package Controllers;

import entities.Accommodation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServiceAccommodation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherAccommodationController {
    ServiceAccommodation sp = new ServiceAccommodation();
    @FXML
    private TableColumn<Accommodation, Integer> priceCol;
    @FXML
    private TableColumn<Accommodation, String> titleCol;
    @FXML
    private TableColumn<Accommodation, String> typeCol;
    @FXML
    private TableColumn<Accommodation, String> addressCol;
    @FXML
    private TableView<Accommodation> tableView;
    @FXML
    private Label welcomeLBL;

    @FXML
    private TextField filterField;
    ObservableList<Accommodation> observableList;
    @FXML
    void initialize() {

        try {
            List<Accommodation> accommodationList = sp.afficher();
             observableList = FXCollections.observableList(accommodationList);

            tableView.setItems(observableList);

            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                handleFilterAction(newValue);
            });


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void delete(ActionEvent event) {
        try {
            Accommodation p = tableView.getSelectionModel().getSelectedItem();
            sp.supprimer(p.getId());
            observableList.remove(p);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void update(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierAccom.fxml"));
            Parent root = loader.load();
            ModifierAccommodationController apc = loader.getController();
            Accommodation p = tableView.getSelectionModel().getSelectedItem();
            apc.setData(p);
            welcomeLBL.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void viewAccom(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccomDetail.fxml"));
            Parent root = loader.load();
            AccomDetail apc = loader.getController();
            Accommodation p = tableView.getSelectionModel().getSelectedItem();
            apc.setData(p);
            welcomeLBL.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void handleFilterAction(String filterValue) {
        // Créer une liste temporaire pour stocker les guides filtrés
        ObservableList<Accommodation> filteredList = FXCollections.observableArrayList();

        // Parcourir la liste observableList existante et ajouter les guides qui correspondent au critère de filtrage dans la liste temporaire
        for (Accommodation accommodation : observableList) {
            if (accommodation.getTitle().toLowerCase().contains(filterValue.toLowerCase())
                    ||accommodation.getType().toLowerCase().contains(filterValue.toLowerCase())
                    ||accommodation.getAddress().toLowerCase().contains(filterValue.toLowerCase())) {
                filteredList.add(accommodation);
            }
        }

        // Mettre à jour les données de la TableView avec la liste filtrée
        tableView.setItems(filteredList);
    }

    void setData(String param) {
        welcomeLBL.setText("Accommodation List " + param);
    }

    @FXML
    public void go_Categ(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCategory.fxml"));
            Parent root = loader.load();
            tableView.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void add_accom(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterAccom.fxml"));
            Parent root = loader.load();
            tableView.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
