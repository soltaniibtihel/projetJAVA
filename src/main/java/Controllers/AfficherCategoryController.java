package Controllers;

import entities.Accommodation;
import entities.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceAccommodation;
import services.ServiceCategory;

import java.sql.SQLException;
import java.util.List;

public class AfficherCategoryController { ServiceCategory sp = new ServiceCategory();
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


    void setData(String param) {
        welcomeLBL.setText("Welcome " + param);
    }

}
