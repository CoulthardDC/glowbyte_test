package dao.impl;

import dao.TableColsDao;
import db.DB;
import exceptions.DbException;
import mappers.ColumnMapper;
import model.Column;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableColsDaoImpl implements TableColsDao {

    private final Connection connection;
    private final ColumnMapper columnMapper = new ColumnMapper();

    public TableColsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Column> findPkColumns() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String request = "SELECT * " +
                "FROM table_cols AS tc " +
                "WHERE EXISTS (SELECT * FROM table_list AS tl where tl.TABLE_NAME = tc.TABLE_NAME " +
                "AND UPPER(tl.PK) LIKE CONCAT('%', UPPER(tc.COLUMN_NAME), '%'));";

        List<Column> columns = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(request);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                columns.add(columnMapper.mapToColumn(resultSet));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return columns;
    }

    @Override
    public List<Column> findAll() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String request = "SELECT * FROM table_cols";
        List<Column> columns = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(request);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                columns.add(columnMapper.mapToColumn(resultSet));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return columns;
    }

    @Override
    public void save(Column column) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO TABLE_COLS " +
                            " (TABLE_NAME, COLUMN_NAME, COLUMN_TYPE) " +
                            " VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, column.getTableName());
            preparedStatement.setString(2, column.getColumnName());
            preparedStatement.setString(3, column.getColumnType());
            int rows = preparedStatement.executeUpdate();

            if (rows <= 0) {
                throw new DbException("Неизвестная ошибка. Запись не добавлена");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }
}
