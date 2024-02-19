package com.storage.model.DAO;


import static com.storage.model.DAO.Queries.COUNTRY_ADD;
import static com.storage.model.DAO.Queries.COUNTRY_GET_BY_ID;
import static com.storage.model.DAO.Queries.COUNTRY_GET_BY_NAME;
import static com.storage.model.DAO.Queries.COUNTRY_SELECT_ALL;

import java.util.ArrayList;

import org.springframework.stereotype.Component;


@Component
public class CountryDAO extends AbstractDAO {

    public CountryDAO() {

    }

    public ArrayList<String> getCountryNames() {
        return getListOfString(COUNTRY_SELECT_ALL, -1);
    }

    public String getCountryById(int id) {
        return selectStringByNumber(COUNTRY_GET_BY_ID, id);
    }

    void addNewCountry(String countryName) {
        setStringAndExecute(countryName, COUNTRY_ADD);
    }

    int getCountryIdByName(String country) {
        int id = selectNumberByString(COUNTRY_GET_BY_NAME, country);
        if (id == 0) {
            addNewCountry(country);
            return getCountryIdByName(country);
        }
        return id;
    }

}

