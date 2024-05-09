package Controllers;

import entities.Accommodation;
import entities.Category;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServiceAccommodation;
import services.ServiceCategory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherCategoryController {
    ServiceCategory sp = new ServiceCategory();

    @FXML
    private TableColumn<entities.Category, Integer> statisticCol;
    @FXML
    private TableColumn<entities.Category, String> categoryNameCol;
    @FXML
    private TableColumn<entities.Category, String> descriptionCol;

    @FXML
    private TableView<entities.Category> tableView;
    @FXML
    private Label welcomeLBL;
    ObservableList<entities.Category> observableList;
    @FXML
    void initialize() {

        try {
            List<entities.Category> categoryList = sp.afficher();
            observableList = FXCollections.observableList(categoryList);

            tableView.setItems(observableList);

            categoryNameCol.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            statisticCol.setCellValueFactory(new PropertyValueFactory<>("statistic"));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void delete(ActionEvent event) {
        try {
            Category p = tableView.getSelectionModel().getSelectedItem();
            sp.supprimer(((Category) p).getId());
            observableList.remove(p);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void showStats(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Statistique.fxml"));
            Parent root = loader.load();
            tableView.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("echec retour main");;
        }

    }
    @FXML
    void go_category(ActionEvent event) {try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCategory.fxml"));
        Parent root = loader.load();

        // Obtenir le contrôleur de Notificationcontroller
        AjouterCategoryController ajouterCategoryController = loader.getController();

        // Appeler la méthode pour initialiser les données avec l'ID de réservations

        // Afficher la vue de Notification.fxml
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Notification");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }

    }


    void setData(String param) {
        welcomeLBL.setText("Category List" + param);
    }

    @FXML
    public void go_accommodations(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherAccom.fxml"));
            Parent root = loader.load();
            tableView.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
