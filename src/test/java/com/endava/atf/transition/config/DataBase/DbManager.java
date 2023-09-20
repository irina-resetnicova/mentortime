/*create class: DbManager. The class should be able to execute sql queries using jdbc connection
 - there are two types of execution:
         a) Statement.executeQuery() -> for sql SELECT -> method should return String or List
         b) Statement.executeUpdate() -> for sql UPDATE or INSERT or DELETE query -> method should return Boolean or Integer*/

package com.endava.atf.transition.config.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {

    private Connection getDriver(){
       return DbConnection.getInstance();
    }

    public List executeQuery(String query, String... params){
        try {
            Statement stmt = getDriver().createStatement();
            ResultSet rset = stmt.executeQuery(query);

            ResultSetMetaData meta = rset.getMetaData();
            int columns = meta.getColumnCount();

            List rows  = new ArrayList();
            while (rset.next()) {

                String [] currentRow = new String[columns];
                for(int i=1; i<columns; i++){
                    currentRow[i-1] = rset.getString(i);
                }
                rows.add(currentRow);

            }
            return rows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
