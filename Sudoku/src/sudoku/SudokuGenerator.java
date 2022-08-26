/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author straq
 */
public class SudokuGenerator {
    
    public static SudokuPuzzle generateRandomSudoku(SudokuPuzzleType puzzleType){
        SudokuPuzzle puzzle = new SudokuPuzzle(puzzleType.getRows(), puzzleType.getColumns(),puzzleType.getBoxWidth(),puzzleType.getBoxHeight(),puzzleType.getValidValues());
        
        Random randomGenerator = new Random();
        ArrayList<String> notUsedValidValues = new ArrayList<>(Arrays.asList(puzzle.getVALIDVALUES()));
        for(int r = 0; r< puzzle.getNumRows(); r++){
            int randomValue = randomGenerator.nextInt(notUsedValidValues.size());
            puzzle.makeMove(r,0,notUsedValidValues.get(randomValue));
            notUsedValidValues.remove(randomValue);
        }
        return puzzle;
    }
}
