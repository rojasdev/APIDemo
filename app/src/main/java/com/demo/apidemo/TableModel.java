package com.demo.apidemo;

public class TableModel {
    private int tableId;
    private String tableLabel;

    public TableModel(int tableId, String tableLabel) {
        this.tableId = tableId;
        this.tableLabel = tableLabel;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableLabel() {
        return tableLabel;
    }

    public void setTableLabel(String tableLabel) {
        this.tableLabel = tableLabel;
    }
}
