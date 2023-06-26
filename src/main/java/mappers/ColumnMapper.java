package mappers;

import model.Column;
import model.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ColumnMapper {
    public Column mapToColumn(ResultSet resultSet) throws SQLException {
        Column column = new Column();
        column.setColumnName(resultSet.getString("COLUMN_NAME"));
        column.setTableName(resultSet.getString("TABLE_NAME"));
        column.setColumnType(resultSet.getString("COLUMN_TYPE"));
        return column;
    }
}
