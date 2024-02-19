package com.storage.model.DAO;


import static com.storage.model.DAO.Queries.STATISTICS_BIRTHDAY_COUNT_BETWEEN_VALUES;
import static com.storage.model.DAO.Queries.STATISTICS_BIRTHDAY_DIAGRAM_LABELS;
import static com.storage.model.DAO.Queries.STATISTICS_CC_CITIES;
import static com.storage.model.DAO.Queries.STATISTICS_CC_CITIES_MAX;
import static com.storage.model.DAO.Queries.STATISTICS_CC_COUNTRIES;
import static com.storage.model.DAO.Queries.STATISTICS_CC_COUNTRIES_MAX;
import static com.storage.model.DAO.Queries.STATISTICS_CC_CREATE_VIEW;
import static com.storage.model.DAO.Queries.STATISTICS_CC_DROP_VIEW;
import static com.storage.model.DAO.Queries.STATISTICS_DAYS_OF_BIRTH;
import static com.storage.model.DAO.Queries.STATISTICS_EMPLOYEES_BORN_NEXT_MONTH;
import static com.storage.model.DAO.Queries.STATISTICS_EMPLOYEES_BORN_NEXT_WEEK;
import static com.storage.model.DAO.Queries.STATISTICS_MAX_COUNT_DAYS_BIRTH;
import static com.storage.model.DAO.Queries.STATISTICS_MAX_COUNT_MONTHS_BIRTH;
import static com.storage.model.DAO.Queries.STATISTICS_MONTHS_OF_BIRTH;
import static com.storage.model.DAO.Queries.STATISTICS_MONTHS_OF_BIRTH_MALE_FEMALE;
import static com.storage.model.DAO.Queries.STATISTICS_MONTHS_OF_BIRTH_MALE_FEMALE_MAX;
import static com.storage.model.DAO.Queries.STATISTICS_SALARIES_COUNT_BETWEEN_VALUES;
import static com.storage.model.DAO.Queries.STATISTICS_SALARIES_SELECT_DIAGRAM_LABELS;
import static com.storage.model.DAO.Queries.STATISTICS_YEARS_OLD_MAX;
import static com.storage.model.DAO.Queries.STATISTICS_YEARS_OLD_MIN;
import static com.storage.model.DAO.Queries.STATISTICS_YEARS_OLD_VALUES;
import static java.sql.Date.valueOf;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;


@Component
public class StatisticsDAO extends AbstractDAO {

    public StatisticsDAO() {

    }

