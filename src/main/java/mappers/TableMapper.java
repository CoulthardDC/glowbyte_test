package mappers;

import model.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class TableMapper {
    public Table mapToTable(ResultSet resultSet) throws SQLException {
        Table table = new Table();
        table.setTableName(resultSet.getString("TABLE_NAME"));
        table.setPkList(Arrays.asList(resultSet.getString("PK").split(",")));
        return table;
    }
}
