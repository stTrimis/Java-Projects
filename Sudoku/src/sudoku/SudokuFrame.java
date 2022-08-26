
package sudoku;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class SudokuFrame extends JFrame{

    private SudokuPanel sPanel;
    
    public SudokuFrame() throws HeadlessException{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sudoku");
        this.setMinimumSize(new Dimension(800,600));
        
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("Game");
        JMenu newGame = new JMenu("New Game");
        
        JMenuItem sixBySixGame = new JMenuItem("6 by 6 Game");
        JMenuItem nineByNineGame = new JMenuItem("9 by 9 Game");
        JMenuItem twelveByTwelveGame = new JMenuItem("12 by 12 Game");
        
        newGame.add(sixBySixGame);
        newGame.add(nineByNineGame);
        newGame.add(twelveByTwelveGame);
        
        file.add(newGame);
        menuBar.add(file);
        this.setJMenuBar(menuBar);
        
        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new FlowLayout());
        windowPanel.setPreferredSize(new Dimension(800,600));
        
        sPanel = new SudokuPanel();
        
        windowPanel.add(sPanel);
        
        this.add(windowPanel);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                SudokuFrame frame = new SudokuFrame();
                frame.setVisible(true);
            }
        });
    }
    
}
