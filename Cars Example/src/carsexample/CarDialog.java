
package carsexample;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CarDialog extends JDialog {
    private JTextField modeltext,brandtext,pricetext;
    private JButton okButton,cancelButton;
    private JPanel panel1,panel2;
    private boolean okFlag=true;
    
    public boolean isNumber(String t)
    {
        double d=0.0;
        try
        {
            d=Double.parseDouble(t);
            return true;
        }catch(Exception e)
        {
            return false;
        }
        
    }
    public Car getCar()
    {
        if(okFlag==false)
        return null;
        else 
        {
            if(modeltext.getText().length()==0 || brandtext.getText().length()==0
                    || pricetext.getText().length()==0) return null;
            if(!isNumber(pricetext.getText())) return null;
            return new Car(modeltext.getText(),brandtext.getText(),Double.parseDouble(pricetext.getText()));
        }
    }
    
    public void makePanel1()
    {
        panel1=new JPanel();
        add(panel1);
        panel1.setLayout(new GridLayout(1,6));
        panel1.add(new JLabel("MODEL: "));
        modeltext=new JTextField("",10);
        panel1.add(modeltext);
        panel1.add(new JLabel("BRAND: "));
        brandtext=new JTextField("",10);
        panel1.add(brandtext);
        panel1.add(new JLabel("PRICE: "));
        pricetext=new JTextField("0.0",10);
        panel1.add(pricetext);
    }
    
    public void makePanel2()
    {
        panel2=new JPanel();
        add(panel2);
        panel2.setLayout(new GridLayout(1,2));
        okButton=new JButton("OK");
        panel2.add(okButton);
        cancelButton=new JButton("CANCEL");
        panel2.add(cancelButton);
        okButton.setBackground(Color.BLUE);
        cancelButton.setBackground(Color.RED);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    okFlag=true;
                    setVisible(false);
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    okFlag=false;
                    setVisible(false);
            }
        });
    }
    
    public CarDialog(JFrame parent,String title)
    {
        super(parent,title);
        setModal(true);
        setSize(800,200);
        setResizable(false);
        setLayout(new FlowLayout());
        makePanel1();
        makePanel2();
        setVisible(true);
    }
}
