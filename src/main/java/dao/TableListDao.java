package dao;

import model.Table;

import java.util.List;

public interface TableListDao {

    List<Table> findAll();

    void save(Table table);
}
