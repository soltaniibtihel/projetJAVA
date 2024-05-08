package Controllers;

import entities.Accommodation;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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

}
