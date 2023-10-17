/*create class: DbManager. The class should be able to execute sql queries using jdbc connection
 - there are two types of execution:
         a) Statement.executeQuery() -> for sql SELECT -> method should return String or List
         b) Statement.executeUpdate() -> for sql UPDATE or INSERT or DELETE query -> method should return Boolean or Integer*/
package com.endava.atf.transition.config.DataBase;
import java.sql.*;
import static io.opentelemetry.semconv.trace.attributes.SemanticAttributes.GraphqlOperationTypeValues.QUERY;

public class DbManager {

    private Connection connection = DbConnection.getInstance(); // открыл сессию к дата бэйс // Open a connection
    private final Statement pstmt = connection.createStatement(); // через Statement делаешь реквесты дл я DB

    public Statement getPstmt() {
        return pstmt;
    }

    public DbManager() throws SQLException {
    }
}

