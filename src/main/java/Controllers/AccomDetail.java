package Controllers;

import entities.Accommodation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AccomDetail {
    @FXML
    public Text title;
    @FXML
    public Text description;
    @FXML
    public ImageView image;

    public void setData(Accommodation data) {
        // Use the data parameter as needed
        title.setText(data.getTitle());
        description.setText(data.getType());
        image.setImage(new Image("file:///"+data.getImage()));
    }
    @FXML
    void go_accomList(ActionEvent event) {try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherAccom.fxml"));
        Parent root = loader.load();

        // Obtenir le contrôleur de Notificationcontroller
        AjouterAccommodationController ajouterAccommodationController = loader.getController();

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


}
