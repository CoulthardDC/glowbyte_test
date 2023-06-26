package dao;

import model.Table;

import java.util.List;
import java.util.Optional;

public interface TableListDao {

    List<Table> findAll();

    Long save(Table table);
}
