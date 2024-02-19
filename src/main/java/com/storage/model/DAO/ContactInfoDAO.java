package com.storage.model.DAO;

import static com.storage.model.DAO.Queries.CONTACT_INFO_BY_ID;
import static com.storage.model.DAO.Queries.CONTACT_INFO_EDIT;
import static com.storage.model.DAO.Queries.CONTACT_INFO_ID_BY_EMPLOYEE_ID;
import static com.storage.model.DAO.Queries.CONTACT_INFO_TO_EMPLOYEE_ADD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.storage.model.pojos.ContactInfo;
import com.storage.model.pojos.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactInfoDAO extends AbstractDAO {

    @Autowired
    CityDAO cityDAO;
    @Autowired
    CountryDAO countryDAO;

    public ContactInfoDAO() {
    }

    public int addContactInfo(Employee employee) {
        return preparedStatementForContactInfo(CONTACT_INFO_TO_EMPLOYEE_ADD, employee);
    }

    public int editContactInfo(Employee employee) {
        return preparedStatementForContactInfo(CONTACT_INFO_EDIT, employee);
    }

    int preparedStatementForContactInfo(String query, Employee employee) {
        ContactInfo contactInfo = employee.getContactInfo();
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int cityId = cityDAO.getCityId(contactInfo.getCity());
            preparedStatement.setString(1, contactInfo.getPhoneNumber());
            preparedStatement.setInt(2, cityId);
            preparedStatement.setString(3, contactInfo.getStreet());
            preparedStatement.setInt(4, contactInfo.getStreetNumber());
            preparedStatement.setInt(5, employee.getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public int getContactInfoIdByEmployeeId(int employeeId) {
        return selectNumberByNumber(CONTACT_INFO_ID_BY_EMPLOYEE_ID, employeeId);
    }

    public int findOrCreateContactInfo(Employee employee) {
        int contactInfoId = getContactInfoIdByEmployeeId(employee.getId());
        if (contactInfoId != 0) {
            return contactInfoId;
        } else {
            return addContactInfo(employee);
        }
    }

    public ContactInfo getContactInfoById(int id) {
        ContactInfo contactInfo = null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(CONTACT_INFO_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                contactInfo = new ContactInfo(
                        resultSet.getInt("id"),                                    //   id
                        resultSet.getString("city_name"),                          //  city
                        resultSet.getString("street"),                             //   street
                        resultSet.getInt("street_number"),                         //   streetNumber
                        resultSet.getString("country_name"),                       //   country
                        resultSet.getString("phone_number")                        //   phoneNumber
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactInfo;
    }

    public ContactInfo createContactInfoFromResultSet(ResultSet resultSet) {

        ContactInfo contactInfo = null;
        try {
            contactInfo = new ContactInfo(
                    resultSet.getInt("id"),
                    resultSet.getString("city_name"),
                    resultSet.getString("street"),
                    resultSet.getInt("street_number"),
                    resultSet.getString("country_name"),
                    resultSet.getString("phone_number")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactInfo;
    }

}