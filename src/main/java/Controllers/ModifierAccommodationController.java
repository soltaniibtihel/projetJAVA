package Controllers;

import entities.Accommodation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServiceAccommodation;

import java.io.IOException;
import java.sql.SQLException;

public class ModifierAccommodationController {

    @FXML
    private TextField priceTF;

    @FXML
    private TextField titleTF;

    @FXML
    private TextField typeTF;

    @FXML
    private TextField addressTF;

    @FXML
    void ModifierAccom(ActionEvent event) {
        String title = titleTF.getText();
        String type = typeTF.getText();
        String address = addressTF.getText();
        int price = Integer.parseInt(priceTF.getText());
        Accommodation p = new Accommodation(title,address,type,price);
        p.setId(data.getId());
        ServiceAccommodation sp = new ServiceAccommodation();
        try {
            sp.modifier(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Accommodation modifiée avec succéss");
            alert.show();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.show();
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

    private Accommodation data;

    public void setData(Accommodation data) {
        this.data = data;
        // Use the data parameter as needed
        titleTF.setText(data.getTitle());
        typeTF.setText(data.getType());
        addressTF.setText(data.getAddress());
        priceTF.setText(data.getPrice()+"");
    }

}