    public void createCountriesAndCitiesView() {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(STATISTICS_CC_CREATE_VIEW);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> getCitiesCount() {
        return execute(STATISTICS_CC_CITIES, "city_name", "city_count");
    }

    public HashMap<String, Integer> getCountriesCount() {
        return execute(STATISTICS_CC_COUNTRIES, "country_name", "country_count");
    }

    public int getCitiesMaxCount() {
        return getMaxCount(STATISTICS_CC_CITIES_MAX);
    }

    public int getCountriesMaxCount() {
        return getMaxCount(STATISTICS_CC_COUNTRIES_MAX);
    }

    private int getMaxCount(String statisticsCcCountriesMax) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statisticsCcCountriesMax);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("max") + 2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void dropCountriesAndCitiesView() {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(STATISTICS_CC_DROP_VIEW);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Integer> execute(String statisticsCcCountries, String name, String count) {
        HashMap<String, Integer> map = new HashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statisticsCcCountries);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getString(name), resultSet.getInt(count));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public LinkedHashMap<String, Integer> getSalaries() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        ArrayList<Integer> values = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(STATISTICS_SALARIES_SELECT_DIAGRAM_LABELS);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                for (int i = 1; i <= 7; i++) {
                    values.add(resultSet.getInt(i));
                }
            }
            for (int i = 0; i < values.size() - 1; i++) {
                PreparedStatement statement = connection.prepareStatement(STATISTICS_SALARIES_COUNT_BETWEEN_VALUES);
                statement.setInt(1, values.get(i));
                statement.setInt(2, values.get(i + 1));
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    map.put(String.format("%d - %d", values.get(i), values.get(i + 1)), result.getInt("count"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }


    public int getSalariesMax() {
        LinkedHashMap<String, Integer> map = getSalaries();
        int max = 0;
        for (Integer value : map.values()) {
            if (max < value) max = value;
        }
        return max + 2;
    }


    public LinkedHashMap<String, Integer> getBirthdays() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        ArrayList<Integer> values = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(STATISTICS_BIRTHDAY_DIAGRAM_LABELS);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                for (int i = 1; i <= 7; i++) {
                    values.add(resultSet.getInt(i));
                }
            }
            for (int i = 0; i < values.size() - 1; i++) {
                PreparedStatement statement = connection.prepareStatement(STATISTICS_BIRTHDAY_COUNT_BETWEEN_VALUES);
                statement.setDate(1, valueOf(LocalDate.of(values.get(i), 1, 1)));
                statement.setDate(2, valueOf(LocalDate.of(values.get(i + 1), 12, 31)));
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    map.put(String.format("%d - %d", values.get(i), values.get(i + 1)), result.getInt("count"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public int getBirthdaysMax() {
        LinkedHashMap<String, Integer> map = getBirthdays();
        int max = 0;
        for (Integer value : map.values()) {
            if (max < value) max = value;
        }
        return max + 2;
    }

    public int getYearsOldMax() {
        return getMaxCount(STATISTICS_YEARS_OLD_MAX);
    }

    public int getYearsOldMin() {
        return getMaxCount(STATISTICS_YEARS_OLD_MIN);
    }

    public ArrayList<Integer> getYearsOld() {
        ArrayList<Integer> values = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(STATISTICS_YEARS_OLD_VALUES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                values.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }

    public LinkedHashMap<String, LinkedHashMap<String, Integer>> getCountOfBirthMonthsFemaleVsMale() {
        LinkedHashMap<String, LinkedHashMap<String, Integer>> map = new LinkedHashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(STATISTICS_MONTHS_OF_BIRTH_MALE_FEMALE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String month = resultSet.getString("month");
                String gender = resultSet.getString("gender");
                int count = resultSet.getInt("count");
                if (!map.keySet().contains(month)) {
                    map.put(resultSet.getString("month"), new LinkedHashMap<>());
                }
                LinkedHashMap<String, Integer> genderAndOCunt = map.get(month);
                genderAndOCunt.put(gender, count);
                map.put(month, genderAndOCunt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public int getCountOfBirthMonthsFemaleVsMaleMax() {
        return getMaxCount(STATISTICS_MONTHS_OF_BIRTH_MALE_FEMALE_MAX);
    }

    public LinkedHashMap<String, Integer> getCountOfBirthMonths() {
        return countOf(STATISTICS_MONTHS_OF_BIRTH);
    }

    public LinkedHashMap<String, Integer> getCountOfBirthDays() {
        return countOf(STATISTICS_DAYS_OF_BIRTH);
    }

    private LinkedHashMap<String, Integer> countOf(String statisticsDaysOfBirth) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statisticsDaysOfBirth);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getString("date"), resultSet.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }


    public int getMaxCountOfBirthMonths() {
        return getMaxCount(STATISTICS_MAX_COUNT_MONTHS_BIRTH);
    }

    public LinkedHashMap<String, LocalDate> getEmployeesBornNextMonth() {
        return getEmployeeNameAndData(STATISTICS_EMPLOYEES_BORN_NEXT_MONTH);
    }

    public LinkedHashMap<String, LocalDate> getEmployeesBornNextWeek() {
        return getEmployeeNameAndData(STATISTICS_EMPLOYEES_BORN_NEXT_WEEK);
    }

    private LinkedHashMap<String, LocalDate> getEmployeeNameAndData(String statisticsEmployeesBornNextWeek) {
        LinkedHashMap<String, LocalDate> map = new LinkedHashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statisticsEmployeesBornNextWeek);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getString("name"), resultSet.getDate("birthday").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }


    public int getMaxCountOfBirthDays() {
        return getMaxCount(STATISTICS_MAX_COUNT_DAYS_BIRTH);
    }
}

