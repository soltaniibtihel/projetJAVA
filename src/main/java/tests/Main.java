package tests;

import entities.Accommodation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ServiceAccommodation;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Accommodation accommodation = new Accommodation("","Ain Drahem","house", 100);
        Accommodation accommodation2 = new Accommodation("Rihana","Ain Drahem","hotel", 120);


        ServiceAccommodation serviceAccommodation = new ServiceAccommodation();
        try{
            serviceAccommodation.ajouter(accommodation);
            serviceAccommodation.ajouter(accommodation2);
            serviceAccommodation.supprimer(1);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }


        try {
            System.out.println(serviceAccommodation.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

}