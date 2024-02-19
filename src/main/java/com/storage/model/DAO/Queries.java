package com.storage.model.DAO;

import org.springframework.stereotype.Component;

@Component
public class Queries {

    /* COUNTRY */

    static final String COUNTRY_ADD =
            "           INSERT INTO country(name) VALUES (?);                                                               ";
    static final String COUNTRY_DELETE =
            "           DELETE FROM country WHERE id NOT IN ( SELECT country_id FROM city) AND name = ?                     ";
    static final String COUNTRY_GET_BY_NAME =
            "           SELECT id FROM country WHERE name = ?                                                               ";
    static final String COUNTRY_SELECT_ALL =
            "           SELECT name FROM country;                                                                           ";
    static final String COUNTRY_GET_BY_ID =
            "           SELECT name FROM country WHERE id = ?                                                               ";

    /* CITY */

    static final String CITY_GET_BY_NAME = "SELECT id FROM city WHERE name = ?";
    static final String CITY_GET_NAMES = "SELECT name FROM city";
    static final String CITY_ADD = "INSERT INTO city(name, country_id) VALUES (?, ?);";
    static final String CITY_GET_BY_ID = "SELECT name FROM city WHERE id = ?";
    static final String CITY_DELETE =
            "           DELETE FROM city WHERE id not in ( select city_id FROM contact_info ) AND name = ?";
    static final String CITY_GET_COUNTRY_NAME =
            "           select name from country                                                                     " +
                    "   where id = (select country_id from city where name = ?)                                     ";
    static final String CITY_AND_COUNTRY_NAMES =
            "           SELECT c.name as city_name, cy.name as country_name FROM city c JOIN country cy                 " +
                    "   ON c.country_id = cy.id                                                                         " +
                    "   ORDER BY cy.name ASC;                                                                           ";

    /* EMPLOYEE */

    static final String EMPLOYEE_ADD =
            "           INSERT INTO employee(first_name, last_name, birthday, salary, gender) VALUES (?, ?, ?, ?, ?);";
    static final String EMPLOYEE_SELECT_ALL =
            "           SELECT e.*, ci.*, c.name as city_name, cy.name as country_name FROM employee e 	                " +
                    "   JOIN contact_info ci ON e.id = ci.employee_id                					                " +
                    "   JOIN city c ON ci.city_id = c.id                                                                " +
                    "   JOIN country cy ON c.country_id = cy.id                                                         " +
                    "   order by e.id;                                                                                    ";
    static final String EMPLOYEE_BY_ID =
            "           SELECT e.*, ci.*, c.name as city_name, cy.name as country_name FROM employee e                   " +
                    "   JOIN contact_info ci ON e.id = ci.employee_id                                                    " +
                    "   JOIN city c ON ci.city_id = c.id                                                                 " +
                    "   JOIN country cy ON c.country_id = cy.id                                                          " +
                    "   WHERE e.id = ?                                                                                   ";
    static final String EMPLOYEE_GET_CONTACT_INFO_ID =
            "           SELECT contact_info_id FROM employee WHERE id = ?;";
    static final String EMPLOYEE_EDIT =
            "           UPDATE employee SET first_name = ?, last_name = ?, birthday = ?, salary = ? WHERE id = ?;       ";
    static final String EMPLOYEE_DELETE = "" +
            "           DELETE FROM employee WHERE id = ?;                                                              ";

    /* EVENTS */


    static final String EVENT_SELECT_ALL =
            "                select e.id as event_id, e.name, e.date, s.name as status,                                 " +
                    "        if (e.details, e.details, '-') as details,                                                 " +
                    "        if (l.name, l.name, '-') as location ,                                                     " +
                    "       ci.id, ci.street, ci.street_number,                                                         " +
                    "       ci.phone_number, c.name as city_name, cy.name as country_name,                              " +
                    "       UPPER(t.name) as tag                                                                        " +
                    "       from event e                                                                                " +
                    "       left join status s on e.status_id = s.id                                                    " +
                    "       left join location l on e.location_id = l.id                                                " +
                    "       left join contact_info ci on l.id = ci.location_id                                          " +
                    "       left join city c on ci.city_id = c.id                                                       " +
                    "       left join  country cy on c.country_id = cy.id                                               " +
                    "       inner join event_tag et on e.id = et.event_id                                               " +
                    "       join tag t on et.tag_id = t.id                                                              " +
                    "       order by e.id asc ;                                                                          ";

    static final String EVENT_SELECT_BY_ID =
            "           select e.id as event_id, e.name, e.date, s.name as status,            " +
                    "   if (e.details, e.details, '-') as details,                            " +
                    "   if (l.name, l.name, '-') as location                                  " +
                    "   from event e                                                          " +
                    "   left join status s on e.status_id = s.id                              " +
                    "   left join location l on e.location_id = l.id                          " +
                    "   where e.id = ? ;                                                                                         ";

    static final String EVENT_DELETE =
            "           DELETE FROM event WHERE id = ?;                                                                 ";
    static final String EVENT_GET_LOCATION_ID =
            "           SELECT location_id FROM event WHERE id = ?;                                                    ";

