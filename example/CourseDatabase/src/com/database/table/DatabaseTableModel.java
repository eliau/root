package com.database.table;

import java.util.*;
import java.sql.*;
import javax.swing.table.*;

 
public class DatabaseTableModel extends AbstractTableModel {
   private static final long serialVersionUID = 5L;
    private List<String> columnNames = new ArrayList<String>();
    private List<Class> columnTypes = new ArrayList<Class>();
    private List<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
 
    public int getRowCount() {
        synchronized (data) {
            return data.size();
        }
    }
 
    public int getColumnCount() {
        return columnNames.size();
    }
 
    public Object getValueAt(int row, int col) {
        synchronized (data) {
            return data.get(row).get(col);
        }
    }
 
    public String getColumnName(int col) {
        return columnNames.get(col);
    }
 
    public Class<?> getColumnClass(int col) {
        return columnTypes.get(col);
    }
 
    public boolean isCellEditable(int row, int col) {
        return false;
    }
 
    public void setValueAt(Object obj, int row, int col) {
        synchronized (data) {
            data.get(row).set(col, obj);
        }
    }
 
    /**
     * Core of the model. Initializes column names, types, data from ResultSet.
     *
     * @param rs ResultSet from which all information for model is token.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void setDataSource(ResultSet rs) throws SQLException, ClassNotFoundException {
        ResultSetMetaData rsmd = rs.getMetaData();
        columnNames.clear();
        columnTypes.clear();
        data.clear();
 
        int columnCount = rsmd.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            columnNames.add(rsmd.getColumnName(i + 1));
            Class<?> type = Class.forName(rsmd.getColumnClassName(i + 1));
            columnTypes.add(type);
        }
        fireTableStructureChanged();
        while (rs.next()) {
            ArrayList<Object> rowData = new ArrayList<Object>();
            for (int i = 0; i < columnCount; i++) {
                if (columnTypes.get(i) == String.class)
                    rowData.add(rs.getString(i + 1));
                else
                    rowData.add(rs.getObject(i + 1));
            }
            synchronized (data) {
                data.add(rowData);
                this.fireTableRowsInserted(data.size() - 1, data.size() - 1);
            }
        }
    }
    
}