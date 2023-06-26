package dao;

import model.Column;
import model.Table;

import java.util.List;

public interface TableColsDao {
    List<Column> findPkColumns();

    Long save(Column column);

    List<Column> findAll();
}
