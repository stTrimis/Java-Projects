package carsexample;

public class Car {
    private String brand,model;
    private double price;
    public Car()
    {
        brand="";
        model="";
        price=0.0;
    }
    
    public Car(String b,String m,double p)
    {
        brand =b;
        model =m;
        price =p;
    }
    
    public void setmodel(String m)
    {
        model = m;
    }
    
    public String getmodel()
    {
        return model;
    }
    
    public void setbrand(String b)
    {
        brand = b;
    }
    
    public String getbrand()
    {
        return brand;
    }
    
    public void setprice(double p)
    {
        price = p;
    }
    
    public double getprice()
    {
        return price;
    }
    
    
    public boolean equals(Car other)
    {
        if(model.equals(other.getmodel()) &&
           brand.equals(other.getbrand()) &&
           price == other.getprice()) return true;
        return false;
    }
    
    public String toString()
    {
        return brand+","+model+","+price;
    }
}
