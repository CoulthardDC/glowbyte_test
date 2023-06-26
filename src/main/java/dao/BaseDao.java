package dao;

import exceptions.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
    private Connection connection;

    public  BaseDao(Connection connection) {
        this.connection = connection;
    }

    public void createTableList(String tableName) {
        String request = "CREATE TABLE IF NOT EXISTS `%s` (\n" +
                "  `TABLE_NAME` VARCHAR(32),\n" +
                "  `PK`         VARCHAR(256) NOT NULL,\n" +
                "  PRIMARY KEY(`TABLE_NAME`, `PK`)\n" +
                ");";
        execSQL(String.format(request, tableName));
    }

    public void createTableCol(String tableName) {
        String request = "CREATE TABLE IF NOT EXISTS `%s` (\n" +
                "  `TABLE_NAME` VARCHAR(32),\n" +
                "  `COLUMN_NAME`         VARCHAR(32) NOT NULL,\n" +
                "  `COLUMN_TYPE`         VARCHAR(32) NOT NULL,\n" +
                "  PRIMARY KEY(`TABLE_NAME`, `COLUMN_NAME`, `COLUMN_TYPE`)\n" +
                ");";
        execSQL(String.format(request, tableName));
    }


    private boolean execSQL(String sql) {
        boolean result = false;
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                statement.execute(sql);
                statement.close();
                statement = null;
                result = true;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return result;
    }
}
