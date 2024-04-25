package services;

import entities.Accommodation;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceAccommodation implements IService<Accommodation>{
    Connection connection;

    public ServiceAccommodation(){
       connection= MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Accommodation accommodation) throws SQLException {
        if(accommodation.getTitle().isBlank()){
            System.out.println("title cannot be empty");
        }else if(accommodation.getType().isBlank()){
            System.out.println("type cannot be empty");
        }else {
            String req = "insert into accommodation (title,address,type,price) values('" + accommodation.getTitle() + "','" + accommodation.getAddress() + "','" + accommodation.getType() + "'," + accommodation.getPrice() + ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(req);
            System.out.println("accommodation '"+accommodation.getTitle()+"' ajoute");
        }
    }

    @Override
    public void modifier(Accommodation accommodation) throws SQLException {
        String req ="update accommodation set title=?,address=?,price=?,type=? where id=?";

        PreparedStatement preparedStatement= connection.prepareStatement(req);
        preparedStatement.setString(1, accommodation.getTitle());
        preparedStatement.setString(2, accommodation.getAddress());
        preparedStatement.setString(3, accommodation.getType());
        preparedStatement.setInt(4, accommodation.getPrice());
        preparedStatement.setInt(5, accommodation.getId());

        preparedStatement.executeUpdate();


    }

    @Override
    public void supprimer(int id) throws SQLException {

        String req ="delete from accommodation where id="+id;
        Statement statement= connection.createStatement();
        statement.executeUpdate(req);

    }

    @Override
    public List<Accommodation> afficher() throws SQLException {
        List<Accommodation> accommodations = new ArrayList<>();
        String req ="select * from accommodation";
        Statement statement= connection.createStatement();
        ResultSet rs =statement.executeQuery(req);
        while (rs.next()){
            Accommodation accommodation = new Accommodation();
            accommodation.setTitle(rs.getString("title"));
            accommodation.setType(rs.getString("type"));
            accommodation.setAddress(rs.getString(3));
            accommodation.setPrice(rs.getInt("Price"));
            accommodation.setId(rs.getInt("id"));

            accommodations.add(accommodation);

        }
        return accommodations;
    }
}
