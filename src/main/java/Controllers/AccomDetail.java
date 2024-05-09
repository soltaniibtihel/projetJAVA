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
    void go_accomList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherAccom.fxml"));
            Parent root = loader.load();
            AfficherAccommodationController apc = loader.getController();
            title.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }


}
