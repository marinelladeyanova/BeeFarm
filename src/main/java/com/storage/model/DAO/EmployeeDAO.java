package com.storage.model.DAO;


import static com.storage.model.DAO.Queries.EMPLOYEE_ADD;
import static com.storage.model.DAO.Queries.EMPLOYEE_BY_ID;
import static com.storage.model.DAO.Queries.EMPLOYEE_DELETE;
import static com.storage.model.DAO.Queries.EMPLOYEE_EDIT;
import static com.storage.model.DAO.Queries.EMPLOYEE_GET_CONTACT_INFO_ID;
import static com.storage.model.DAO.Queries.EMPLOYEE_SELECT_ALL;
import static java.sql.Date.valueOf;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.storage.model.pojos.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDAO extends AbstractDAO {


    @Autowired
    ContactInfoDAO contactInfoDAO;

    public EmployeeDAO() {
    }

    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(EMPLOYEE_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(createEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public void addEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(EMPLOYEE_ADD, Statement.RETURN_GENERATED_KEYS);
            prepareStatementForEmployee(employee, preparedStatement);
            preparedStatement.setBoolean(5, employee.isGender());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                employee.setId(generatedKeys.getInt(1));
            }
            contactInfoDAO.findOrCreateContactInfo(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void prepareStatementForEmployee(Employee employee, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, valueOf(employee.getBirthday()));
            preparedStatement.setDouble(4, employee.getSalary());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeById(int id) {
        return createEmployeeFromResultSet(getResultSetByNumber(EMPLOYEE_BY_ID, id));
    }

    private Employee createEmployeeFromResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return null;
        }
        Employee e = null;
        try {
            e = new Employee(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getDouble("salary"),
                    contactInfoDAO.createContactInfoFromResultSet(resultSet),
                    resultSet.getDate("birthday").toLocalDate(),
                    resultSet.getBoolean("gender")
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public void editEmployee(Employee employee) {
        try {
            contactInfoDAO.editContactInfo(employee);
            PreparedStatement preparedStatement = connection.prepareStatement(EMPLOYEE_EDIT);
            prepareStatementForEmployee(employee, preparedStatement);
            preparedStatement.setInt(5, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getContactInfoIdByEmployeeId(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(EMPLOYEE_GET_CONTACT_INFO_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void removeEmployee(int employeeId) {
        setIntAndExecute(employeeId, EMPLOYEE_DELETE);
    }


}
