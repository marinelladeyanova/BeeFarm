package com.storage.model.DAO;

import static com.storage.model.DAO.Queries.CITY_ADD;
import static com.storage.model.DAO.Queries.CITY_AND_COUNTRY_NAMES;
import static com.storage.model.DAO.Queries.CITY_DELETE;
import static com.storage.model.DAO.Queries.CITY_GET_BY_NAME;
import static com.storage.model.DAO.Queries.CITY_GET_COUNTRY_NAME;
import static com.storage.model.DAO.Queries.CITY_GET_NAMES;
import static com.storage.model.DAO.Queries.COUNTRY_DELETE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityDAO extends AbstractDAO {

    @Autowired
    CountryDAO countryDAO;

    public CityDAO() {
    }

    public int getCityId(String name) {
        return selectNumberByString(CITY_GET_BY_NAME, name);
    }

    public ArrayList<String> getAllCityNames() {
        return getListOfString(CITY_GET_NAMES, -1);
    }

    public LinkedHashMap<String, String> getCityAndCountryNames() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CITY_AND_COUNTRY_NAMES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getString("city_name"), resultSet.getString("country_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }


    public void addNewCity(String city, String country) {
        int countryId = countryDAO.getCountryIdByName(country);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CITY_ADD);
            preparedStatement.setString(1, city);
            preparedStatement.setInt(2, countryId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getCountryNameByCityName(String cityName) {
        return selectStringByString(CITY_GET_COUNTRY_NAME, cityName);
    }

    public void removeCity(String cityName) {
        String countryName = getCountryNameByCityName(cityName);
        setStringAndExecute(cityName, CITY_DELETE);
        setStringAndExecute(countryName, COUNTRY_DELETE);
    }


}



