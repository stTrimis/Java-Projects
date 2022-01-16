
/*This project is reserved by Stelios Trimis. Copyright © 2021*/


package goalproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GoalProject {

    
    public static void main(String[] args) {
        ArrayList<Game> games=new ArrayList<Game>();
         try {
            FileReader file=new FileReader("score.txt");
            Scanner in=new Scanner(file);
            while(in.hasNextLine())
            {
                String line=in.nextLine();
                if(line.length()<3) continue;
                if(line.startsWith("#")) continue;//if(line.charAt(0)=='#')
                //an ksekinaei i grammi me - i teleionei me -
                if(line.indexOf("-")==0 || line.indexOf("-")==line.length()-1) continue;
                //an den exei to -
                if(line.indexOf("-")==-1) continue;
                String a=line.substring(0, line.indexOf("-"));
                
                //gia na parakampsei to -
                String b=line.substring(line.indexOf("-")+1);
                Game g=new Game(Integer.parseInt(a),Integer.parseInt(b));
                games.add(g);
            }
            in.close();
            int countWins=0;
            
            for(Game g: games)
            {
                //den eixame isopalia
                if(!g.noneWin()) countWins++;
                else System.out.println("None win in "+g);
            }
           
            JOptionPane.showMessageDialog(null,"Total wins "+countWins);
        } catch (FileNotFoundException ex) {
           System.out.println("Error");
        }
    }
    
}
