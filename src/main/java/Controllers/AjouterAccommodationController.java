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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import services.ServiceAccommodation;
import services.ServiceCategory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AjouterAccommodationController {

    @FXML
    public Button btnFileChooser;
    @FXML
    public TextField txtPhoto;
    ServiceCategory sp = new ServiceCategory();
    @FXML
    public ChoiceBox<Category> categories;

    @FXML
    private TextField priceTF;

    @FXML
    private TextField titleTF;

    @FXML
    private TextField typeTF;

    @FXML
    private TextField addressTF;

    @FXML
    void initialize() {

        try {
            List<entities.Category> categoryList = sp.afficher();
            ObservableList<Category> observableList = FXCollections.observableList(categoryList);
            categories.setItems(observableList);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void InsererAccom(ActionEvent event) {
        String title = titleTF.getText();
        String type = typeTF.getText();
        String address = addressTF.getText();
        String link = txtPhoto.getText();
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        if (title.isBlank()) {
            error.setContentText("Title cannot be empty");
            error.show();
        } else if (type.isBlank()) {
            error.setContentText("Type cannot be empty");
            error.show();
        } else if (address.isBlank()) {
            error.setContentText("Address cannot be empty");
            error.show();
        } else if (priceTF.getText().isBlank()) {
            error.setContentText("Price cannot be empty");
            error.show();
        } else {
            int price = Integer.parseInt(priceTF.getText());
            Accommodation p = new Accommodation(title, address, type, price);
            p.setImage(link);
            ServiceAccommodation sp = new ServiceAccommodation();
            try {
                sp.ajouter(p);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Accommodation has been inserted successfully");
                alert.show();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.show();
            }
        }
    }


    @FXML
    void naviguez(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherAccom.fxml"));
            Parent root = loader.load();
            AfficherAccommodationController apc = loader.getController();
            apc.setData(titleTF.getText());
            priceTF.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void handleFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            txtPhoto.setText(file.getAbsolutePath().replace("\\", "\\\\"));
        }
    }

}
