package goalproject;

public class Game {
    private int home,visitor;
     public Game(int h,int v)
    {
        home=h;
        visitor=v;
    }
    
    public int gethome()
    {
        return home;
    }
    
    public int getvisitor()
    {
       return visitor;
    }
    
    public boolean homeWin()
    {
        return home>visitor;
    }
    
    public boolean visitorWin()
    {
        return visitor>home;
    }
    
    public boolean noneWin()
    {
        return home == visitor;
    }
    
    public String toString()
    {
        return home+"-"+visitor;
    }
}
