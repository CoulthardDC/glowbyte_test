package dao;

import dao.impl.TableColsDaoImpl;
import dao.impl.TableListDaoImpl;
import db.DB;

public class DaoFactory {

    public static TableColsDao createTableColsDao() {
        return new TableColsDaoImpl(DB.getConnection());
    }

    public static TableListDao createTableListDao() {
        return new TableListDaoImpl(DB.getConnection());
    }

    public static BaseDao createBaseDao() {
        return new BaseDao(DB.getConnection());
    }
}
