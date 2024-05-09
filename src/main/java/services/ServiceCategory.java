package services;


import entities.Category;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCategory implements IServiceAcc<Category> {
    Connection connection;

    public ServiceCategory(){
        connection= MyDatabase.getInstance().getConnection();
    }


    @Override
    public void ajouter(Category category) throws SQLException {
        if(category.getCategoryName().isBlank()){
            System.out.println("name cannot be empty");
        }else {
            String req = "insert into category (categoryName,description,statistic) values('" + category.getCategoryName() + "','" + category.getDescription() + "'," + category.getStatistic() + ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(req);
            System.out.println("category '"+category.getCategoryName()+"' ajoute");
        }
    }

        public void modifier(Category category) throws SQLException {
            String req ="update category set categoryName=?,description=?,statistic=? where id=?";

            PreparedStatement preparedStatement= connection.prepareStatement(req);
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setInt(3, category.getStatistic());
            preparedStatement.setInt(4, category.getId());

            preparedStatement.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {

        String req ="delete from category where id="+id;
        Statement statement= connection.createStatement();
        statement.executeUpdate(req);

    }

    @Override
    public List<Category> afficher() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String req ="select * from category";
        Statement statement= connection.createStatement();
        ResultSet rs =statement.executeQuery(req);
        while (rs.next()){
            Category category = new Category();
            category.setCategoryName(rs.getString("categoryName"));
            category.setDescription(rs.getString("description"));
            category.setStatistic(rs.getInt("statistic"));
            category.setId(rs.getInt("id"));

            categories.add(category);

        }
        return categories;
    }
}
