package dao;

import model.Column;

import java.util.List;

public interface TableColsDao {
    List<Column> findPkColumns();

    void save(Column column);

    List<Column> findAll();
}
