package view;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * CurrentResaltTableModel
 */
public class CurrentResaltTableModel extends AbstractTableModel {
    int userChooseNamberOfRepeats = 3;

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return userChooseNamberOfRepeats + 1;
    }
//


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //запрет редактирования колонки резатултата
        if (rowIndex == 0 && columnIndex == userChooseNamberOfRepeats) {
            return false;
        }
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
       this.setValueAt(aValue, rowIndex, columnIndex);
    }

    public String getColumnName(int columnIndex) {
        final String columnName = "Подход " + (columnIndex + 1);

        switch (columnIndex) {
            case 0:
                return columnName;
            case 1:
                return columnName;
            case 2:
                return columnName;
            default:
                return "Всего";
        }
    }


}