    static final String EVENT_DELETE_LOCATION =
            "           DELETE FROM location WHERE id = ? AND                                                           " +
                    "   (SELECT COUNT(*) FROM event WHERE location_id = ?) = 0 ;                                        ";

    static final String EVENT_GET_TAGS =
            "           select UPPER(t.name)                                                               " +
                    "   from event e                                                                      " +
                    "   inner join event_tag et on e.id = et.event_id                                     " +
                    "   join tag t on et.tag_id = t.id                                                    " +
                    "   where e.id = ?;                                                                   ";

    static final String EVENT_GET_SUGGESTED_TAGS =
            "           select UPPER(name) from tag t where id not in (                                 "   +
                    "   select tag_id from event_tag where event_id = ?)                                                ";

    static final String EVENT_REMOVE_TAG =
                "    DELETE FROM event_tag WHERE event_id = ? and tag_id = (select id from tag where name = ?);   ";

    static final String EVENT_ADD_TAG =
            "  INSERT INTO event_tag(event_id, tag_id) VALUES (?, (select id from tag where name = lower(?)) ); ";

    /* CONTACT_INFO */

    static final String CONTACT_INFO_ID_BY_EMPLOYEE_ID =
            "           SELECT id FROM contact_info WHERE employee_id = ?";
    static final String CONTACT_INFO_BY_ID =
            "           SELECT * FROM contact_info ci JOIN city c ON ci.city_id = c.id JOIN country cy ON c.country_id = cy.id WHERE ci.id = ? ";
    static final String CONTACT_INFO_TO_EMPLOYEE_ADD =
            "           INSERT INTO contact_info(phone_number, city_id, street, street_number, employee_id) VALUES (?, ?, ?, ?, ?);";
    static final String CONTACT_INFO_EDIT =
            "           UPDATE contact_info SET phone_number = ?, city_id = ?, street = ?, street_number = ? WHERE employee_id = ?;";


    /* EMPLOYEE STATISTICS - COUNTRY AND CITY */

    static final String STATISTICS_CC_CREATE_VIEW =
            "           CREATE VIEW cities_and_countries AS                                                              " +
                    "   SELECT e.id, c.name as city_name, cy.name as country_name FROM employee e                        " +
                    "   JOIN contact_info ci ON e.id = ci.employee_id                                                    " +
                    "   JOIN city c ON ci.city_id = c.id                                                                 " +
                    "   JOIN country cy ON c.country_id = cy.id;                                                         ";

    static final String STATISTICS_CC_CITIES =
            "           SELECT city_name, count(city_name) AS city_count " +
                    "   FROM cities_and_countries " +
                    "   GROUP BY city_name; ";

    static final String STATISTICS_CC_CITIES_MAX =
            "           SELECT MAX(city_count) AS max FROM ( " +
                    "   SELECT city_name, count(city_name) AS city_count " +
                    "   FROM cities_and_countries " +
                    "   GROUP BY city_name) c; ";

    static final String STATISTICS_CC_COUNTRIES =
            "           SELECT country_name, count(country_name) AS country_count " +
                    "   FROM cities_and_countries " +
                    "   GROUP BY country_name; ";

    static final String STATISTICS_CC_COUNTRIES_MAX =
            "           SELECT MAX(country_count) AS max FROM ( " +
                    "   SELECT country_name, count(country_name) AS country_count " +
                    "   FROM cities_and_countries " +
                    "   GROUP BY country_name) c; ";
    static final String STATISTICS_CC_DROP_VIEW =
            "           DROP VIEW cities_and_countries";

    /* EMPLOYEE STATISTICS - SALARIES */

    static final String STATISTICS_SALARIES_SELECT_DIAGRAM_LABELS =
            "           SELECT                                                                   " +
                    "          Cast(min AS UNSIGNED)                        AS min         ,   " +
                    "          Cast(( av - min ) / 3 + min AS UNSIGNED)     AS av_minus1   ,   " +
                    "          Cast(2 * ( av - min ) / 3 + min AS UNSIGNED) AS av_minus    ,   " +
                    "          Cast(av AS UNSIGNED)                         AS av          ,   " +
                    "          Cast(( max - av ) / 3 + av AS UNSIGNED)      AS av_plus     ,   " +
                    "          Cast(2 * ( max - av ) / 3 + av AS UNSIGNED)  AS av_plus1    ,   " +
                    "          Cast(max AS UNSIGNED)                        AS max             " +
                    "   FROM   (SELECT Max(salary) max,                                        " +
                    "                  Avg(salary) av,                                         " +
                    "                  Min(salary) min                                         " +
                    "           FROM   employee)calculations;                                  ";

    static final String STATISTICS_SALARIES_COUNT_BETWEEN_VALUES =
            "           SELECT Count(*) AS count FROM employee WHERE salary BETWEEN ? AND ? ";


    /* EMPLOYEE STATISTICS - BIRTHDAY */

