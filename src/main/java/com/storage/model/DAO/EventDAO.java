package com.storage.model.DAO;

import static com.storage.model.DAO.Queries.EVENT_ADD_TAG;
import static com.storage.model.DAO.Queries.EVENT_DELETE;
import static com.storage.model.DAO.Queries.EVENT_DELETE_LOCATION;
import static com.storage.model.DAO.Queries.EVENT_GET_LOCATION_ID;
import static com.storage.model.DAO.Queries.EVENT_GET_SUGGESTED_TAGS;
import static com.storage.model.DAO.Queries.EVENT_GET_TAGS;
import static com.storage.model.DAO.Queries.EVENT_REMOVE_TAG;
import static com.storage.model.DAO.Queries.EVENT_SELECT_ALL;
import static com.storage.model.DAO.Queries.EVENT_SELECT_BY_ID;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.storage.model.pojos.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventDAO extends AbstractDAO {

    @Autowired
    ContactInfoDAO contactInfoDAO;

    public EventDAO() {
    }

    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();
        Event event = new Event();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(EVENT_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("event_id") != event.getId()) {
                    if (event.getId() > 0) {
                        events.add(event);
                    }
                    event = createEventFromResultSet(resultSet);
                    event.setContactInfo(contactInfoDAO.createContactInfoFromResultSet(resultSet));
                }
                event.addTag(resultSet.getString("tag"));
            }
            events.add(event);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    private Event createEventFromResultSet(ResultSet resultSet) throws SQLException {
        return new Event(
                resultSet.getInt("event_id"),
                resultSet.getString("name"),
                resultSet.getDate("date").toLocalDate(),
                resultSet.getString("status"),
                resultSet.getString("details"),
                resultSet.getString("location")
        );
    }

    public void deleteEvent(int id) {
        int location_id = getLocationId(id);
        setIntAndExecute(id, EVENT_DELETE);
        deleteLocation(location_id);
    }

    private int getLocationId(int id) {
        return selectNumberByNumber(EVENT_GET_LOCATION_ID, id);
    }

    private void deleteLocation(int id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(EVENT_DELETE_LOCATION);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Event getEventById(int id) {
        Event event = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(EVENT_SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                event = createEventFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    public ArrayList<String> getEventTags(int id) {
        return  getListOfString(EVENT_GET_TAGS, id);
    }

    public ArrayList<String> getSuggestedTags(int id){
        return  getListOfString(EVENT_GET_SUGGESTED_TAGS, id);
    }

    public void removeTagFromEvent(String tagName, int eventId) {
        setIntAndStringAndExecute(eventId, tagName, EVENT_REMOVE_TAG);
    }

    public void addTagToEvent(String tagName, int eventId) {
        setIntAndStringAndExecute(eventId, tagName, EVENT_ADD_TAG);
    }



/*
    public void addEmployee(Employee employee) {
        PreparedStatement preparedStatement;
        try {
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

    private void prepareStatementForEmployee(Employee employee, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setDate(3, valueOf(employee.getBirthday()));
        preparedStatement.setDouble(4, employee.getSalary());
    }

    public Employee getEmployeeById(int id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(EMPLOYEE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createEmployeeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

*/

}
