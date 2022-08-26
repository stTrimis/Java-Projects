/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package sudoku;

/**
 *
 * @author straq
 */
public enum SudokuPuzzleType {
    
    NINEBYNINE(9, 9, 3, 3, new String[] {"1","2","3","4","5","6","7","8","9"});

    
    
    private final int rows;
    private final int columns;
    private final int boxWidth;
    private final int boxHeight;
    private String[] validValues;
    
    private SudokuPuzzleType (int rows, int columns,int boxWidth,int boxHeight, String[] validValues){
        this.validValues = validValues;
        this.rows = rows;
        this.columns = columns;
        this.boxHeight = boxHeight;
        this.boxWidth = boxWidth;
    }
    
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getBoxWidth() {
        return boxWidth;
    }

    public int getBoxHeight() {
        return boxHeight;
    }

    public String[] getValidValues() {
        return validValues;
    }
}
