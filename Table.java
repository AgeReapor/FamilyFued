package FamilyFued;

import java.lang.StringBuilder;

public class Table {
    private String[][] table;

    public Table(String[][] table) {
        this.table = table;
    }

    public Table(String[] array) {
        this.table = new String[][] { array };
    }

    public Table(String cellValue) {
        this.table = new String[][] { { cellValue } };
    }

    public Table(Table table) {
        this.table = new String[table.getTable().length][table.getTable()[0].length];
        for (int i = 0; i < table.getTable().length; i++) {
            for (int j = 0; j < table.getTable()[0].length; j++) {
                this.table[i][j] = table.getTable()[i][j];
            }
        }
    }

    // Getters
    public String[][] getTable() {
        return table;
    }

    // Setters
    public void setTable(String[][] table) {
        this.table = table;
    }

    public void setTable(Table table) {
        this.table = table.getTable();
    }

    // Methods
    public String getTableString() {
        return getTableString(this.table);
    }

    public Table rotateTable() {
        this.setTable(Table.rotateTable(this));
        return this;
    }

    public Table appendTable(Table table) {
        this.setTable(Table.appendTable(this, table));
        return this;
    }

    public Table filterTable(int column, StringValidation validation) {
        this.setTable(Table.filterTable(this, column, validation));
        return this;
    }

    // Static Methods

    private static int[] getColumnWidths(String[][] table) {
        int[] widths = new int[table[0].length];
        for (int i = 0; i < widths.length; i++) {
            for (String[] row : table) {
                widths[i] = Math.max(widths[i], row[i].length());
            }
        }
        return widths;
    }

    private static StringBuilder getTableLine(int[] widths) {
        StringBuilder line = new StringBuilder();
        for (int width : widths) {
            line.append("+");
            for (int i = 0; i < width + 2; i++) {
                line.append("-");
            }
        }
        line.append("+\n");
        return line;
    }

    private static StringBuilder getRow(String[] row, int[] widths) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < widths.length; i++) {
            line.append("| ");
            line.append(row[i]);
            for (int j = 0; j < widths[i] - row[i].length() + 1; j++) {
                line.append(" ");
            }
        }
        line.append("|\n");
        return line;
    }

    public static String getTableString(String[][] table) {
        int[] widths = getColumnWidths(table);
        StringBuilder ret = getTableLine(widths);
        for (String[] row : table) {
            ret.append(getRow(row, widths));
            ret.append(getTableLine(widths));
        }
        return ret.toString();
    }

    public static Table rotateTable(Table table) {
        int width = table.getTable()[0].length;
        int height = table.getTable().length;
        String[][] newTable = new String[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newTable[j][height - 1 - i] = table.getTable()[i][j];
            }
        }
        return new Table(newTable);
    }

    public static Table appendTable(Table tableTop, Table tableBottom) {

        int cols = tableTop.getTable()[0].length;
        int rows = tableTop.getTable().length + tableBottom.getTable().length;

        String[][] table = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i < tableTop.getTable().length) {
                    table[i][j] = tableTop.getTable()[i][j];
                } else {
                    table[i][j] = tableBottom.getTable()[i - tableTop.getTable().length][j];
                }
            }
        }
        return new Table(table);
    }

    public static Table filterTable(Table table, int column, StringValidation validation) {
        try {
            String[][] tableArr = table.getTable();
            Table filteredTable = null;
            for (String[] row : tableArr) {
                if (!validation.validate(row[column]))
                    continue;
                Table newRow = new Table(row);
                if (filteredTable == null)
                    filteredTable = newRow;
                else
                    filteredTable.appendTable(newRow);
            }
            return filteredTable;
        } catch (Exception e) {
            return null;
        }
    }

    // methods
    public void print() {
        System.out.println(this.getTableString());
    }

    public void printWithEmptyAlt(String alt) {
        if (this == null || this.getTable().length == 0) {
            new Table(alt).print();
            return;
        }
        this.print();
    }
}
