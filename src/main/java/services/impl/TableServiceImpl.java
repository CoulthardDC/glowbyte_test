package services.impl;

import dao.TableColsDao;
import dao.TableListDao;
import model.Column;
import model.Table;
import services.TableService;

import java.util.List;

public class TableServiceImpl implements TableService {

    private final TableListDao tableListDao;
    private final TableColsDao tableColsDao;


    public TableServiceImpl(TableListDao tableListDao, TableColsDao tableColsDao) {
        this.tableListDao = tableListDao;
        this.tableColsDao = tableColsDao;
    }

    @Override
    public List<Column> getPkInfo() {
        return tableColsDao.findPkColumns();
    }

    @Override
    public List<Table> getAllTables() {
        return tableListDao.findAll();
    }

    @Override
    public void saveTable(Table table) {
        tableListDao.save(table);
    }

    @Override
    public void saveColumn(Column column) {
        tableColsDao.save(column);
    }

    @Override
    public List<Column> getAllColumns() {
        return tableColsDao.findAll();
    }
}
