package model;

public class Table {

    private String tableName;
    private String pkList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPkList() {
        return pkList;
    }

    public void setPkList(String pkList) {
        this.pkList = pkList;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", pkList=" + pkList +
                '}';
    }
}
