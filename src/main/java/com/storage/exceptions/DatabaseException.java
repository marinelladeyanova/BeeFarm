package com.storage.exceptions;

import java.sql.SQLException;

public class DatabaseException extends Exception {

    public DatabaseException(String messege, ClassNotFoundException e) {
    }

    public DatabaseException(String messege, SQLException e) {

    }
}
