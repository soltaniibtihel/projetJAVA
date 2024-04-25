package Controllers;

import entities.Accommodation;
import entities.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServiceCategory;

import java.io.IOException;
import java.sql.SQLException;
public class AjouterCategoryController {
    @FXML
    private TextField categoryNameTF;

    @FXML
    private TextField descriptionTF;

    @FXML
    private TextField statisticTF;


    @FXML
    void InsererCategory(ActionEvent event) {
        String categoryName = categoryNameTF.getText();
        String description = descriptionTF.getText();
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        if(categoryName.isBlank()){
            error.setContentText("Name cannot be empty");
            error.show();
        }else if(description.isBlank()){
            error.setContentText("Description cannot be empty");
            error.show();
        }else if(statisticTF.getText().isBlank()){
            error.setContentText("Statistic cannot be empty");
            error.show();
        }else{
        int statistic = Integer.parseInt(statisticTF.getText());
        Category p = new Category(categoryName,description,statistic);
        ServiceCategory sp = new ServiceCategory();
        try {
            sp.ajouter(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Category has been inserted successfully");
            alert.show();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.show();
        }}
    }


    @FXML
    void naviguez(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCategory.fxml"));
            Parent root = loader.load();
            AfficherCategoryController apc = loader.getController();
            apc.setData(categoryNameTF.getText());
            statisticTF.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


}
