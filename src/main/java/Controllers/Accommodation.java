package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class Accommodation {

    @FXML
    Button add_button;

    @FXML
    TextField text_field;

    @FXML
    public void initialize() {
        add_button.setOnAction(e -> {
            text_field.setText("ADD NEW ACCOMMODATION");
        });
    }
}
