package mappers;

import model.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableMapper {
    public Table mapToTable(ResultSet resultSet) throws SQLException {
        Table table = new Table();
        table.setTableName(resultSet.getString("TABLE_NAME"));
        table.setPkList(resultSet.getString("PK"));
        return table;
    }
}
