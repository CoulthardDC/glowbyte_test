import controllers.Controller;
import dao.BaseDao;
import dao.DaoFactory;
import dao.TableColsDao;
import dao.TableListDao;
import model.Column;
import model.Table;
import services.TableService;
import services.WriteService;
import services.impl.FileWriteService;
import services.impl.TableServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        BaseDao baseDao = DaoFactory.createBaseDao();
        baseDao.createTableCol("TABLE_COLS");
        baseDao.createTableList("TABLE_LIST");

        TableListDao tableListDao = DaoFactory.createTableListDao();
        TableColsDao tableColsDao = DaoFactory.createTableColsDao();

        TableService tableService = new TableServiceImpl(
                tableListDao,
                tableColsDao
        );

        WriteService writeService = new FileWriteService("db.txt");

        Controller controller = new Controller(
                tableService,
                writeService
        );

        List<Table> tables = generateTableList();
        for (Table table : tables) {
            controller.saveTable(table);
        }

        controller.getAllTables().forEach(System.out::println);

        List<Column> columns = generateColumnList();
        for (Column column : columns) {
            //tableColsDao.save(column);
            controller.saveColumn(column);
        }
        controller.getAllColumns().forEach(System.out::println);
        System.out.println("___________________________________");
        controller.readFromDbAndWriteToFile();
    }

    public static List<Table> generateTableList() {
        List<Table> tables = new ArrayList<>();
        Table table = new Table();
        table.setTableName("users");
        table.setPkList(Arrays.asList("ID"));
        tables.add(table);
        table = new Table();
        table.setTableName("accounts");
        table.setPkList(Arrays.asList("account, account_id"));
        tables.add(table);
        return tables;
    }

    public static List<Column> generateColumnList() {
        List<Column> columns = new ArrayList<>();
        Column column = new Column();
        column.setTableName("users");
        column.setColumnName("first_name");
        column.setColumnType("VARCHAR");
        columns.add(column);

        column = new Column();
        column.setTableName("users");
        column.setColumnName("last_name");
        column.setColumnType("VARCHAR");
        columns.add(column);

        column = new Column();
        column.setTableName("users");
        column.setColumnName("id");
        column.setColumnType("INT");
        columns.add(column);

        column = new Column();
        column.setTableName("accounts");
        column.setColumnName("register_date");
        column.setColumnType("TIMESTAMP");
        columns.add(column);

        column = new Column();
        column.setTableName("accounts");
        column.setColumnName("CARD_NUMBER");
        column.setColumnType("INT");
        columns.add(column);

        column = new Column();
        column.setTableName("accounts");
        column.setColumnName("ACCOUNT");
        column.setColumnType("VARCHAR");
        columns.add(column);

        column = new Column();
        column.setTableName("accounts");
        column.setColumnName("ACCOUNT_ID");
        column.setColumnType("INT");
        columns.add(column);

        column = new Column();
        column.setTableName("accounts");
        column.setColumnName("ACCOUNT_NAME");
        column.setColumnType("VARCHAR");
        columns.add(column);

        return columns;
    }
}
