package dao.impl;

import dao.TableListDao;
import db.DB;
import exceptions.DbException;
import mappers.TableMapper;
import model.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableListDaoImpl implements TableListDao {
    private final Connection connection;
    private final TableMapper tableMapper = new TableMapper();

    public TableListDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Table> findAll() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String request = "SELECT * FROM table_list";
        List<Table> tables = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(request);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                tables.add(tableMapper.mapToTable(resultSet));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return tables;
    }

    @Override
    public void save(Table table) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO TABLE_LIST " +
                            " (TABLE_NAME, PK) " +
                            " VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, table.getTableName());
            preparedStatement.setString(2, String.join(", ", table.getPkList()));
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