    static final String STATISTICS_BIRTHDAY_DIAGRAM_LABELS =
            "           SELECT min,  " +
                    "       Cast(( middle - min ) / 3 + min AS UNSIGNED)        AS av_minus1,  " +
                    "       Cast(2 * ( middle - min ) / 3 + min AS UNSIGNED)    AS av_minus,  " +
                    "       middle,  " +
                    "       Cast(( max - middle ) / 3 + middle AS UNSIGNED)     AS av_plus,  " +
                    "       Cast(2 * ( max - middle ) / 3 + middle AS UNSIGNED) AS av_plus1,  " +
                    "       max  " +
                    "   FROM  (SELECT max,  " +
                    "              Cast(( max + min ) / 2 AS UNSIGNED) AS middle,  " +
                    "              min  " +
                    "       FROM   (SELECT Substring(Max(birthday), 1, 4) AS max,  " +
                    "                      Substring(Min(birthday), 1, 4) AS min  " +
                    "               FROM   employee) dates) calculations ;";
    static final String STATISTICS_BIRTHDAY_COUNT_BETWEEN_VALUES =
            "           select count(*) as count from employee where birthday between ? and ?;";


    static final String STATISTICS_YEARS_OLD_VALUES =
            "           SELECT  TIMESTAMPDIFF(YEAR,birthday,CURDATE()) AS age FROM employee order by age asc ;";


    static final String STATISTICS_YEARS_OLD_MAX =
            "           SELECT  max(TIMESTAMPDIFF(YEAR,birthday,CURDATE())) as max FROM employee; ";
    static final String STATISTICS_YEARS_OLD_MIN =
            "           SELECT  min(TIMESTAMPDIFF(YEAR,birthday,CURDATE())) - 4 as max FROM employee; ";


    static final String STATISTICS_MONTHS_OF_BIRTH =
            "           SELECT MONTHNAME(birthday) as date, count(*) as count           " +
                    "   FROM employee                                                   " +
                    "   group by MONTH(birthday)                                        " +
                    "   order by  MONTH(birthday) ;                                     ";

    static final String STATISTICS_MONTHS_OF_BIRTH_MALE_FEMALE =
            "            SELECT count(*) AS count, if(gender, 'female', 'male') AS gender, MONTHNAME(birthday) as month       " +
                    "    FROM employee                                                                                       " +
                    "    group by month , gender                                                                             " +
                    "     order by MONTH(birthday)                                                                           ";
    static final String STATISTICS_MONTHS_OF_BIRTH_MALE_FEMALE_MAX =
            "           select max(count) as max from (                                 " +
                    "   SELECT count(*) as count                                        " +
                    "   FROM employee                                                   " +
                    "   group by  MONTHNAME(birthday) , gender ) counts                     ";


    static final String STATISTICS_DAYS_OF_BIRTH =
            "           SELECT DATE_FORMAT(birthday, '%W') as date  , count(*) as count                    " +
                    "   from employee                                                                " +
                    "   group by date                                                                " +
                    "   order by  dayofweek(birthday) ;                                             ";


    static final String STATISTICS_MAX_COUNT_MONTHS_BIRTH =
            "           select max(count) as max from (  SELECT count(*) as count                        " +
                    "   FROM employee group by MONTH(birthday) ) counts                                  ";
    static final String STATISTICS_MAX_COUNT_DAYS_BIRTH =
            "           select max(count) as max from (                               " +
                    "   SELECT count(*) as count                                                   " +
                    "   from employee                                                       " +
                    "   group by DATE_FORMAT(birthday, '%W') )days                                             ";


    static final String STATISTICS_EMPLOYEES_BORN_NEXT_MONTH =
            "           SELECT concat(first_name, ' ', last_name) as name, birthday FROM employee           " +
                    "       WHERE MONTH(birthday) = MONTH(DATE_ADD(CURDATE(),INTERVAL 1 MONTH));            ";


    static final String STATISTICS_EMPLOYEES_BORN_NEXT_WEEK =
            "           SELECT concat(first_name, ' ', last_name) as name, birthday FROM employee        " +
                    "   WHERE WEEKOFYEAR(birthday) = WEEKOFYEAR(DATE_ADD(CURRENT_DATE(),INTERVAL 1 week));  ";


    static final String count_employees_birth_by_month_and_by_gender =
            "           SELECT count(*), gender, MONTH(birthday)  as month                                      " +
                    "   FROM employee                                                                           " +
                    "   group by month , gender                                                                 " +
                    "   order by  month                                                                       ";


    /*  MANAGERS   */

    static final String MANAGER_GET_ALL =
            "           select m.id as m_id, CONCAT_WS( ' ', m.first_name,m.last_name) as m_name," +
                    "   e.id as e_id,  CONCAT_WS( ' ',  e.first_name, e.last_name) as e_name " +
                    "   from employee e join employee m  on e.manager = m.id " +
                    "   order by m.id                   ";
    static final String REMOVE_MANAGER = " UPDATE employee SET manager = null where id = ? ";

    static final String ADD_MANAGER =
            "           update employee set manager = ? where id = ? and id <> ?;";

    static final String EMPLOYEES_SHORT_INFO =
            "            select id , CONCAT_WS( ' ', id, first_name, last_name) as info from employee e         ";



}


    /*
    http://www.dpriver.com/pp/sqlformat.htm
    */
