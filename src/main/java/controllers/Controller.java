package controllers;

import model.Column;
import model.Table;
import services.TableService;
import services.WriteService;

import java.util.List;

public class Controller {
    private final TableService tableService;
    private final WriteService writeService;

    public Controller(TableService tableService, WriteService writeService) {
        this.tableService = tableService;
        this.writeService = writeService;
    }

    public void readFromDbAndWriteToFile() {
        writeService.saveAll(tableService.getPkInfo());
    }

    public List<Table> getAllTables() {
        return tableService.getAllTables();
    }

    public List<Column> getAllColumns() {
        return tableService.getAllColumns();
    }

    public void saveTable(Table table) {
        tableService.saveTable(table);
    }

    public void saveColumn(Column column) {
        tableService.saveColumn(column);
    }
}
