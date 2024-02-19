package com.storage.model.DAO;

import static com.storage.model.DAO.Queries.ADD_MANAGER;
import static com.storage.model.DAO.Queries.EMPLOYEES_SHORT_INFO;
import static com.storage.model.DAO.Queries.MANAGER_GET_ALL;
import static com.storage.model.DAO.Queries.REMOVE_MANAGER;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.storage.model.pojos.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagersDAO extends AbstractDAO {

    @Autowired
    EmployeeDAO employeeDAO;
    @Autowired
    ContactInfoDAO contactInfoDAO;

    public ManagersDAO() {
    }

    public ArrayList<Employee> getAllManagersAndEmployees() {
        ArrayList<Employee> managers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(MANAGER_GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                managers.add(createManagerFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managers;
    }

    private Employee createManagerFromResultSet(ResultSet resultSet) {
        Employee e = null;
        try {
            e = new Employee(
                    resultSet.getInt("m_id"),
                    resultSet.getString("m_name"),
                    resultSet.getInt("e_id"),
                    resultSet.getString("e_name")
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public void removeManagerTo(int id) {
        setIntAndExecute(id, REMOVE_MANAGER);
    }

    public void addNewManager(Employee manager) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_MANAGER);
            preparedStatement.setInt(1, manager.getId());
            preparedStatement.setInt(2, manager.getEmployeeId());
            preparedStatement.setInt(3, manager.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> selectEmpShortInfo() {
        ArrayList<Employee> employeesShortInfo = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(EMPLOYEES_SHORT_INFO);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeesShortInfo.add(
                        new Employee(
                                resultSet.getInt("id"),
                                resultSet.getString("info")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeesShortInfo;
    }

}
