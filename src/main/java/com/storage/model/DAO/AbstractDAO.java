package com.storage.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.storage.exceptions.InvalidUpdate;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDAO {

    protected static final int INVALID_UPDATE = 0;

    protected Connection connection;
/*

    public AbstractDAO() {
        try {
            this.connection = DataBaseConnection.getInstance().getConnection();
        } catch (DatabaseException databaseException) {
            databaseException.printStackTrace();
        }
    }
*/


    public ArrayList<String> getListOfString(String query, int number ) {
        ArrayList<String> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (number > 0){
                preparedStatement.setInt(1, number);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    void update(String statement, String name) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, name);
            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new InvalidUpdate("Invalid update");
        } catch (SQLException | InvalidUpdate e) {
            e.printStackTrace();
        }
    }

    int selectNumberByString(String statement, String name) {
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    String selectStringByString(String statement, String name) {
        PreparedStatement preparedStatement;
        String result = "";
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    int selectNumberByNumber(String statement, int number) {
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    void setIntAndExecute(int number, String query){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void setIntAndStringAndExecute(int number, String text, String query){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, number);
            preparedStatement.setString(2, text);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void setStringAndExecute(String string, String query){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, string);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     String selectStringByNumber(String query, int id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    ResultSet getResultSetByNumber(String query, int number) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
