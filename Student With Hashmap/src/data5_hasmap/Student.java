package data5_hasmap;
public class Student 
{
    private int id;
    private String name,lastname;
    public Student(int i,String n,String l)
    {
        id=i;
        name=n;
        lastname=l;
    }
    
    public String getname() 
    {
        return name;
    }
    
    public String getlastname()
    {
        return lastname;
    }
    
    public int getid()
    {
        return id;
    }
    
    public String toString()
    {
        return name+","+lastname+","+id;
    }
}
