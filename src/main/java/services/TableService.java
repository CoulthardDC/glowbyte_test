package services;

import dao.TableListDao;
import model.Column;
import model.Table;

import java.util.List;

public interface TableService {
    List<Column> getPkInfo();
    List<Table> getAllTables();
    List<Column> getAllColumns();
    void saveTable(Table table);
    void saveColumn(Column column);
}
